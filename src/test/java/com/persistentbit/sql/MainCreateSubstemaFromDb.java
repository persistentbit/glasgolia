package com.persistentbit.sql;

/**
 * TODOC
 *
 * @author petermuys
 * @since 15/01/17
 */
public class MainCreateSubstemaFromDb extends SQLTestTools{
//
//	public static void main(String[] args) {
//		logPrint.registerAsGlobalHandler();
//		dbRun.run(builder.buildOrUpdate()).orElseThrow();
//
//
//		SubstemaCompiler compiler     = new SubstemaCompiler(
//			new DependencySupplier().withResources()
//		);
//		RSubstema        baseSubstema = compiler.compile("com.persistentbit.sql.test").orElseThrow();
//		DbSubstemaGen    gen          = new DbSubstemaGen(testDbConnector, baseSubstema, compiler);
//		gen.loadTables().orElseThrow();
//
//		//gen.mergeWithBase();
//
//		gen.mergeEmbedded(".*", ".*", "com.persistentbit.sql.test", "Address");
//
//		baseSubstema = gen.replaceBase(true);
//		SubstemaSourceGenerator codeGen = new SubstemaSourceGenerator();
//		codeGen.addSubstema(baseSubstema);
//		String packageName = "com.persistentbit.sql.test";
//		Path source =SourcePath.findTestSourcePath(MainCreateSubstemaFromDb.class, packageName+".substema").orElseThrow();
//		Path resources = SourcePath.findTestResourcePath(MainCreateSubstemaFromDb.class,packageName+".substema").orElseThrow();
//
//
//		IOFiles.write(codeGen.toString(),resources.resolve(packageName+".substema").toFile(),IO.utf8);
//		System.out.println(codeGen);
//
//		PList<GeneratedJava> generatedJavas = DbJavaGen
//			.generate(new JavaGenOptions(true,true), "com.persistentbit.sql.test", compiler)
//			.map(gr -> gr.orElseThrow());
//
//		Path destSource = source.resolve("com").resolve("persistentbit").resolve("sql").resolve("test");
//		IOFiles.mkdirsIfNotExisting(source.toFile())
//			   .ifPresent(path -> {
//				generatedJavas.forEach(gj -> {
//					IOFiles.write(gj.code,destSource.resolve(gj.name.getClassName()+".java").toFile(),IO.utf8);
//				});
//			})
//		;
//
//	}
}
