package org.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
	
	 private static Properties loadProperties() {
		 
	        Properties properties = new Properties();
	        try (FileInputStream input = new FileInputStream("/home/francium/eclipse-workspac/SeleniumAutomation/lib/src/test/resources/org/config/config.properties")) {
	            properties.load(input);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return properties;
	    }
	 
	 public String getProperty(String key) {
	        return loadProperties().getProperty(key);
	    }

}
