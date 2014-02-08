package com.infinityappsolutions.jetty;

import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.JDBCLoginService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		// MBeanContainer mbContainer = new MBeanContainer(
		// ManagementFactory.getPlatformMBeanServer());
		// server.addBean(mbContainer);

		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath("/");
		webapp.setWar("./WebContent");
		HandlerList handlerList = new HandlerList();
		handlerList.addHandler(webapp);
		server.setHandler(webapp);

		// Realm
		HashLoginService hashLoginService = new HashLoginService("Realm", "/etc/realm.properties");
		JDBCLoginService jdbcLoginService = new JDBCLoginService("JDBCRealm", "/etc/jdbcRealm.properties");
		JAASLoginService
		
		server.start();
		Handler handlers[] = server.getHandlers();
		for (Handler handler : handlers) {
			System.out.println("Handler name: " + handler);
		}
		server.join();
		System.out.println("Server Stopped");

	}
}
