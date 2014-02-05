package com.infinityappsolutions.webdesigner.server;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.webapp.WebAppContext;
import org.osgi.framework.Bundle;

public class WebDesignerServer {
	private static WebDesignerServer webDesignerServer;
	private static Server server;

	public static void main(String args[]) {
		getInstance().startServer();
	}

	private WebDesignerServer() {

	}

	public static WebDesignerServer getInstance() {
		if (webDesignerServer == null) {
			webDesignerServer = new WebDesignerServer();
		}
		return webDesignerServer;
	}

	public void startServer() {
		Thread thread = new Thread(ServerRunnable);
		thread.start();
	}

	public Runnable ServerRunnable = new Runnable() {
		public void run() {
			try {
				server = new Server(8080);

				Bundle bundle = org.eclipse.core.runtime.Platform
						.getBundle("com.infinityappsolutions.webdesigner.plugin");
				File file = null;
				file = FileLocator.getBundleFile(bundle);

				// Set strings and system properties
				String filePath = file.getAbsolutePath();
				String webdefaultPath = filePath + "/etc/webdefault.xml";
				String jettyHome = filePath;
				String jettyBase = filePath;
				String warPath = jettyHome + "/WebContent";
				System.setProperty(Main.PROPERTY_JETTY_HOME, jettyHome);
				System.setProperty(Main.PROPERTY_JETTY_BASE, jettyBase);

				// Print debug statements
				System.out.println("+++++++++++++++++++++++++++++++++++++++");
				System.out.println("Jetty Home: " + jettyHome);
				System.out.println("Jetty Base: " + jettyHome);
				System.out.println("War: " + warPath);
				System.out.println("+++++++++++++++++++++++++++++++++++++++");

				// config webappcontext
				WebAppContext webapp = new WebAppContext();
				webapp.setContextPath("/");
				webapp.setDefaultsDescriptor(webdefaultPath);
				webapp.setWar(warPath);
				HandlerList handlerList = new HandlerList();
				handlerList.addHandler(webapp);
				server.setHandler(webapp);

				server.start();
				server.join();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

	public void stopServer() throws Exception {
		if (server != null && server.isRunning()) {
			server.stop();
		}
	}
}
