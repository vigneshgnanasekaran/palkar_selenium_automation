package org.utilites;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.node.BooleanNode;

public class CommonUtility {
	public void presenceOfElementLocated(By locator) {
		 WebDriverWait wait = new WebDriverWait(DriverUtillity.initializeDriver(), Duration.ofSeconds(20));
		 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		 
	}

}
