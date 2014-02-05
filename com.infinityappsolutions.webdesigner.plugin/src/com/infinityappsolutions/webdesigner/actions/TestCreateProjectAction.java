package com.infinityappsolutions.webdesigner.actions;

import org.eclipse.core.runtime.CoreException;

import com.infinityappsolutions.webdesigner.server.project.tools.ProjectGenerator;

public class TestCreateProjectAction {

	public TestCreateProjectAction() {

	}

	public void createProject() {
		System.out.println("Test WOrks");
		ProjectGenerator generator = new ProjectGenerator("Test2");
		try {
			generator.createProject();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
