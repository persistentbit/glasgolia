package com.persistentbit.glasgolia.db.dbdef;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.collections.PList;
import com.persistentbit.core.collections.PStream;
import com.persistentbit.core.exceptions.ToDo;
import com.persistentbit.core.result.Result;
import com.persistentbit.glasgolia.db.work.DbWork;
import com.persistentbit.glasgolia.nativesql.UJdbc;

/**
 * TODOC
 *
 * @author petermuys
 * @since 3/06/17
 */
public class DbMetaDataImporter{

	public static DbWork<PList<DbMetaCatalog>> getCatalogs(){
		return DbWork.function().code(log -> ctx -> ctx.get().<PList<DbMetaCatalog>>flatMapExc(con ->{
			return UJdbc.getList(con.getMetaData().getCatalogs(),
				 rs -> new DbMetaCatalog(rs.getString("TABLE_CAT"))
			);

		}));
	}
	public static DbWork<PList<DbMetaSchema>> getSchemas(DbMetaCatalog catalog) {
		return DbWork.function(catalog).code(log -> ctx -> ctx.get().<PList<DbMetaSchema>>flatMapExc(con ->{
			return UJdbc.getList(con.getMetaData().getSchemas(catalog.getName().orElse(""),null),
								 rs -> DbMetaSchema.buildExc(b -> b
									  .setCatalog(catalog)
									  .setName(rs.getString("TABLE_SCHEM"))
								 ).orElseThrow()
			);

		}));
	}


	public static DbWork<PList<DbMetaSchema>> getAllSchemas(){
		return DbWork.function().code(log -> ctx ->
			getCatalogs().execute(ctx)
			.flatMap( catList ->
				Result.fromSequence(
					catList.mapExc(cat -> getSchemas(cat).execute(ctx))
			    ).map(PStream::<DbMetaSchema>flatten).map(PStream::plist)
			));
	}

	public static DbWork<PList<String>> getTableTypes(DbMetaSchema schema){
		return DbWork.function(schema).code(log -> ctx -> ctx.get().<PList<String>>flatMapExc(con ->{
			return UJdbc.getList(con.getMetaData().getTableTypes(),
								 rs -> rs.getString("TABLE_TYPE")
			);

		}));
	}

	public static DbWork<PList<DbMetaTable>> getTables(DbMetaSchema schema){
		return getTables(schema,"TABLE");
	}

	public static DbWork<PList<DbMetaTable>> getViews(DbMetaSchema schema) { return getTables(schema,"VIEW");}

	public static DbWork<PList<DbMetaTable>> getTablesAndViews(DbMetaSchema schema){
		return getTables(schema)
			.combine(tables -> getViews(schema)).map(t -> t._1.plusAll(t._2));

	}
	public static DbWork<PList<DbMetaTable>> getTypes(DbMetaSchema schema){
		return DbWork.function(schema).code(log -> ctx -> {
			PList<DbMetaTable> res = PList.empty();

			for(String typeName : ctx.getDbType().map(dbType-> dbType.getDbMetaTypeNameForCustomTypes()).orElseThrow()){
				res = res.plusAll(getTables(schema,typeName).execute(ctx).orElseThrow());
			}
			return Result.success(res);
		});
	}

	public static DbWork<PList<DbMetaTable>> getTables(DbMetaSchema schema, @Nullable  String typeName){
		return DbWork.function(schema,typeName).code(log -> ctx -> ctx.get().<PList<DbMetaTable>>flatMapExc(con -> {
			Result<PList<DbMetaTable>> tables = UJdbc.getList(con.getMetaData().getTables(
				schema.getCatalog().getName().orElse(""),
				schema.getName().orElse(""),
				null,
				typeName == null ? null : new String[] { typeName }
			), rs -> {
				String table_cat = rs.getString("TABLE_CAT");
				String table_schem = rs.getString("TABLE_SCHEM");
				String table_name = rs.getString("TABLE_NAME");
				String table_type = rs.getString("TABLE_TYPE");
				String remarks = rs.getString("REMARKS");

				//String type_cat = rs.getString("TYPE_CAT");
				//String type_schem = rs.getString("TYPE_SCHEM");
				//String type_name = rs.getString("TYPE_NAME");
				//String self_referencing_col_name = rs.getString("SELF_REFERENCING_COL_NAME");
				//String ref_generation = rs.getString("REF_GENERATION");
				return DbMetaTable.build(b -> b
					.setType(table_type)
					.setSchema(schema)
					.setName(table_name)
					.setComment(remarks)
				);
			});
			return tables.flatMap(tableList -> {
				return Result.fromSequence(tableList.mapExc(table -> loadColumns(table).execute(ctx))).map(l -> l.plist());
			});
		}));
	}

	public static DbWork<PList<DbMetaUDT>> getUDT(DbMetaSchema schema, @Nullable  String typeName, @Nullable int[] sqlTypes){
		return DbWork.function(schema,typeName).code(log -> ctx -> ctx.get().<PList<DbMetaUDT>>flatMapExc(con -> {
			Result<PList<DbMetaUDT>> types = UJdbc.getList(con.getMetaData().getUDTs(
				schema.getCatalog().getName().orElse(""),
				schema.getName().orElse(""),
				typeName,
				sqlTypes
			), rs -> {
				String type_cat = rs.getString("TYPE_CAT");
				String type_schem = rs.getString("TYPE_SCHEM");
				String type_name = rs.getString("TYPE_NAME");
				String class_name = rs.getString("CLASS_NAME");
				int data_type = rs.getInt("DATA_TYPE");
				String remarks = rs.getString("REMARKS");
				int base_type = rs.getInt("BASE_TYPE");
				//String type_cat = rs.getString("TYPE_CAT");
				//String type_schem = rs.getString("TYPE_SCHEM");
				//String type_name = rs.getString("TYPE_NAME");
				//String self_referencing_col_name = rs.getString("SELF_REFERENCING_COL_NAME");
				//String ref_generation = rs.getString("REF_GENERATION");
				return DbMetaUDT.build(b -> b
				   .setSchema(schema)
				   .setBaseType(base_type)
				   .setDataType(data_type)
				   .setJavaClassName(class_name)
				   .setName(type_name)
				   .setRemarks(remarks)
				);
			});
			return types;
		}));
	}


	/*
	COLUMN_NAME String => column name
DATA_TYPE int => SQL type from java.sql.Types
TYPE_NAME String => Data source dependent type name, for a UDT the type name is fully qualified
COLUMN_SIZE int => column size.
BUFFER_LENGTH is not used.
DECIMAL_DIGITS int => the number of fractional digits. Null is returned for data types where DECIMAL_DIGITS is not applicable.
NUM_PREC_RADIX int => Radix (typically either 10 or 2)
NULLABLE int => is NULL allowed.
columnNoNulls - might not allow NULL values
columnNullable - definitely allows NULL values
columnNullableUnknown - nullability unknown
REMARKS String => comment describing column (may be null)
COLUMN_DEF String => default value for the column, which should be interpreted as a string when the value is enclosed in single quotes (may be null)
SQL_DATA_TYPE int => unused
SQL_DATETIME_SUB int => unused
CHAR_OCTET_LENGTH int => for char types the maximum number of bytes in the column
ORDINAL_POSITION int => index of column in table (starting at 1)
IS_NULLABLE String => ISO rules are used to determine the nullability for a column.
YES --- if the column can include NULLs
NO --- if the column cannot include NULLs
empty string --- if the nullability for the column is unknown
SCOPE_CATALOG String => catalog of table that is the scope of a reference attribute (null if DATA_TYPE isn't REF)
SCOPE_SCHEMA String => schema of table that is the scope of a reference attribute (null if the DATA_TYPE isn't REF)
SCOPE_TABLE String => table name that this the scope of a reference attribute (null if the DATA_TYPE isn't REF)
SOURCE_DATA_TYPE short => source type of a distinct type or user-generated Ref type, SQL type from java.sql.Types (null if DATA_TYPE isn't DISTINCT or user-generated REF)
IS_AUTOINCREMENT String => Indicates whether this column is auto incremented
YES --- if the column is auto incremented
NO --- if the column is not auto incremented
empty string --- if it cannot be determined whether the column is auto incremented
IS_GENERATEDCOLUMN String => Indicates whether this is a generated column
YES --- if this a generated column
NO --- if this not a generated column
empty string --- if it cannot be determined whether this is a generated column
	 */
	public static DbWork<DbMetaTable> loadColumns(DbMetaTable table){
		return DbWork.function(table).code(log -> ctx -> ctx.get().flatMapExc(con -> {
			Result<PList<DbMetaColumn>> columns =  UJdbc.getList(
				con.getMetaData().getColumns(
					table.getSchema().getCatalog().getName().orElse(""),
					table.getSchema().getName().orElse(""),
					table.getName(),null)
				,rs -> {
					String name = rs.getString("COLUMN_NAME");
					int dataType = rs.getInt("DATA_TYPE");
					String typeName = rs.getString("TYPE_NAME");
					int columnSize = rs.getInt("COLUMN_SIZE");
					int decimalDigits = rs.getInt("DECIMAL_DIGITS");
					int nullable = rs.getInt("NULLABLE");
					String remarks = rs.getString("REMARKS");
					String column_def = rs.getString("COLUMN_DEF");
					int charOctet_length = rs.getInt("CHAR_OCTET_LENGTH");
					int ordinal_position = rs.getInt("ORDINAL_POSITION");
					String isNullable = rs.getString("IS_NULLABLE");
					//String scopeCatalog = rs.getString("SCOPE_CATALOG");
					//String scopeSchema = rs.getString("SCOPE_SCHEMA");
					//String scopeTable = rs.getString("SCOPE_TABLE");
					int sourceDataType = rs.getInt("SOURCE_DATA_TYPE");
					String isAutoIncrement = rs.getString("IS_AUTOINCREMENT");
					//String isGeneratedColumn = rs.getString("IS_GENERATEDCOLUMN");
					DbMetaDataType type = new DbMetaDataType(dataType)
						   .withIsAutoIncrement(isAutoIncrement.equalsIgnoreCase("YES"))
						   .withIsNullable(isNullable.equalsIgnoreCase("YES"))
						   .withDbTypeName(typeName)
						   .withColumnSize(columnSize)
						   .withDecimalDigits(decimalDigits);

					return DbMetaColumn.build(b -> b
						.setComment(remarks)
						.setDefaultValue(column_def)
						.setName(name)
						.setType(type)
					);

				}
			);
			return columns.map(cols -> table.withColumns(cols));
		}));
	}


	public static DbWork<DbMetaSchema> importSchema(String catalogName, String schemaName){
		return DbWork.function(catalogName,schemaName).code(log -> ctx -> ctx.get().<DbMetaSchema>flatMapExc(con ->{
			throw new ToDo();
			/*DatabaseMetaData md = con.getMetaData();
			return UJdbc.getList(md.getSchemas(),rs -> {
				return new DbMetaSchema(
					rs.getString("TABLE_SCHEM"),
					rs.getString("TABLE_CATALOG")
				);
			}).flatMap(list ->
					  Result.fromOpt(
					  	list.find(schem -> schemaName.equals(schem.getName().orElse(null))))
			)
			.<DbMetaSchema>flatMapExc(schema -> {
				log.info("Found schema: " + schema.getCatalogName() + ", " + schema.getName());

				try(ResultSet ttypes = md.getTableTypes()){

					while(ttypes.next()){

					}
				}

				ResultSet rs = md.getTables(null, schemaName,"%", new String[]{ "TABLE","VIEW"});
				while(rs.next()){
					String tableName = rs.getString("TABLE_NAME");
					String tableType = rs.getString("TABLE_TYPE");
					String tableComment = rs.getString("REMARKS");
					log.info("Found Table " + tableName);
					DbMetaTable.Type setType;
					switch(tableType){
						case "TABLE": setType = DbMetaTable.Type.table;break;
						case "VIEW": setType = DbMetaTable.Type.view;break;
						default: return Result.failure("Don't know tabletype " + tableType);
					}
					DbMetaTable set = new DbMetaTable(setType,tableName).comment(tableComment);

				}
				return Result.success(schema);
			});*/

		}));
	}

	private Result<DbMetaTable> importSet(DbMetaSchema schema, DbMetaTable set){
		throw new ToDo();
	}

}
