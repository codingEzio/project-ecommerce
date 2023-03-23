package com.elliot.mall;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Properties;

/**
 * [Technical Explanation]
 * This code creates a custom comment generator for use with MyBatis Generator, a tool for generating Java
 * code that interacts with a database. The custom generator extends the DefaultCommentGenerator class
 * provided by MyBatis and overrides its methods to customize the comments generated for database columns
 * and Java fields. It also adds support for generating Swagger annotations based on the database column
 * comments.
 * [Explanation using analogies]
 * An analogy for this code would be that it's like having a secretary who helps to write documentation
 * for a company's products. The CommentGenerator is like the secretary, who adds comments to the generated
 * Java code. The DefaultCommentGenerator class is like a set of default instructions that the secretary
 * follows when writing comments, while the custom CommentGenerator adds specific instructions for
 * generating comments based on the database column comments. The Swagger annotations are like additional
 * notes that the secretary adds to the documentation to help developers understand the code.</p>
 */
public class GeneratorDBFieldComment extends DefaultCommentGenerator {
	private boolean addRemarkComments = false;
	private static final String EXAMPLE_SUFFIX = "Example";
	private static final String MAPPER_SUFFIX = "Mapper";
	private static final String API_MODEL_PROPERTY_FULL_CLASS_NAME = "io.swagger.annotations.ApiModelProperty";

	@Override
	public void addConfigurationProperties(Properties properties) {
		super.addConfigurationProperties(properties);

		this.addRemarkComments = StringUtility.isTrue(properties.getProperty("addRemarkComments"));
	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
	                            IntrospectedColumn introspectedColumn) {
		String remarks = introspectedColumn.getRemarks();

		if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
			if (remarks.contains("\"")) {
				remarks = remarks.replace("\"", "'");
			}

			field.addJavaDocLine("@ApiModelProperty(value = \"" + remarks + "\")");
		}
	}

	private void addFieldJavaDoc(Field field, String remarks) {
		field.addJavaDocLine("/**");

		String[] remarkLines = remarks.split(System.getProperty("line.separator"));
		for (String remarkLine: remarkLines) {
			field.addJavaDocLine(" * " + remarkLine);
		}

		addJavadocTag(field, false);

		field.addJavaDocLine(" */");
	}

	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {
		super.addJavaFileComment(compilationUnit);

		if (!compilationUnit.getType().getFullyQualifiedName().contains(MAPPER_SUFFIX) &&
				!compilationUnit.getType().getFullyQualifiedName().contains(EXAMPLE_SUFFIX)) {
			compilationUnit.addImportedType(new FullyQualifiedJavaType(API_MODEL_PROPERTY_FULL_CLASS_NAME));
		}
	}
}
