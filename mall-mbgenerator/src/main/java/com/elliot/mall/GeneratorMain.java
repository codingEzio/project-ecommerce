package com.elliot.mall;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * [Technical Explanation]
 * This code is a Java class that uses MyBatis Generator, a tool for generating Java code that interacts
 * with a database. The class includes a main() method that reads a MyBatis Generator configuration file
 * and generates code based on the configuration. The generated code includes Java classes that map to
 * database tables, and XML files that define SQL statements to interact with the database.
 * [Explanation using analogies]
 * An analogy for this code would be that it's like having a robot that automatically builds furniture
 * from a set of instructions. The Generator class is like the robot, which reads the instructions for
 * building the furniture (i.e., the MyBatis Generator configuration file), and generates the necessary
 * parts and pieces to build the furniture (i.e., the Java classes and SQL statements for interacting
 * with the database). The warnings generated during the code generation process are like error messages
 * that the robot might display if it encountered a problem during the building process.
 */
public class GeneratorMain {
	public static void main(String[] args) throws Exception {
		List<String> warnings = new ArrayList<String>();

		boolean overwrite = true;

		InputStream inputStream = GeneratorMain.class.getResourceAsStream("/generatorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(inputStream);

		assert inputStream != null;
		inputStream.close();

		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);

		myBatisGenerator.generate(null);

		for (String warning: warnings) {
			System.out.println(warning);
		}
	}
}
