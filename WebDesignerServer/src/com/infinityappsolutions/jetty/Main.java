package com.infinityappsolutions.jetty;

import java.util.Collections;

import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.JDBCLoginService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.util.security.Constraint;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		// MBeanContainer mbContainer = new MBeanContainer(
		// ManagementFactory.getPlatformMBeanServer());
		// server.addBean(mbContainer);

		// Realm
		// HashLoginService hashLoginService = new HashLoginService("Realm",
		// "etc/realm.properties");

		JDBCLoginService jdbcLoginService = new JDBCLoginService("JDBCRealm", "etc/jdbcRealm.properties");

		ConstraintSecurityHandler security = new ConstraintSecurityHandler();

		Constraint constraint = new Constraint();
		constraint.setName("auth");
		constraint.setAuthenticate(true);
		constraint.setRoles(new String[] { "user", "admin" });

		ConstraintMapping mapping = new ConstraintMapping();
		mapping.setPathSpec("/*");
		mapping.setConstraint(constraint);

		security.setConstraintMappings(Collections.singletonList(mapping));
		security.setAuthenticator(new WebDesignerLoginAuthenticator());
		security.setLoginService(jdbcLoginService);

		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/");
		webAppContext.setWar("./WebContent");
		// ************
		security.setHandler(webAppContext);

		// add Handlers
		HandlerList handlerList = new HandlerList();
		// handlerList.addHandler(webAppContext);
		handlerList.addHandler(security);
		handlerList.setStopTimeout(1);
		server.setHandler(handlerList);

		// JAASLoginService jaasLoginService = new
		// JAASLoginService("JAAS Realm");
		// jaasLoginService.setLoginModuleName("LoginModule");

		// server.addBean(jdbcLoginService);
		// http://www.eclipse.org/jetty/documentation/current/embedded-examples.html#embedded-secured-hello-handler
		server.start();
		Handler handlers[] = server.getHandlers();
		for (Handler handler : handlers) {
			System.out.println("Handler name: " + handler);
		}
		server.join();
		System.out.println("Server Stopped");

	}
}
