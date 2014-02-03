package com.infinityappsolutions.webdesigner.server.project.tools;

public class Project {
	private long id;
	private String name;
	private long orgid;
	private ProjectType projectType;
	private String descriptor;// 'main', 'branch-'uid, etc

	public Project(long id, String name, long orgid, ProjectType projectType,
			String descriptor) {
		super();
		this.id = id;
		this.name = name;
		this.orgid = orgid;
		this.projectType = projectType;
		this.descriptor = descriptor;
	}

	public Project() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getOrgid() {
		return orgid;
	}

	public void setOrgid(long orgid) {
		this.orgid = orgid;
	}

	public ProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

}
