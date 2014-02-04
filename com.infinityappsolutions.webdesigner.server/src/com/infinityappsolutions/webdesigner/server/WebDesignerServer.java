package com.infinityappsolutions.webdesigner.server;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
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
			server = new Server(8080);
			WebAppContext webapp = new WebAppContext();
			webapp.setContextPath("/");
			Bundle bundle = org.eclipse.core.runtime.Platform
					.getBundle("com.infinityappsolutions.webdesigner.server");
			System.out.println("+++++++++++++++++++++++++++++++++++++++");
			String fileName = bundle.getLocation();
			File file = null;
			try {
				file = FileLocator.getBundleFile(bundle);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			webapp.setDefaultsDescriptor(file.getAbsolutePath()
					+ "/jetty/etc/webdefault.xml");
			// webapp.setDescriptor(file.getAbsolutePath()
			// + "/WebContent/WEB-INF/web.xml");
			webapp.setWar(file.getAbsolutePath() + "/WebContent");
			System.out.println("FilePATH: " + file.getAbsolutePath());
			System.out.println("jetty.home: " + file.getAbsolutePath());
			System.out.println("War: " + webapp.getWar());

			System.setProperty(Main.PROPERTY_JETTY_HOME, file.getAbsolutePath());
			System.setProperty(Main.PROPERTY_JETTY_BASE, file.getAbsolutePath());
			System.out.println("+++++++++++++++++++++++++++++++++++++++");

			HandlerList handlerList = new HandlerList();
			handlerList.addHandler(webapp);
			server.setHandler(webapp);
			try { 
				server.start();
				server.join();
				Handler handlers[] = server.getHandlers();
				for (Handler handler : handlers) {
					System.out.println("Handler name: " + handler);
				}
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

	public void parseXML() {
		// use jetty configurations
		// File file = new File(jetty_home + "/etc");
		// File[] children = file.listFiles();
		// if (children != null) {
		// for (File child : children) {
		// if (child.getName().endsWith(".xml")) {
		// System.out
		// .println("++++++++++++++++++++++++++++++++++++++++++++");
		// System.out.println("File name: " + child.getName()
		// + " exists: " + child.exists());
		// System.out
		// .println("++++++++++++++++++++++++++++++++++++++++++++");
		//
		// XmlConfiguration configuration = new XmlConfiguration(
		// new FileInputStream(child));
		// configuration.configure(server);
		// }
		// }
		// }
	}
}
