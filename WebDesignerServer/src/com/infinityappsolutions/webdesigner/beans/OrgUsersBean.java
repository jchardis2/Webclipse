package com.infinityappsolutions.webdesigner.beans;

public class OrgUsersBean {

	private Long orgid;
	private Long userid;
	private boolean createProjects;
	private boolean deleteProjects;
	private boolean editProjects;

	public OrgUsersBean() {
	}

	public OrgUsersBean(Long orgid, Long userid, boolean createProjects, boolean deleteProjects, boolean editProjects) {
		super();
		this.orgid = orgid;
		this.userid = userid;
		this.createProjects = createProjects;
		this.deleteProjects = deleteProjects;
		this.editProjects = editProjects;
	}

	public boolean isCreateProjects() {
		return createProjects;
	}

	public void setCreateProjects(boolean createProjects) {
		this.createProjects = createProjects;
	}

	public boolean isDeleteProjects() {
		return deleteProjects;
	}

	public void setDeleteProjects(boolean deleteProjects) {
		this.deleteProjects = deleteProjects;
	}

	public boolean isEditProjects() {
		return editProjects;
	}

	public void setEditProjects(boolean editProjects) {
		this.editProjects = editProjects;
	}

	public Long getOrgid() {
		return orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
}