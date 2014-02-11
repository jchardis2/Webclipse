package com.infinityappsolutions.webdesigner.views;

import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.infinityappsolutions.webdesigner.actions.CreateAccountAction;
import com.infinityappsolutions.webdesigner.beans.UserBean;
import com.infinityappsolutions.webdesigner.exceptions.DBException;
import com.infinityappsolutions.webdesigner.log.Logger;
import com.infinityappsolutions.webdesigner.security.SecureHashUtil;

@ViewScoped
@ManagedBean(name = "createAccountView")
public class CreateAccountView extends UserBean {
	private static final long serialVersionUID = -3082634691614062475L;
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
		SecureHashUtil hashUtil = new SecureHashUtil();
		try {
			password = hashUtil.sha256Hash((String) password);
			CreateAccountAction accountAction = new CreateAccountAction();
			accountAction.createAccount(this);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			Logger.getInstance().debug(e);
		}
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
