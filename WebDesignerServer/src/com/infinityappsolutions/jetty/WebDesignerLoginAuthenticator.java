package com.infinityappsolutions.jetty;

import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.UserIdentity;

public class WebDesignerLoginAuthenticator extends BasicAuthenticator {

	@Override
	public UserIdentity login(String username, Object password, ServletRequest request) {
		SecureHashUtil hashUtil = new SecureHashUtil();
		try {
			password = hashUtil.sha256Hash((String) password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserIdentity user = _loginService.login(username, password);
		if (user != null) {
			renewSession((HttpServletRequest) request, (request instanceof Request ? ((Request) request).getResponse() : null));
			return user;
		}
		return null;
	}

}
