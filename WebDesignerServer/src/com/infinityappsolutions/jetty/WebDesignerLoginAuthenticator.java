package com.infinityappsolutions.jetty;

import java.security.NoSuchAlgorithmException;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.security.authentication.FormAuthenticator;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.UserIdentity;

public class WebDesignerLoginAuthenticator extends FormAuthenticator {

	public WebDesignerLoginAuthenticator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WebDesignerLoginAuthenticator(String login, String error, boolean dispatch) {
		super(login, error, dispatch);
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserIdentity login(String username, Object password, ServletRequest request) {
//		String url = ((HttpServletRequest) request).getRequestURI();
//		System.out.println(url);
//		Set<String> set = request.getParameterMap().keySet();
		SecureHashUtil hashUtil = new SecureHashUtil(); 
		try {
			password = hashUtil.sha256Hash((String) password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		UserIdentity user = _loginService.login(username, password);
//		if (user != null) {
//			renewSession((HttpServletRequest) request, (request instanceof Request ? ((Request) request).getResponse() : null));
//			return user;
//		}
//		return null;
		return super.login(username, password, request);
	}

}
