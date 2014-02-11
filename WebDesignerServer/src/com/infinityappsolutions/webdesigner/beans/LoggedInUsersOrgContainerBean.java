package com.infinityappsolutions.webdesigner.beans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "loggedInUsersOrgBean")
public class LoggedInUsersOrgContainerBean {
	private ArrayList<OrgUsersBean> orgUsersBeansList;
	private OrgUsersBean currentOrgUsersBean;

	public LoggedInUsersOrgContainerBean() {

	}

	public ArrayList<OrgUsersBean> getOrgUsersBeansList() {
		return orgUsersBeansList;
	}

	public void setOrgUsersBeansList(ArrayList<OrgUsersBean> orgUsersBeansList) {
		this.orgUsersBeansList = orgUsersBeansList;
	}

	public OrgUsersBean getCurrentOrgUsersBean() {
		return currentOrgUsersBean;
	}

	public void setCurrentOrgUsersBean(OrgUsersBean currentOrgUsersBean) {
		this.currentOrgUsersBean = currentOrgUsersBean;
	}

}
