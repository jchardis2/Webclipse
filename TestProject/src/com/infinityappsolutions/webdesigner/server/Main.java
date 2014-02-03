package com.infinityappsolutions.webdesigner.server;

public class Main {
	public static final String PROPERTY_JETTY_HOME = "jetty.home";
	public static final String PROPERTY_JETTY_BASE = "jetty.base";
	public static final String PROPERTY_JETTY_RESOURCE_BASE = "jetty.resource.base";

	public static void main(String args[]) {
		setSystemProperties();
		WebDesignerServer designerServer = WebDesignerServer.getInstance();
		designerServer.startServer();
	}

	public static void setSystemProperties() {
		System.setProperty(PROPERTY_JETTY_HOME,
				"/home/jchardis/ds/Jetty/jetty-distribution-9.1.1.v20140108");
		System.setProperty(PROPERTY_JETTY_BASE,
				"/home/jchardis/ds/Jetty/jetty-distribution-9.1.1.v20140108/demo-base");
		System.setProperty(
				PROPERTY_JETTY_RESOURCE_BASE,
				"/home/jchardis/git/Webclipse/com.infinityappsolutions.webdesigner.server/WebContent");
		System.setProperty("org.apache.jasper.compiler.disablejsr199", "true");
	}
}
