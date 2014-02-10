package com.infinityappsolutions.webdesigner.views;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.infinityappsolutions.webdesigner.actions.CreateAccountAction;
import com.infinityappsolutions.webdesigner.beans.UserBean;

@ViewScoped
@ManagedBean(name = "createAccountView")
public class CreateAccountView extends UserBean {
	private String email2;
	private String password2;

	public String createAccount() {
		if (email != null && email2 != null && !email.equals(email2)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email Validation:", "Email 1 should match Email 2"));
			return null;
		} else if (password != null && password2 != null && !password.equals(password2)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password Validation:", "Password 1 should match Password 2"));
			return null;
		}
		CreateAccountAction accountAction = new CreateAccountAction();
		accountAction.createAccount(this);
		return "error";

	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

}
