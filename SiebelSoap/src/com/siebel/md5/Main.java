package com.siebel.md5;
import java.io.File;

import com.google.inject.servlet.GuiceFilter;
import com.siebel.md5.bindings.GuiceServletInjector;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	 final static Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws Exception {
    	File f = new File("config.properties");
    	if(!f.exists() && !f.isDirectory()) {
        	CreateDefaultConfigurationFile.createConfiguration();
    	}
    	String portString=ReadConfigurationFile.getConfiguration();
    	String version = System.getProperty("java.version");
    	System.out.println("Java Version :"+version);
    	int port=9990;
    	try{
    		port=Integer.parseInt(portString);
    	}catch(Exception e){
    		logger.error("Port Number Should be valid string, Server will start on port 9999");
    	}
        Server server = new Server(port);
        ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/");
        servletContextHandler.addEventListener(new GuiceServletInjector());
        servletContextHandler.addFilter(GuiceFilter.class, "/*", null);
        servletContextHandler.addServlet(DefaultServlet.class, "/");

        server.start();
        System.out.println("Server started on port :"+port);
/*        System.out.println("Press any key to stop server...");
        System.in.read();
        System.out.println("Stopping...");
        server.stop();
        server.join();*/
    }
}