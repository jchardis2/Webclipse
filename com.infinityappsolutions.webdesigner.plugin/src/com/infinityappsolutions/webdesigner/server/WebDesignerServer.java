package com.infinityappsolutions.webdesigner.server;

import java.io.File;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.webapp.WebAppClassLoader;
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
				if (server != null
						&& (server.getState() == server.RUNNING || server
								.isStarted())) {
					server.stop();
				}
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
				System.setProperty("org.apache.jasper.compiler.disablejsr199",
						"true");
				System.setProperty(Main.PROPERTY_PROJECTS_HOME,
						"/home/jchardis/WebDesignerProjects");
				// Print debug statements
				System.out.println("+++++++++++++++++++++++++++++++++++++++");
				System.out.println("Jetty Home: " + jettyHome);
				System.out.println("Jetty Base: " + jettyHome);
				System.out.println("War: " + warPath);
				System.out.println("+++++++++++++++++++++++++++++++++++++++");

				// config webappcontext
				WebAppContext webAppContext = new WebAppContext();
				webAppContext.setContextPath("/");
				webAppContext.setDefaultsDescriptor(webdefaultPath);
				webAppContext.setWar(warPath);
				webAppContext.setClassLoader(new WebAppClassLoader(getClass()
						.getClassLoader(), webAppContext));
				HandlerList handlerList = new HandlerList();
				handlerList.addHandler(webAppContext);
				server.setHandler(webAppContext);
				Handler handlers[] = server.getHandlers();
				System.out.println("+++++++++++++++++++++++++++++++++++++++");
				for (Handler handler : handlers) {
					System.out.println("Handler: " + handler);
				}
				System.out.println("+++++++++++++++++++++++++++++++++++++++");
				server.start();
				server.join();
				handlers = server.getHandlers();
				System.out.println("+++++++++++++++++++++++++++++++++++++++");
				for (Handler handler : handlers) {
					System.out.println("Handler: " + handler);
				}
				System.out.println("+++++++++++++++++++++++++++++++++++++++");
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
