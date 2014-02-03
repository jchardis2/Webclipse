package com.infinityappsolutions.webdesigner.server;

import org.apache.jasper.servlet.JspServlet;
import org.eclipse.jetty.deploy.DeploymentManager;
import org.eclipse.jetty.deploy.providers.WebAppProvider;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.webapp.WebAppContext;

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
				// testcode
				org.apache.jasper.servlet.JspServlet jspServlet = new JspServlet();
				// Create Server
				server = new Server(8080);

				// Get properties
				String jetty_home = System.getProperty("jetty.home", null);
				String jetty_base = System.getProperty("jetty.base", null);
				String jetty_resource_base = System.getProperty(
						Main.PROPERTY_JETTY_RESOURCE_BASE, null);

				// Create contexts and resources
				WebAppContext context = new WebAppContext();
				context.setWelcomeFiles(new String[] { "index.html" });
				context.setResourceBase(jetty_resource_base);
				context.setParentLoaderPriority(true);
				context.setContextPath("/WebDesigner");
				context.setDefaultsDescriptor(jetty_home
						+ "/etc/webdefault.xml");
				System.out
						.println("++++++++++++++++++++++++++++++++++++++++++++");
				System.out.println("Context path: " + context.getContextPath());
				System.out.println("Resource Base : "
						+ context.getResourceBase());
				System.out
						.println("++++++++++++++++++++++++++++++++++++++++++++");
				HandlerList handlers = new HandlerList();
				ResourceHandler resource_handler = new ResourceHandler();
				resource_handler.setDirectoriesListed(true);
				resource_handler.setResourceBase(jetty_resource_base);
				// handlers.setHandlers(new Handler[] { resource_handler, new
				// DefaultHandler() });
				handlers.setHandlers(new Handler[] { context, resource_handler,
						new DefaultHandler() });
				server.setHandler(handlers);
				server.start();
				System.out
						.println("++++++++++++++++++++++++++++++++++++++++++++");
				System.out.println("Server Started");
				// System.out.println("Server state: " + server.getState());
				System.out.println("Context path: " + context.getContextPath());
				System.out.println("Resource Base : "
						+ context.getResourceBase());
				System.out
						.println("++++++++++++++++++++++++++++++++++++++++++++");
				server.join();
				System.out
						.println("++++++++++++++++++++++++++++++++++++++++++++");
				System.out.println("Server Stopped");
				System.out
						.println("++++++++++++++++++++++++++++++++++++++++++++");
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
