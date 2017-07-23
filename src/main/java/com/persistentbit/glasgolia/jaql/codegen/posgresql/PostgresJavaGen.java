package com.persistentbit.glasgolia.jaql.codegen.posgresql;

import com.persistentbit.core.collections.*;
import com.persistentbit.core.exceptions.ToDo;
import com.persistentbit.core.javacodegen.*;
import com.persistentbit.core.result.Result;
import com.persistentbit.core.tuples.Tuple2;
import com.persistentbit.core.utils.UString;
import com.persistentbit.glasgolia.db.dbdef.*;
import com.persistentbit.glasgolia.db.work.DbRun;
import com.persistentbit.glasgolia.db.work.DbWork;
import com.persistentbit.glasgolia.jaql.customtypes.*;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * TODOC
 *
 * @author petermuys
 * @since 11/07/17
 */
public class PostgresJavaGen{
	private final JavaGenTableSelection	selection;
	private final DbNameTransformer	nameTransformer;
	private final DbRun run;
	private final String rootPackage;

	public PostgresJavaGen(JavaGenTableSelection selection, String rootPackage,DbNameTransformer nameTransformer) {
		this.selection = selection;
		this.rootPackage = rootPackage;
		this.nameTransformer = nameTransformer;
		this.run = selection.getRun();
	}
	public PostgresJavaGen(JavaGenTableSelection selection,String rootPackage){
		this(selection, rootPackage,new DbNameTransformer(name -> UString.firstUpperCase(UString.snake_toCamelCase(name))));

	}

	public JavaGenTableSelection	getSelection() {
		return selection;
	}

	public Result<PList<GeneratedJavaSource>>	generate(){

		PList<DbMetaSchema> allSchemas = DbMetaDataImporter.getAllSchemas().transaction(run).orElseThrow();
		PList<DbEnumType> enumTypes = loadEnumTypes(allSchemas).transaction(run).orElseThrow();
		System.out.println("ALL ENUMS: " + enumTypes);

		PList<DbMetaTable> tables = selection.getTablesAndViews();

		PList<DbCustomType> customTypes = selection
			.getSchemas()
			.map(schema -> DbMetaDataImporter.getTypes(schema).transaction(run).orElseThrow()
				 .map(metaTable -> new DbCustomType(
				 	metaTable
				 	//metaTable.withColumns(metaTable.getColumns().map(col -> col.withType(col.getType().withIsNullable(false))))

					 ,nameTransformer.toJavaName(metaTable),PList.empty()))
			)
			.<DbCustomType>flatten()
			.plist();
		System.out.println("ALL CUSTOM TYPES: " + customTypes);
		PList<DbMetaUDT> udts = selection
			 .getSchemas()
			 .map(schema -> DbMetaDataImporter.getUDT(schema,null,new int[] {Types.DISTINCT}).transaction(run).orElseThrow())
			.<DbMetaUDT>flatten()
			.plist();
		udts.forEach(udt -> {
			System.out.println("UDT: " + udt);
		});

		PList<DbCustomType> customTypesWithFields = customTypes.map(dbCustomType -> {
			DbMetaTable ctTable = dbCustomType.getDefinition();
			DbCustomType res = dbCustomType;
			for(DbMetaColumn col : res.getDefinition().getColumns()){
				DbJavaField newField = getDbJavaField(ctTable,col.withType(col.getType().withIsNullable(false)),customTypes,enumTypes,udts);
				res = res.withFields(res.getFields().plus(newField));
			}
			return res;
		});




		PList<DbJavaTable> javaTables = tables.map(table -> generateJavaTable(table,customTypes,enumTypes,udts));



		// FIND USED STRUCTURES
		PSet<DbCustomType> usedTypes = PSet.empty();
		for(DbJavaTable table : javaTables){
			for(DbJavaField field : table.getJavaFields()){
				for(DbJavaFieldStruct structField : field.getStructures()){
					usedTypes = usedTypes.plus(
						customTypesWithFields.find(ct -> ct.getDefinition().equals(structField.getCustomTypeDbMeta())).get()
					);
				}
			}
		}
		for(DbCustomType ct : customTypesWithFields){
			for(DbJavaField field : ct.getFields()){
				for(DbJavaFieldStruct structField : field.getStructures()){
					usedTypes = usedTypes.plus(
						customTypesWithFields.find(uct -> uct.getDefinition().equals(structField.getCustomTypeDbMeta())).get()
					);
				}
			}
		}
		System.out.println("USED CUSTOM TYPES: " + usedTypes.map(ut-> ut.getJavaClassName()).toString(", "));

		//FIND USED ENUMS
		PSet<DbEnumType> usedEnums = PSet.empty();
		for(DbJavaTable table : javaTables){
			for(DbJavaField field : table.getJavaFields()){
				for(DbJavaFieldEnum enumField : field.getUsedEnums()){
					usedEnums = usedEnums.plus(
						enumTypes.find(et -> enumField.getEnumType().equals(et)).get()
					);
				}
			}
		}
		for(DbCustomType ct : usedTypes){
			for(DbJavaField field : ct.getFields()){
				for(DbJavaFieldEnum enumField : field.getUsedEnums()){
					usedEnums = usedEnums.plus(
						enumTypes.find(et -> enumField.getEnumType().equals(et)).get()
					);
				}
			}
		}
		System.out.println("USED ENUMS: " + usedEnums.map(ue-> ue.getName()).toString(", "));

		//CREATE ENUM SOURCE CODE
		Result<PList<GeneratedJavaSource>> genSourceEnums = Result.fromSequence(
			usedEnums.map(ue -> generateEnumSource(ue))
		).map(stream -> stream.plist());

		//CREATE STATE CLASSES SOURCE CODE

		Result<PList<GeneratedJavaSource>> genSourceCustomTypes = Result.fromSequence(usedTypes.map(ct -> generateStateClass(ct))).map(stream -> stream.plist());
		Result<PList<GeneratedJavaSource>> genSourceTables = Result.fromSequence(javaTables.map(table -> generateStateClass(table))).map(stream -> stream.plist());

		return genSourceCustomTypes.flatMap( ct ->
			genSourceEnums.flatMap(et -> genSourceTables.map(t ->
																 t.plusAll(ct).plusAll(et))

		));
	}

	private DbWork<PList<DbEnumType>> loadEnumTypes(PList<DbMetaSchema> allSchemas){
		return DbWork.function().code(log -> ctx -> ctx.get().mapExc(con -> {
			String sql = "SELECT pg_type.typname AS enumtype,\n" +
				"     pg_enum.enumlabel AS enumlabel,\n" +
				"     pg_enum.enumsortorder as sortorder,\n" +
				"     n.nspname as schema\n" +
				" FROM pg_type\n" +
				" JOIN pg_enum ON pg_enum.enumtypid = pg_type.oid\n" +
				" join pg_catalog.pg_namespace n on n.oid = pg_type.typnamespace\n" +
				"order by schema,enumtype,sortorder";
			PMap<String,DbEnumType> enumTypes = PMap.empty();
			try(Connection c = con){
				try(PreparedStatement stat = c.prepareStatement(sql)){

					try(ResultSet rs = stat.executeQuery()){
						while(rs.next()){
							String name = rs.getString("enumtype");
							String value = rs.getString("enumlabel");
							float sortOrder = rs.getFloat("sortorder");
							String schemaName = rs.getString("schema");
							DbMetaSchema schema = allSchemas.find(s -> s.getName().orElse("").equals(schemaName)).orElse(null);
							if(schema == null){
								throw new RuntimeException("Can't find schema " + schemaName + " for enum type " + name);
							}
							String fullName = schemaName + "." + name;
							DbEnumType type = enumTypes.getOrDefault(fullName,null);
							if(type == null){
								//A new type
								String packName = toJavaPackage(schema);
								String clsName = nameTransformer.toJavaName(name);
								type = new DbEnumType(schema,name,packName,clsName);
							}
							type = type.addValue(value,UString.toJavaIdentifier(value));
							enumTypes = enumTypes.put(fullName,type);
						}

					}
				}
			}
			return enumTypes.values().plist();
		}));
	}

	private Result<DbEnumType> findEnumType(String enumDbName){
		throw new ToDo();
	}


	private Optional<DbMetaTable> getCustomTypeMetaTable(DbMetaSchema usedInSchema,String dbTypeName){
		//FIRST, CHECK THE CURRENT SCHEMA
		for(DbMetaTable custom : DbMetaDataImporter.getTypes(usedInSchema).transaction(run).orElseThrow()){
			if(custom.getName().equals(dbTypeName)){
				return Optional.of(custom);
			}
		}
		//NOW CHECK THE REST
		PList<DbMetaSchema> allschemas = selection.getSchemas().filter(schema -> usedInSchema.equals(schema) == false);
		for(DbMetaSchema schemaToCheck : allschemas){
			for(DbMetaTable custom : DbMetaDataImporter.getTypes(schemaToCheck).transaction(run).orElseThrow()){
				if(custom.getName().equals(dbTypeName)){
					return Optional.of(custom);
				}
			}
		}
		return Optional.empty();
	}

	private DbJavaTable	generateJavaTable(DbMetaTable table,PList<DbCustomType> customTypes,PList<DbEnumType> enumTypes,PList<DbMetaUDT> domains){
		PList<DbJavaField> fields = table.getColumns().map(col -> getDbJavaField(table, col,customTypes,enumTypes,domains));
		String javaClassName = nameTransformer.toJavaName(table);
		String packName = rootPackage
			+ "." + nameTransformer.toJavaName(table.getSchema().getCatalog())
			+ "." + nameTransformer.toJavaName(table.getSchema());
		return new DbJavaTable(table,fields, javaClassName,packName);
	}

	private Result<GeneratedJavaSource> generateDomainSource(DbMetaUDT udt){
		return Result.function(udt).code(l -> {
			JClass cls = new JClass(nameTransformer.toJavaName(udt.getName()));
			JField field = null;

			if(true) throw new ToDo();

			cls = cls.addField(field);
			cls = cls.addMainConstructor(AccessLevel.Public);
			cls = cls.addMethod(field.createGetter());
			JJavaFile file = new JJavaFile(toJavaPackage(udt.getSchema()))
				.addClass(cls);
			return Result.success(file.toJavaSource());
		});
	}

	private Result<GeneratedJavaSource> generateEnumSource(DbEnumType enumType){
		return Result.function(enumType).code(l-> {
			JClass cls = new JClass(enumType.getJavaClassName());
			JField dbValue = new JField("dbValue",String.class)
				.notFinal();
			cls = cls.addField(dbValue);
			cls = cls.addMainConstructor(AccessLevel.Private);
			cls = cls.addRequiredFieldsConstructor(AccessLevel.Private);
			cls = cls.addMethod(dbValue.createGetter());


			JEnum j = new JEnum(cls,PList.empty());



			for(Tuple2<String,String> dbAndJava : enumType.getValueAndJavaNameList()){
				j = j.addInstance(new JEnumInstance(dbAndJava._2,PList.val("\"" +UString.escapeToJavaString(dbAndJava._1) + "\"" )));
			}
			JJavaFile file = new JJavaFile(toJavaPackage(enumType.getSchema()))
				.addEnum(j);
			return Result.success(file.toJavaSource());
		});
	}

	private Result<GeneratedJavaSource> generateStateClass(DbCustomType customType){
		return Result.function(customType).code(l -> {
			JClass cls = new JClass(customType.getJavaClassName());
			for(DbJavaField field : customType.getFields()){
				cls = cls.addField(field.createJField());
			}
			cls = cls.makeCaseClass();




			JJavaFile file = new JJavaFile(toJavaPackage(customType.getDefinition().getSchema()))
				.addClass(cls);
			return Result.success(file.toJavaSource());
		});
	}

	private Result<GeneratedJavaSource> generateStateClass(DbJavaTable table){
		return Result.function(table).code(l -> {
			JClass cls = new JClass(table.getJavaClassName());
			for(DbJavaField field : table.getJavaFields()){
				cls = cls.addField(field.createJField());
			}
			cls = cls.makeCaseClass();




			JJavaFile file = new JJavaFile(table.getPackName())
				.addClass(cls);
			return Result.success(file.toJavaSource());
		});
	}
	private String toJavaPackage(DbMetaSchema schema){
		return rootPackage
			+ "." + nameTransformer.toJavaName(schema.getCatalog())
			+ "." + nameTransformer.toJavaName(schema);

	}

	private DbJavaField	getDbJavaField(DbMetaTable table, DbMetaColumn column,PList<DbCustomType> customTypes, PList<DbEnumType> enumTypes, PList<DbMetaUDT> domains){
		DbMetaDataType mt = column.getType();
		String javaName = nameTransformer.toJavaName(table,column);
		switch(mt.getSqlType()){
			case Types.VARBINARY:
			case Types.LONGVARBINARY:
			case Types.BINARY:
			case Types.BLOB:
				return new DbJavaFieldCustomObject(column,javaName,PByteList.class);
			case Types.BOOLEAN:
				return new DbJavaFieldPrimitiveType(column, javaName, boolean.class);

			case Types.BIGINT:
				return new DbJavaFieldPrimitiveType(column, javaName, long.class);
			case Types.BIT: {
				if(mt.getColumnSize() == 1){
					return new DbJavaFieldPrimitiveType(column, javaName, boolean.class);
				}
				return new DbJavaFieldCustomObject(column, javaName, PBitList.class);
			}
			case Types.CHAR:
			case Types.NCHAR:
			case Types.VARCHAR:
			case Types.NVARCHAR:
			case Types.CLOB:
			case Types.LONGNVARCHAR:
			case Types.NCLOB:
				DbEnumType enumType = enumTypes.find(e -> e.getName().equals(mt.getDbTypeName().get())).orElse(null);
				if(enumType != null){
					return new DbJavaFieldEnum(column, javaName, enumType,nameTransformer.toJavaName(enumType.getName()),toJavaPackage(enumType.getSchema()));
				}
				return new DbJavaFieldCustomObject(column, javaName, String.class);
			case Types.DATE:
				return new DbJavaFieldCustomObject(column, javaName, LocalDate.class);

			case Types.NUMERIC:
			case Types.DECIMAL:
				return new DbJavaFieldCustomObject(column, javaName, BigDecimal.class);
			case Types.DOUBLE:
				if(mt.getDbTypeName().orElse("").equals("money")){
					return new DbJavaFieldCustomObject(column, javaName, Money.class);
				}
				return new DbJavaFieldPrimitiveType(column, javaName, double.class);
			case Types.REAL:
			case Types.FLOAT:
				return new DbJavaFieldPrimitiveType(column, javaName, float.class);
			case Types.INTEGER:
				return new DbJavaFieldPrimitiveType(column, javaName, int.class);

			case Types.SMALLINT:
				return new DbJavaFieldPrimitiveType(column, javaName, short.class);
			case Types.TIMESTAMP:
				if(mt.getDbTypeName().get().equals("timestamp")) {
					return new DbJavaFieldCustomObject(column, javaName, LocalDateTime.class);
				} else {
					return new DbJavaFieldCustomObject(column, javaName, ZonedDateTime.class);
				}

			case Types.TIME:
				return new DbJavaFieldCustomObject(column, javaName, Time.class);

			case Types.TINYINT:
				return new DbJavaFieldPrimitiveType(column, javaName, byte.class);
			case Types.STRUCT:
				String pack = toJavaPackage(table.getSchema());
				String clsName = nameTransformer.toJavaName(mt.getDbTypeName().get());
				return new DbJavaFieldStruct(column,javaName,getCustomTypeMetaTable(table.getSchema(),mt.getDbTypeName().get()).get(),clsName,pack);
			case Types.JAVA_OBJECT:
				throw new RuntimeException("Not Implemented: JAVA_OBJECT for " + column );
			case Types.SQLXML:
				return new DbJavaFieldCustomObject(column, javaName, Xml.class);
			case Types.DISTINCT:
				DbMetaUDT udt = domains.find(u -> u.getName().equals(mt.getDbTypeName().orElse(null))).get();
				return new DbJavaFieldDomain(column,udt,javaName,nameTransformer.toJavaName(udt.getName()),this.toJavaPackage(udt.getSchema()));



			case Types.ARRAY:
				return createArrayField(javaName, table, column,customTypes,enumTypes,domains);

			case Types.OTHER:
				switch(column.getType().getDbTypeName().orElse(null)){
					case "interval":
						return new DbJavaFieldCustomObject(column, javaName, Interval.class);
					case "cidr":
						return new DbJavaFieldCustomObject(column, javaName, Cidr.class);
					case "inet":
						return new DbJavaFieldCustomObject(column, javaName, Inet.class);
					case "macaddr":
						return new DbJavaFieldCustomObject(column, javaName, Macaddr.class);
					case "uuid":
						return new DbJavaFieldCustomObject(column, javaName, UUID.class);
					case "varbit":
						return new DbJavaFieldCustomObject(column, javaName, PBitList.class);
					case "tsvector":
						return new DbJavaFieldCustomObject(column, javaName, Tsvector.class);
					case "tsquery":
						return new DbJavaFieldCustomObject(column, javaName, Tsquery.class);
					case "json":
						return new DbJavaFieldCustomObject(column, javaName, Json.class);
					default:
						throw new RuntimeException("Don't know other type " + column.getType().getDbTypeName() + " for " + column);
				}

			default:
				throw new RuntimeException("Unknown: " + column);
		}
	}

	private DbJavaFieldArray	createArrayField(String javaName, DbMetaTable table, DbMetaColumn column,PList<DbCustomType> customTypes, PList<DbEnumType> enumTypes,PList<DbMetaUDT> udts){
		String dbTypeName = column.getType().getDbTypeName().orElse(null);
		DbJavaField element;
		switch(dbTypeName){
			case "_numeric":
				element= new DbJavaFieldCustomObject(column, javaName, BigDecimal.class);
				break;
			case "_int8":
				element = new DbJavaFieldPrimitiveType(column, javaName, long.class);
				break;
			case "_int4":
				element = new DbJavaFieldPrimitiveType(column, javaName, int.class);
				break;
			case "_int2":
				element = new DbJavaFieldPrimitiveType(column, javaName, short.class);
				break;
			case "_float4":
				element = new DbJavaFieldPrimitiveType(column, javaName, float.class);
				break;
			case "_float8":
				element = new DbJavaFieldPrimitiveType(column, javaName, double.class);
				break;
			case "_money":
				element = new DbJavaFieldCustomObject(column, javaName, Money.class);
				break;
			case "_varchar":
			case "_text":
			case "_bpchar":
				element = new DbJavaFieldCustomObject(column, javaName, String.class);
				break;
			case "_bytea":
				element = new DbJavaFieldCustomObject(column,javaName,PByteList.class);
				break;
			case "_timestamp":
				element = new DbJavaFieldCustomObject(column, javaName, LocalDateTime.class);
				break;
			case "_timestamptz":
				element = new DbJavaFieldCustomObject(column, javaName, ZonedDateTime.class);
				break;
			case "_date":
				element = new DbJavaFieldCustomObject(column, javaName, LocalDate.class);
				break;
			case "_time":
				element = new DbJavaFieldCustomObject(column, javaName, Time.class);
				break;
			case "_timetz":
				element = new DbJavaFieldCustomObject(column, javaName, Time.class);
				break;
			case "_interval":
				element = new DbJavaFieldCustomObject(column, javaName, Interval.class);
				break;
			case "_bool":
				element = new DbJavaFieldPrimitiveType(column, javaName, boolean.class);
				break;
			case "_cidr":
				element = new DbJavaFieldCustomObject(column, javaName, Cidr.class);
				break;
			case "_inet":
				element = new DbJavaFieldCustomObject(column, javaName, Inet.class);
				break;
			case "_macaddr":
				element = new DbJavaFieldCustomObject(column, javaName, Macaddr.class);
				break;
			case "_bit":
			case "_varbit":
				element = new DbJavaFieldCustomObject(column,javaName, PBitList.class);
				break;
			case "_tsvector":
				element = new DbJavaFieldCustomObject(column, javaName, Tsvector.class);
				break;

			case "_tsquery":
				element = new DbJavaFieldCustomObject(column, javaName, Tsquery.class);
				break;

			case "_uuid":
				element = new DbJavaFieldCustomObject(column, javaName, UUID.class);
				break;
			case "_xml":
				element = new DbJavaFieldCustomObject(column, javaName, Xml.class);
				break;

			case "_json":
				element = new DbJavaFieldCustomObject(column, javaName, Json.class);
				break;

			//case "_enum_test":
			//case "_full_name":
			//case "us_postal_code":

			default:
				DbEnumType enumType = enumTypes.find(et -> ("_" + et.getName()).equals(dbTypeName)).orElse(null);
				if(enumType != null){

					element = new DbJavaFieldEnum(column,javaName,enumType,nameTransformer.toJavaName(enumType.getName()),toJavaPackage(enumType.getSchema()));
					break;
				}
				DbCustomType customType = customTypes.find(ct -> ("_" + ct.getDefinition().getName()).equals(dbTypeName)).orElse(null);
				if(customType != null){
					String pack = toJavaPackage(customType.getDefinition().getSchema());
					String clsName = nameTransformer.toJavaName(customType.getDefinition().getName());
					element = new DbJavaFieldStruct(column,javaName,customType.getDefinition(),clsName,pack);
					break;
				}
				/*if(dbTypeName.startsWith("_")){
					DbMetaTable customTypeMeta = getCustomTypeMetaTable(table.getSchema(),dbTypeName).orElse(null);
					if(customTypeMeta != null){
						return new DbJavaFieldArray(column,javaName,new DbJavaFieldStruct(column,javaName,customTypeMeta));
					}
					//MUST BE AN ENUM
				}


				if(true) return null;*/

				throw new RuntimeException("Don't know array element type " + dbTypeName + " for " + column);
		}

		return new DbJavaFieldArray(column,javaName, element);
	}


	private JField	generateStateField(DbMetaTable table, DbMetaColumn column){
		DbMetaDataType mt = column.getType();
		String javaName = nameTransformer.toJavaName(table,column);
		switch(mt.getSqlType()){
			case Types.VARBINARY:
			case Types.LONGVARBINARY:
			case Types.BINARY:
			case Types.BLOB:
				return new JField(javaName,PByteList.class);
			case Types.BOOLEAN:
				return new JField(javaName,mt.getIsNullable() ? Boolean.class : boolean.class);

			case Types.BIGINT:
				return new JField(javaName,mt.getIsNullable() ? Long.class : long.class);
			case Types.BIT: {
				if(mt.getColumnSize() == 1){
					return new JField(javaName,mt.getIsNullable() ? Boolean.class : boolean.class);
				}
				return new JField(javaName,PBitList.class);
			}


			case Types.CHAR:
			case Types.NCHAR:
			case Types.VARCHAR:
			case Types.NVARCHAR:
			case Types.CLOB:
			case Types.LONGNVARCHAR:
			case Types.NCLOB:
				return new JField(javaName,String.class);
			case Types.DATE:
				return new JField(javaName,LocalDate.class);

			case Types.NUMERIC:
			case Types.DECIMAL:
				return new JField(javaName,BigDecimal.class);
			case Types.DOUBLE:
				if(mt.getDbTypeName().orElse("").equals("money")){
					return new JField(javaName, Money.class);
				}
				return new JField(javaName,mt.getIsNullable() ? Double.class : double.class);
			case Types.REAL:
			case Types.FLOAT:
				return new JField(javaName,mt.getIsNullable() ? Float.class : float.class);
			case Types.INTEGER:
				return new JField(javaName,mt.getIsNullable() ? Integer.class : int.class);

			case Types.SMALLINT:
				return new JField(javaName,mt.getIsNullable() ? Short.class : short.class);
			case Types.TIMESTAMP:
				return new JField(javaName,LocalDateTime.class);
			case Types.TIME:
				return new JField(javaName,LocalTime.class);
			case Types.TINYINT:
				return new JField(javaName,mt.getIsNullable() ? Byte.class : byte.class);
			case Types.STRUCT:
				return new JField(javaName,nameTransformer.toJavaName(table,column));
			case Types.JAVA_OBJECT:
				throw new RuntimeException("Not Implemented: JAVA_OBJECT for " + column );
			case Types.SQLXML:
				return new JField(javaName,String.class);
			case Types.DISTINCT:
				return new JField(javaName,nameTransformer.toJavaName(table,column));



			case Types.ARRAY:
				return new JField(javaName,PList.class);
			case Types.OTHER:
				switch(column.getType().getDbTypeName().orElse(null)){
					case "interval":
						return new JField(javaName, Interval.class);
					case "cidr":
						return new JField(javaName, Cidr.class);
					case "inet":
						return new JField(javaName, Inet.class);
					case "macaddr":
						return new JField(javaName, Macaddr.class);
					case "uuid":
						return new JField(javaName, UUID.class);
					case "varbit":
						return new JField(javaName, PBitList.class);
					case "tsvector":
						return new JField(javaName, Tsvector.class);
					case "tsquery":
						return new JField(javaName, Tsquery.class);
					case "json":
						return new JField(javaName,Json.class);
					default:
						throw new RuntimeException("Don't know other type " + column.getType().getDbTypeName() + " for " + column);
				}

			default:
				throw new RuntimeException("Unknown: " + column);
		}
	}
}
