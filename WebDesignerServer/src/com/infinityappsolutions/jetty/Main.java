package com.infinityappsolutions.jetty;

import org.eclipse.jetty.jaas.JAASLoginService;
import org.eclipse.jetty.jaas.JAASUserPrincipal;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.JDBCLoginService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.webapp.WebAppContext;

import com.infinityappsolutions.webdesigner.server.tools.role.LoginRoleCheckPolicy;

public class Main {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		// MBeanContainer mbContainer = new MBeanContainer(
		// ManagementFactory.getPlatformMBeanServer());
		// server.addBean(mbContainer);

		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/");
		webAppContext.setWar("./WebContent");
		HandlerList handlerList = new HandlerList();
		handlerList.addHandler(webAppContext);
		server.setHandler(webAppContext);

		// Realm
		HashLoginService hashLoginService = new HashLoginService("Realm", "/etc/realm.properties");
		JDBCLoginService jdbcLoginService = new JDBCLoginService("JDBCRealm", "/etc/jdbcRealm.properties");

		JAASLoginService jaasLoginService = new JAASLoginService("JAAS Realm");
		jaasLoginService.setLoginModuleName("LoginModule");

		server.addBean(jdbcLoginService);
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
