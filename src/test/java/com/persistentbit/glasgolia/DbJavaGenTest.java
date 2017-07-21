package com.persistentbit.glasgolia;

import com.persistentbit.core.collections.PList;
import com.persistentbit.core.testing.TestCase;
import com.persistentbit.core.testing.TestRunner;
import com.persistentbit.glasgolia.jaql.codegen.posgresql.JavaGenTableSelection;
import com.persistentbit.glasgolia.jaql.codegen.posgresql.PostgresJavaGen;

/**
 * TODOC
 *
 * @author petermuys
 * @since 8/07/17
 */
public class DbJavaGenTest extends AbstractDbTest{

	static final TestCase genStateClasses = TestCase.name("GenerateStateClasses").code(tr -> {
		resetPostgres().doWithLogs(log -> lp.print(log)).orElseThrow();

		PostgresJavaGen javaGen = new JavaGenTableSelection(postgresRun)
				.addCatalogs(cat -> true)
				.flatMap(sel -> sel.addSchemas(schema -> "glasschema".equalsIgnoreCase(schema.getName().orElse(null))))
				 .flatMap(sel -> sel.addTablesAndViews(table -> true))
				 .map(sel -> new PostgresJavaGen(sel,"com.persistentbit.glasgolia.postrgrestest"))
				 .orElseThrow();
		tr.info(javaGen.getSelection().show().printToString());
		javaGen.generate().mapEmpty(empty -> PList.empty()).orElseThrow().forEach(gjf -> tr.info(gjf.getCode().printToString()));


		/*PList<DbMetaCatalog> catalogs = tr.add(postgresRun.run(DbMetaDataImporter.getCatalogs())).orElseThrow();
		PList<DbMetaSchema>  schemas  = catalogs.map(cat -> postgresRun.run(DbMetaDataImporter.getSchemas(cat)).orElseThrow()).<DbMetaSchema>flatten().plist();
		DbMetaSchema glasschema = schemas.find(s -> s.getName().get().equalsIgnoreCase("glasschema")).get();
		PList<DbMetaTable>   tables   = DbMetaDataImporter.getTables(glasschema, "TABLE").transaction(postgresRun).orElseThrow();
		DbJavaGenOptions options = new DbJavaGenOptionsImpl(postgresRun.getDbType(),"com.persistentbit.glasgolia.experiments.generated");
		tables.forEach(tab -> {
			tr.info("Table: " + tab.getName());
			//for(DbMetaColumn col : tab.getColumns()){
			//	tr.info("\t" + col);
			//}
			//tr.info("");
			GeneratedJavaSource javaSource = DbJavaGen.generateStateClasses(options, tab.getSchema(), tab).orElseThrow();
			System.out.println(javaSource.getCode().printToString());
		});*/

	});

	public void testAll() {
		TestRunner.runAndPrint(AbstractDbTest.lp, DbJavaGenTest.class);
	}

	public static void main(String[] args) {
		lp.registerAsGlobalHandler();
		new DbJavaGenTest().testAll();
	}
}
