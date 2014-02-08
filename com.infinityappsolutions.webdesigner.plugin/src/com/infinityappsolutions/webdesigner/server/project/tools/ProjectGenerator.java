package com.infinityappsolutions.webdesigner.server.project.tools;

import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaCore;

import com.infinityappsolutions.webdesigner.eclipse.actions.java.BeanGenerator;
import com.infinityappsolutions.webdesigner.tools.database.DAOReader.DatabaseTable;

public class ProjectGenerator {

	public String projectDirectoryName;

	public ProjectGenerator(String projectDirectoryName) {
		this.projectDirectoryName = projectDirectoryName;
	}

	public void createProject() throws CoreException {
		createEclipseProject();
	}

	public void createProject(ArrayList<DatabaseTable> databaseTables) throws CoreException {
		createEclipseProject();
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

}
