package org.palkar.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.config.ReadProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.palkar.pages.HomePage;
import org.palkar.pages.LoginPage;
import org.utilites.DriverUtillity;

public class LoginTest {

	LoginPage objLoginPage;
	ReadProperties objReadProperties;
	HomePage objHomePage;

	@BeforeEach
	public void SetUpBrowser() {
		objLoginPage = new LoginPage(DriverUtillity.initializeDriver());
		objReadProperties = new ReadProperties();
		objHomePage = new HomePage(DriverUtillity.initializeDriver());
	}

	@AfterEach
	public void QuitBrowser() {
		DriverUtillity.quit();
	}

	@Test
	public void Valid_Login_Credentials() {
		DriverUtillity.initializeDriver().get(objReadProperties.getProperty("url"));
		objLoginPage.LoginPageValidation(
				objReadProperties.getProperty("useremail"),
				objReadProperties.getProperty("userpassword"))
		.waitForValidationMessages();

		if (objLoginPage.emailBlank && objLoginPage.passBlank) {
			String validationMessage = objLoginPage.EmailAndPasswordBlankValidation();
			assertTrue(validationMessage.contains("Email can't be blank"));
			assertTrue(validationMessage.contains("Password can't be blank"));

		} else if (objLoginPage.emailBlank) {
			String validationMessage = objLoginPage.EmailBlankValidation();
			assertTrue(validationMessage.contains("Email can't be blank"));
		} else if (objLoginPage.passBlank) {
			String validationMessage = objLoginPage.PasswordBlankValidation();
			assertTrue(validationMessage.contains("Password can't be blank"));

		} else {
			if (!DriverUtillity.initializeDriver().findElements(objLoginPage.InvalidCredentialsMessage).isEmpty()) {
				String validationMessage = objLoginPage.InvalidCredentialsValidation();
				assertTrue(validationMessage.contains("Please check your credentials"));
			} else if (!DriverUtillity.initializeDriver().findElements(objLoginPage.LoggedInMessage).isEmpty()) {
				String validationMessage = objLoginPage.SuccessfullyLoggedInValidation();
				assertTrue(validationMessage.contains("You have successfully logged in"));
			}
		}
	}
}
