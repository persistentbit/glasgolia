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
		return getTables(schema,null);
	}

	public static DbWork<PList<DbMetaTable>> getTables(DbMetaSchema schema, @Nullable  String typeName){
		return DbWork.function(schema,typeName).code(log -> ctx -> ctx.get().<PList<DbMetaTable>>flatMapExc(con -> {
			return UJdbc.getList(con.getMetaData().getTables(
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
