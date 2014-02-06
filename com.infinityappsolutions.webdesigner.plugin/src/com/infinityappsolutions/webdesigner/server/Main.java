package com.infinityappsolutions.webdesigner.server;

public class Main {
	public static final String PROPERTY_JETTY_HOME = "jetty.home";
	public static final String PROPERTY_JETTY_BASE = "jetty.base";
	public static final String PROPERTY_JETTY_RESOURCE_BASE = "jetty.resource.base";
	public static final String PROPERTY_PROJECTS_HOME = "projects.home";

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

	public static String concatFilePaths(String paths[]) {
		String fullPath = "";
		for (String string : paths) {
			fullPath += string + getSystemFileSeperator();
		}
		if (!fullPath.equals("")) {
			return fullPath;
		}
		return null;
	}

	public static String getSystemFileSeperator() {
		return System.getProperty("file.separator");
	}

	// Key Meaning

	// "file.separator" Character that separates components of a file path. This
	// is "/" on UNIX and "\" on Windows.

	// "java.class.path" Path used to find directories and JAR archives
	// containing class files. Elements of the class path are separated by a
	// platform-specific character specified in the path.separator property.

	// "java.home" Installation directory for Java Runtime Environment (JRE)

	// "java.vendor" JRE vendor name

	// "java.vendor.url" JRE vendor URL

	// "java.version" JRE version number

	// "line.separator" Sequence used by operating system to separate lines in
	// text files

	// "os.arch" Operating system architecture

	// "os.name" Operating system name

	// "os.version" Operating system version
	// "path.separator" Path separator character used in java.class.path
	// "user.dir" User working directory
	// "user.home" User home directory
	// "user.name" User account name
}
