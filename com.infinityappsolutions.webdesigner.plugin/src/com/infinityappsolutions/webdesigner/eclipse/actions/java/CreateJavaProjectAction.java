package com.infinityappsolutions.webdesigner.eclipse.actions.java;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.JavaRuntime;

import com.infinityappsolutions.webdesigner.database.tools.DAOReader;
import com.infinityappsolutions.webdesigner.database.tools.DAOReader.DatabaseTable;
import com.infinityappsolutions.webdesigner.eclipse.actions.CreateProjectAction;
import com.infinityappsolutions.webdesigner.server.java.bean.tools.BeanGenerator;

public class CreateJavaProjectAction extends CreateProjectAction {

	public IProject createProject(String orgName, String repositoryName,
			String projectName, ArrayList<DatabaseTable> databaseTables) {

		// description.setNatureIds(new String[] { JavaCore.NATURE_ID });
		IProject generalProject = super.createProject(orgName, repositoryName,
				projectName);
		IJavaProject javaProject = JavaCore.create(generalProject);
		IClasspathEntry[] buildPath = {
				JavaCore.newSourceEntry(generalProject.getFullPath().append(
						"src")), JavaRuntime.getDefaultJREContainerEntry() };
		try {
			javaProject.setRawClasspath(buildPath, generalProject.getFullPath()
					.append("bin"), null);
			IFolder folder = generalProject.getFolder("src");
			folder.create(true, true, null);

			PackageGenerator generator = new PackageGenerator();

			String packageName = "com." + orgName.toLowerCase() + "."
					+ projectName.toLowerCase();
			// create package fragment
			IPackageFragment fragment = generator.addPackage(javaProject,
					packageName, folder);
			ClassGenerator classGenerator = new ClassGenerator(
					databaseTables.get(0), fragment);
			classGenerator.createClass(packageName);

		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return generalProject;
	}

	public void createBeansFromDatabase(DAOReader daoReader) {
		BeanGenerator beanGenerator = new BeanGenerator(
				"infinityappsolutions.com", "testproject");
		try {
			String beangen = beanGenerator.generateBean(daoReader
					.getDatabaseTables().get(0));
			System.out.println("++++++++++++++++++++++++");
			System.out.println(beangen);
			System.out.println("++++++++++++++++++++++++");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
