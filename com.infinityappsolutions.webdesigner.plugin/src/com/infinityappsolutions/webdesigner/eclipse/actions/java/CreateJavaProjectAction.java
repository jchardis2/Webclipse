package com.infinityappsolutions.webdesigner.eclipse.actions.java;

import java.sql.SQLException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.JavaRuntime;

import com.infinityappsolutions.webdesigner.database.tools.DAOReader;
import com.infinityappsolutions.webdesigner.eclipse.actions.CreateProjectAction;
import com.infinityappsolutions.webdesigner.server.java.bean.tools.BeanGenerator;

public class CreateJavaProjectAction extends CreateProjectAction {

	public IProject createProject(String orgName, String repositoryName, String projectName) {
		// description.setNatureIds(new String[] { JavaCore.NATURE_ID });
		IProject generalProject = super.createProject(orgName, repositoryName, projectName);
		IJavaProject javaProject = JavaCore.create(generalProject);
		IClasspathEntry[] buildPath = { JavaCore.newSourceEntry(generalProject.getFullPath().append("src")), JavaRuntime.getDefaultJREContainerEntry() };
		try {
			javaProject.setRawClasspath(buildPath, generalProject.getFullPath().append("bin"), null);
			IFolder folder = generalProject.getFolder("src");
			folder.create(true, true, null);

			PackageGenerator generator = new PackageGenerator();

			String packageName = "com." + orgName.toLowerCase() + projectName.toLowerCase();
			// create package fragment
			IPackageFragment fragment = generator.addPackage(javaProject, packageName, folder);

			// init code string and create compilation unit
			String str = "package " + packageName + ";\n" + "public class Test  {" + "\n" + " private String name;" + "\n" + "}";

			ICompilationUnit cu = fragment.createCompilationUnit("Test.java", str, false, null);

			// create a field
			IType type = cu.getType("Test");

			type.createField("private String age;", null, true, null);

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
		BeanGenerator beanGenerator = new BeanGenerator("infinityappsolutions.com", "testproject");
		try {
			String beangen = beanGenerator.generateBean(daoReader.getDatabaseTables().get(0));
			System.out.println("++++++++++++++++++++++++");
			System.out.println(beangen);
			System.out.println("++++++++++++++++++++++++");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
