package com.infinityappsolutions.webdesigner.login;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jetty.security.ServerAuthException;
import org.eclipse.jetty.security.authentication.DeferredAuthentication;
import org.eclipse.jetty.security.authentication.FormAuthenticator;
import org.eclipse.jetty.security.authentication.SessionAuthentication;
import org.eclipse.jetty.server.Authentication;
import org.eclipse.jetty.server.UserIdentity;
import org.eclipse.jetty.util.URIUtil;

import com.infinityappsolutions.webdesigner.security.SecureHashUtil;

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
		// String url = ((HttpServletRequest) request).getRequestURI();
		// System.out.println(url);
		// Set<String> set = request.getParameterMap().keySet();
		SecureHashUtil hashUtil = new SecureHashUtil();
		try {
			password = hashUtil.sha256Hash((String) password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// UserIdentity user = _loginService.login(username, password);
		// if (user != null) {
		// renewSession((HttpServletRequest) request, (request instanceof
		// Request ? ((Request) request).getResponse() : null));
		// return user;
		// }
		// return null;
		return super.login(username, password, request);
	}

	@Override
	public Authentication validateRequest(ServletRequest req, ServletResponse res, boolean mandatory) throws ServerAuthException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(true);

		// Look for cached authentication
		Authentication authentication = (Authentication) session.getAttribute(SessionAuthentication.__J_AUTHENTICATED);
		if (isLoginOrErrorPage(URIUtil.addPaths(request.getServletPath(), request.getPathInfo())) && authentication != null) {
			try {
				response.sendRedirect("/user/home.xhtml");
				return new DeferredAuthentication(this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return super.validateRequest(request, response, mandatory);
	}
}
