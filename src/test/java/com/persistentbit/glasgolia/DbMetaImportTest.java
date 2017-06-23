package com.persistentbit.glasgolia;

import com.persistentbit.core.collections.PList;
import com.persistentbit.core.testing.TestCase;
import com.persistentbit.core.testing.TestRunner;
import com.persistentbit.glasgolia.db.dbdef.DbMetaCatalog;
import com.persistentbit.glasgolia.db.dbdef.DbMetaDataImporter;
import com.persistentbit.glasgolia.db.dbdef.DbMetaSchema;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/06/17
 */
public class DbMetaImportTest extends AbstractDbTest{


	static final TestCase	testPostgres = TestCase.name("TestPostgres import").code(tr -> {
		resetPostgres().withLogs(log -> lp.print(log)).orElseThrow();
		PList<DbMetaCatalog> catalogs = tr.add(postgresRun.run(DbMetaDataImporter.getCatalogs())).orElseThrow();
		PList<DbMetaSchema> schemas = catalogs.map(cat -> postgresRun.run(DbMetaDataImporter.getSchemas(cat)).orElseThrow()).<DbMetaSchema>flatten().plist();
		schemas.forEach(schema -> tr.info("Found Schema " + schema));
		tr.isEquals(schemas, postgresRun.run(DbMetaDataImporter.getAllSchemas()).orElseThrow());
	});



	public void testAll() {
		TestRunner.runAndPrint(AbstractDbTest.lp,DbMetaImportTest.class);
	}

	public static void main(String[] args) {
		lp.registerAsGlobalHandler();
		new DbMetaImportTest().testAll();
	}
}
