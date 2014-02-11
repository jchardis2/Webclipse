package com.infinityappsolutions.webdesigner.beans;

public class OrgAdminBean extends OrgUsersBean {
	private boolean addUser;
	private boolean addAdmin;
	private boolean editAllProjects;

	public OrgAdminBean() {

	}

	public OrgAdminBean(Long orgid, Long userid, boolean createProjects, boolean deleteProjects, boolean editProjects, boolean addUser, boolean addAdmin, boolean editAllProjects) {
		super(orgid, userid, createProjects, deleteProjects, editProjects);
		this.addUser = addUser;
		this.addAdmin = addAdmin;
		this.editAllProjects = editAllProjects;
	}

	public boolean isAddUser() {
		return addUser;
	}

	public void setAddUser(boolean addUser) {
		this.addUser = addUser;
	}

	public boolean isAddAdmin() {
		return addAdmin;
	}

	public void setAddAdmin(boolean addAdmin) {
		this.addAdmin = addAdmin;
	}

	public boolean isEditAllProjects() {
		return editAllProjects;
	}

	public void setEditAllProjects(boolean editAllProjects) {
		this.editAllProjects = editAllProjects;
	}

}