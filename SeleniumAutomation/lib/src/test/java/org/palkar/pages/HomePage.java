package org.palkar.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	WebDriver driver;
	
	By LoggedInMessage = By.xpath("//div[contains(., 'You have successfully logged in.')]");
	public HomePage(WebDriver driver) {
		 this.driver = driver;
	}
	
	public String  GetLoggedInMessage() {
		new WebDriverWait(driver,  Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated(LoggedInMessage));
		return driver.findElement(LoggedInMessage).getText();
		 
	}
}
