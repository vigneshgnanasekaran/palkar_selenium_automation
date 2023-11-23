package org.utilites;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtility {
	
	    public static String CONFIG_DATA_LOCATION = "src/test/java/org/config/config.data/selenium_test_data.xlsx";  
	    public static String CONFIG_PROPERTIES_LOCATION = "src/test/java/org/config/config.properties";
		private WebDriverWait wait;
	     

	    public CommonUtility() {
	        this.wait = new WebDriverWait(DriverUtillity.initializeDriver(), Duration.ofSeconds(10));
	    }
	    
	    public void waitForConditions(ExpectedCondition<?>... conditions) {
	        wait.until(ExpectedConditions.or(conditions));
	    }

	     
	
}