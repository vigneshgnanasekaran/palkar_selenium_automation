package org.utilites;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverUtillity {

	private DriverUtillity() {
	}

	public static WebDriver driver = null;

	// Singleton pattern
	public static WebDriver initializeDriver() {

		if (driver == null) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			 
		}
		return driver;
	}

	public static void quit() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
