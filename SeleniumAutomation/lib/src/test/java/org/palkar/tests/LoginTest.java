package org.palkar.tests;

import org.config.ReadProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
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

	@ParameterizedTest
	@MethodSource("org.palkar.pages.LoginPage#getUserCredentials")
	public void Valid_Login_Credentials(String username, String password) {
		DriverUtillity.initializeDriver().get(objReadProperties.getProperty("url"));
		objLoginPage.LoginPageValidation(username, password).waitForValidationMessages();
		objLoginPage.handleValidation();

	}
}
