package com.infinityappsolutions.webdesigner.actions;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.infinityappsolutions.webdesigner.dao.DAOFactory;
import com.infinityappsolutions.webdesigner.dao.mysql.UserDAO;
import com.infinityappsolutions.webdesigner.exceptions.DBException;
import com.infinityappsolutions.webdesigner.views.CreateAccountView;

public class CreateAccountAction {

	public void createAccount(CreateAccountView createAccountView) {
		UserDAO dao = new UserDAO(DAOFactory.getProductionInstance());
		try {
			dao.addNewUser(createAccountView);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Account Creation Failed"));

		}

	}
}
