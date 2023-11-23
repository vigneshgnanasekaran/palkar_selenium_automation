package org.palkar.pages;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.log.LogEntry;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.utilites.CommonUtility;
import org.utilites.ExcelReader;

public class LoginPage {

	private WebDriver driver;

	private By LoginEmailTab = By.id("login_email");
	private By LoginPasswordTab = By.id("login_password");
	private By LoginCheckbox = By.xpath("//label[@class=\"custom-control-label\"]");
	private By LoginButton = By.xpath("//button[contains(text(), ' Log')]");

	public By LoggedInMessage = By.xpath("//div[contains(text(), 'You have successfully logged in')]");
	public By InvalidCredentialsMessage = By.xpath("//div[contains(., 'Please check your credentials')]");

	private By EmailCannotBlankMessage = By
			.xpath("//div[@class='invalid-feedback' and contains(., \"Email can't be blank\")]");
	private By PassCannotBlankMessage = By
			.xpath("//div[@class='invalid-feedback' and contains(., \"Password can't be blank\")]");
	public By InvalidEmailIdFormat = By
			.xpath("//div[@class='invalid-feedback' and contains(., \"Please provide a valid email address\")]");
	public boolean emailBlank;
	public boolean passBlank;
	public boolean invalidEmailId;
	public boolean loggedInMessage;
	public boolean invalidCredentialsMessage;
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
		objCommonUtility.waitForConditions(ExpectedConditions.presenceOfElementLocated(EmailCannotBlankMessage),
				ExpectedConditions.presenceOfElementLocated(PassCannotBlankMessage),
				ExpectedConditions.presenceOfElementLocated(LoggedInMessage),
				ExpectedConditions.presenceOfElementLocated(InvalidCredentialsMessage),
				ExpectedConditions.presenceOfElementLocated(InvalidEmailIdFormat));
		emailBlank = !driver.findElements(EmailCannotBlankMessage).isEmpty();
		passBlank = !driver.findElements(PassCannotBlankMessage).isEmpty();
		invalidEmailId = !driver.findElements(InvalidEmailIdFormat).isEmpty();
		loggedInMessage = !driver.findElements(LoggedInMessage).isEmpty();
		invalidCredentialsMessage = !driver.findElements(InvalidCredentialsMessage).isEmpty();
	}

	public String EmailAndPasswordBlankValidation() {
		String emailCannotBlankMessage = driver.findElement(EmailCannotBlankMessage).getText();
		String passCannotBlankMessage = driver.findElement(PassCannotBlankMessage).getText();
		printAssertionMessage(emailCannotBlankMessage.contains("Email can't be blank"),
				"Email can't be blank. Message Founded");
		printAssertionMessage(passCannotBlankMessage.contains("Password can't be blank"),
				"Password can't be blank. Message Founded");
		return emailCannotBlankMessage + " " + passCannotBlankMessage;
	}

	public String InvalidEmailIdAndFormatValidation() {
		String invalidEmailIdFormat = driver.findElement(InvalidEmailIdFormat).getText();
		String emailCannotBlankMessage = driver.findElement(EmailCannotBlankMessage).getText();
		printAssertionMessage(invalidEmailIdFormat.contains("Please provide a valid email address"),
				"Please provide a valid email address. Message Founded");
		printAssertionMessage(emailCannotBlankMessage.contains("Email can't be blank"),
				"Email can't be blank. Message Founded");
		return emailCannotBlankMessage + " " + invalidEmailIdFormat;
	}

	public String EmailBlankValidation() {
		String emailCannotBlankMessage = driver.findElement(EmailCannotBlankMessage).getText();
		printAssertionMessage(emailCannotBlankMessage.contains("Email can't be blank"),
				"Email can't be blank. Message Founded");
		return emailCannotBlankMessage;
	}

	public String PasswordBlankValidation() {
		String passCannotBlankMessage = driver.findElement(PassCannotBlankMessage).getText();
		printAssertionMessage(passCannotBlankMessage.contains("Password can't be blank"),
				"Password can't be blank. Message Founded");
		return passCannotBlankMessage;
	}

	public void CheckLoginStatus() {
		if (loggedInMessage) {
			printAssertionMessage(true, "You have successfully logged in. Message Founded");
		} else if (invalidCredentialsMessage) {
			printAssertionMessage(true, "Please check your credentials. Message Founded");
		}
	}

	public void printAssertionMessage(boolean condition, String message) {
		System.out.println(message);
		assertTrue(condition, message);
	}

	static List<Object[]> getUserCredentials() throws IOException {
		ExcelReader excelReader = new ExcelReader();
		List<ExcelReader.UserCredentials> userCredentialsList = excelReader
				.readTestData(CommonUtility.CONFIG_DATA_LOCATION, "login_credentials");

		List<Object[]> testData = new ArrayList<>();
		for (ExcelReader.UserCredentials userCredentials : userCredentialsList) {
			testData.add(new Object[] { userCredentials.getUsername(), userCredentials.getPassword() });
		}
		return testData;
	}
}
