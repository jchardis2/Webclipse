package com.infinityappsolutions.webdesigner.server.java.bean.tools;

import java.util.ArrayList;
import java.util.HashMap;

import com.infinityappsolutions.webdesigner.database.tools.ColumnTypeMap;
import com.infinityappsolutions.webdesigner.database.tools.DAOReader.DatabaseColumn;
import com.infinityappsolutions.webdesigner.database.tools.DAOReader.DatabaseTable;
import com.infinityappsolutions.webdesigner.tools.FileTools;

public class BeanGenerator {
	public static final String TYPE_BEAN = "Bean";
	public String fileContents;
	public String orgName;
	public String projectName;

	public StringBuffer imports;
	public HashMap<Class<?>, String> importMap;
	public HashMap<String, Class<?>> fields;
	private String newline = FileTools.getNewLine();

	public BeanGenerator(String orgName, String projectName) {
		this.orgName = orgName;
		this.projectName = projectName;
		importMap = new HashMap<Class<?>, String>();
		fields = new HashMap<String, Class<?>>();
	}

	public String generateBean(DatabaseTable databaseTable) {
		String newline = FileTools.getNewLine();
		StringBuffer buffer = new StringBuffer();

		String packageInfo = "package com." + orgName + "." + projectName
				+ ".beans;" + FileTools.getNewLine();

		ArrayList<DatabaseColumn> columns = databaseTable.getColumns();
		ColumnTypeMap columnTypeMap = new ColumnTypeMap();
		for (DatabaseColumn databaseColumn : columns) {
			Class<?> newCLass = columnTypeMap
					.getClass(databaseColumn.getType());
			addImport(newCLass, newCLass.getPackage().toString());
			fields.put(databaseColumn.getName(), newCLass);
		}

		// Add the class start
		String classStart = generateClassStart(databaseTable.getName());
		String fieldString = generateFields();
		String constructors = generateConstructors(databaseTable.getName());

		return packageInfo + classStart + fieldString + constructors;
	}

	public String generateClassStart(String tableName) {
		char dcan[] = tableName.toCharArray();
		dcan[0] = Character.toUpperCase(dcan[0]);
		return "public class " + new String(dcan) + "{"
				+ FileTools.getNewLine();
	}

	public String generateFields() {
		String fieldsString = "";
		for (String field : fields.keySet()) {
			fieldsString += "private " + fields.get(field).getName() + " "
					+ field + ";" + newline;
		}
		return fieldsString;
	}

	public String generateConstructors(String tableName) {
		String constructors = "public " + tableName + "(";
		String tempString = constructors + "){" + newline + "}";
		;
		String tempSetters = "";
		int count = 0;
		for (String field : fields.keySet()) {
			constructors += fields.get(field).getName() + " " + field;
			if (count < fields.keySet().size()) {
				constructors += ", ";
			}
			tempSetters += "this." + field + " = " + field + ";" + newline;
		}
		constructors += "){" + newline + tempSetters + "}";
		constructors += tempString;
		return constructors;

	}

	private void addImport(Class<?> _class, String classPackageName) {
		importMap.put(_class, classPackageName);
	}
}
