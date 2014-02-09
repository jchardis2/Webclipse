package com.infinityappsolutions.webdesigner.beans;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@SessionScoped
@ManagedBean(name = "loginBean")
public class LoginBean {
	private Long id;
	private String username;
	private String email;
	private String password;

	public LoginBean() {

	}

	public LoginBean(Long id, String username, String email, String password) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		try {
			request.login(this.username, this.password);
			context.getExternalContext().redirect("/user/home.xhtml");
			System.out.println("Sent Redirect");
			// TODO Auto-generated catch block
			return "home.xhtml?faces-redirect=true";
		} catch (ServletException e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Login failed."));
			try {
				context.getExternalContext().redirect("/login-error.xhtml");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return "error";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return "error";
		}
	}

	public void logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		try {
			request.logout();
			context.getExternalContext().redirect("/login.xhtml");
		} catch (ServletException e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Logout failed."));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Failed to redirect."));
		}
	}
}
