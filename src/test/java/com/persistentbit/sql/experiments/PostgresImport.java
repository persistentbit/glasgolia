package com.persistentbit.sql.experiments;

import com.persistentbit.core.ModuleCore;
import com.persistentbit.core.OK;
import com.persistentbit.core.logging.printing.LogPrint;
import com.persistentbit.core.result.Result;
import com.persistentbit.glasgolia.db.connections.DbConnector;
import com.persistentbit.glasgolia.db.connections.DbSimpleConnector;
import com.persistentbit.glasgolia.db.dbdef.DbMetaCatalog;
import com.persistentbit.glasgolia.db.dbdef.DbMetaSchema;
import com.persistentbit.glasgolia.db.dbdef.DbMetaTable;
import com.persistentbit.glasgolia.db.dbdef.DbMetaDataImporter;
import com.persistentbit.glasgolia.db.types.DbPostgres;
import com.persistentbit.glasgolia.db.work.DbRun;
import com.persistentbit.glasgolia.db.work.DbWork;

import java.io.PrintStream;

/**
 * TODOC
 *
 * @author petermuys
 * @since 5/06/17
 */
public class PostgresImport{

	public static DbWork<OK> dumpTable(PrintStream out, DbMetaTable table){
		return ctx -> {
			out.println("TABLE  " + table);
			return OK.result;
		};
	}

	public static DbWork<OK> dumpSchema(PrintStream out, DbMetaSchema schema){
		return ctx -> {
			out.println("SCHEMA " + schema.getFullName());
			return DbMetaDataImporter.getTables(schema,null).execute(ctx).flatMap(tables -> {
				for(DbMetaTable table : tables){
					Result<OK> res = dumpTable(out,table).executeNoExc(ctx);
					if(res.isError()){
						return res.map(v-> null);
					}
				}
				out.println("END SCHEMA " + schema.getFullName());
				out.println();
				return OK.result;
			});
		};
	}

	public static DbWork<OK> dumpCatalog(PrintStream out, DbMetaCatalog catalog){
		return ctx -> DbMetaDataImporter.getSchemas(catalog).execute(ctx).flatMap(schemas -> {
			out.println("CATALOG " + catalog.getName().orElse(""));
			for(DbMetaSchema schema : schemas){
				Result<OK> res = dumpSchema(out,schema).executeNoExc(ctx);
				if(res.isError()){
					return res.map(v-> null);
				}
			}
			out.println("END CATALOG " + catalog.getName().orElse(""));
			out.println();
			return OK.result;
		});
	}

	public static DbWork<OK> dumpDb(PrintStream out){
		return ctx -> DbMetaDataImporter.getCatalogs().execute(ctx).flatMap(catalogs -> {
			for(DbMetaCatalog catalog : catalogs){
				Result<OK> res = dumpCatalog(out,catalog).executeNoExc(ctx);
				if(res.isError()){
					return res.map(v-> null);
				}

			}
			return OK.result;

		});


	}

	public static void main(String[] args) throws Exception {
		LogPrint lp = ModuleCore.consoleLogPrint.registerAsGlobalHandler();
		DbConnector
			connector = new DbSimpleConnector(DbPostgres
												  .connectionUrl("localhost", "glasgolia", "testdb"), "glasgolia_user", "glasgolia_pwd");
		DbRun runner = DbRun.create(connector);
		//Result<DbMetaSchema> res = runner.run(DbSchemaImporter.importSchema(null, "testdb"));
		//lp.print(res.getLog());
		runner.run(dumpDb(System.out)).orElseThrow();
	}
}
