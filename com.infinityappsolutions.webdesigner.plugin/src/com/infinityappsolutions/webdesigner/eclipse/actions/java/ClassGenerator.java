package com.infinityappsolutions.webdesigner.eclipse.actions.java;

import java.util.ArrayList;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;

import com.infinityappsolutions.webdesigner.database.tools.ColumnTypeMap;
import com.infinityappsolutions.webdesigner.database.tools.DAOReader.DatabaseColumn;
import com.infinityappsolutions.webdesigner.database.tools.DAOReader.DatabaseTable;

public class ClassGenerator {
	private IPackageFragment fragment;
	private DatabaseTable databaseTable;
	private ICompilationUnit cu;

	public ClassGenerator(DatabaseTable databaseTable, IPackageFragment fragment) {
		this.fragment = fragment;
		this.databaseTable = databaseTable;
	}

	public void createClass(String packageName) throws JavaModelException {
		// init code string and create compilation unit
		String str = "package " + fragment.getElementName() + ";\n"
				+ "public class " + databaseTable.getName() + "  {" + "\n"
				+ "}";

		cu = fragment.createCompilationUnit(databaseTable.getName() + ".java",
				str, false, null);
		createFields();
	}

	private void createFields() throws JavaModelException {
		ColumnTypeMap columnTypeMap = new ColumnTypeMap();
		for (DatabaseColumn databaseColumn : databaseTable.getColumns()) {
			Class<?> newCLass = columnTypeMap
					.getClass(databaseColumn.getType());
			// create a field
			IType type = cu.getType(databaseTable.getName());
			String field = "private " + newCLass.getName()
					+ databaseColumn.getName() + ";";
			System.out.println("Creating Field: " + field);
			type.createField(field, null, true, null);
		}
	}
}
