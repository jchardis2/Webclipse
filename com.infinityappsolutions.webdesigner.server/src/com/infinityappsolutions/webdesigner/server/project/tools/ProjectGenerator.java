package com.infinityappsolutions.webdesigner.server.project.tools;

import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaCore;

import com.infinityappsolutions.webdesigner.database.tools.DAOBean.DatabaseTable;
import com.infinityappsolutions.webdesigner.server.java.bean.tools.BeanGenerator;

public class ProjectGenerator {

	public String projectDirectoryName;

	public ProjectGenerator(String projectDirectoryName) {
		this.projectDirectoryName = projectDirectoryName;
	}

	public void createProject() {
		try {
			createEclipseProject();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// createBeans(databaseTables);
	}

	public void createProject(ArrayList<DatabaseTable> databaseTables) {
		try {
			createEclipseProject();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// createBeans(databaseTables);
	}

	private void createEclipseProject() throws CoreException {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = root.getProject(projectDirectoryName);
		
		project.create(null);
		project.open(null);
		IProjectDescription description = project.getDescription();
		description.setNatureIds(new String[] { JavaCore.NATURE_ID });
		project.setDescription(description, null);
	}

	protected void createBeans(ArrayList<DatabaseTable> databaseTables) {
		BeanGenerator beanGenerator = new BeanGenerator(
				"infinityappsolutions.com", "testproject");
		String beangen = beanGenerator.generateBean(databaseTables.get(0));
		System.out.println("beangen==================");
		System.out.println(beangen);
		System.out.println("beangen==================");
	}

}
