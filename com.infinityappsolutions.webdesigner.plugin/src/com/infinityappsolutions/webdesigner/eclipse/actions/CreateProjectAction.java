package com.infinityappsolutions.webdesigner.eclipse.actions;

import java.sql.SQLException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.JavaCore;

import com.infinityappsolutions.webdesigner.database.tools.DAOReader;
import com.infinityappsolutions.webdesigner.server.Main;
import com.infinityappsolutions.webdesigner.server.java.bean.tools.BeanGenerator;

public class CreateProjectAction {

	public CreateProjectAction() {

	}

	public IProject createProject(String orgName, String repositoryName, String projectName) {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = root.getProject(projectName);
		IProjectDescription description = workspace.newProjectDescription(project.getName());
		description.setNatureIds(new String[] { JavaCore.NATURE_ID });
		Path path = new Path(System.getProperty(Main.PROPERTY_PROJECTS_HOME) + Main.getSystemFileSeperator() + orgName + Main.getSystemFileSeperator() + projectName);
		System.out.println("Path: " + path.toString());
		description.setLocation(path);
		try {
			project.create(description, null);
			project.open(null);
			return project;
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
