package org.palkar.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.utilites.CommonUtility;

public class LoginPage {

	private WebDriver driver;

	private By LoginEmailTab = By.id("login_email");
	private By LoginPasswordTab = By.id("login_password");
	private By LoginCheckbox = By.xpath("//label[@class=\"custom-control-label\"]");
	private By LoginButton = By.xpath("//button[contains(text(), ' Log')]");

	public By LoggedInMessage = By.xpath("//div[contains(., 'You have successfully logged in')]");
	public By InvalidCredentialsMessage = By.xpath("//div[contains(., 'Please check your credentials')]");

	private By EmailCannotBlankMessage = By.xpath("//div[@class='invalid-feedback' and contains(., \"Email can't be blank\")]");
	private By PassCannotBlankMessage = By.xpath("//div[@class='invalid-feedback' and contains(., \"Password can't be blank\")]");

	public boolean emailBlank;
	public boolean passBlank;
	CommonUtility objCommonUtility = new CommonUtility();

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage LoginPageValidation(String UserEmailId, String UserPassword) {
		driver.findElement(LoginEmailTab).sendKeys(UserEmailId);
		driver.findElement(LoginPasswordTab).sendKeys(UserPassword);
		driver.findElement(LoginCheckbox).click();
		driver.findElement(LoginButton).click();
		return this;
	}

	public void waitForValidationMessages() {
		objCommonUtility.presenceOfElementLocated(InvalidCredentialsMessage
				,LoggedInMessage
				,EmailCannotBlankMessage
				,PassCannotBlankMessage);
		  
		

		emailBlank = !driver.findElements(EmailCannotBlankMessage).isEmpty();
		passBlank = !driver.findElements(PassCannotBlankMessage).isEmpty();
	}

	public String EmailAndPasswordBlankValidation() {
		String emailCannotBlankMessage = driver.findElement(EmailCannotBlankMessage).getText();
		String passCannotBlankMessage = driver.findElement(PassCannotBlankMessage).getText();
		System.out.println(emailCannotBlankMessage);
		System.out.println(passCannotBlankMessage);
		return emailCannotBlankMessage + " " + passCannotBlankMessage;
	}

	public String EmailBlankValidation() {
		String emailCannotBlankMessage = driver.findElement(EmailCannotBlankMessage).getText();
		System.out.println(emailCannotBlankMessage + " EMAIL CANNOT BE BLANK");
		return emailCannotBlankMessage ;
	}

	public String PasswordBlankValidation() {
		String passCannotBlankMessage = driver.findElement(PassCannotBlankMessage).getText();
		System.out.println(passCannotBlankMessage + " PASSWORD CANNOT BE BLANK");
		return passCannotBlankMessage;
	}

	public String InvalidCredentialsValidation() {
		String invalidCredentialsMessage = driver.findElement(InvalidCredentialsMessage).getText();
		System.out.println(invalidCredentialsMessage + " INVALID CREDENTIALS");
		return invalidCredentialsMessage ;
	}

	public String SuccessfullyLoggedInValidation() {
		String loggedInMessage = driver.findElement(LoggedInMessage).getText();
		System.out.println(loggedInMessage + " SUCCESSFULLY LOGGED IN");
		return loggedInMessage;
	}
}





/*
 * new WebDriverWait(driver,
 * Duration.ofSeconds(20)).until(ExpectedConditions.or(
 * ExpectedConditions.presenceOfElementLocated(InvalidCredentialsMessage),
 * ExpectedConditions.presenceOfElementLocated(LoggedInMessage),
 * ExpectedConditions.presenceOfElementLocated(EmailCannotBlankMessage),
 * ExpectedConditions.presenceOfElementLocated(PassCannotBlankMessage)));
 * 
 */


