package org.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.utilites.CommonUtility;

public class ReadProperties {
	
	 private static Properties loadProperties() {
		 
	        Properties properties = new Properties();
	        try (FileInputStream input = new FileInputStream(CommonUtility.CONFIG_PROPERTIES_LOCATION)) {
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
