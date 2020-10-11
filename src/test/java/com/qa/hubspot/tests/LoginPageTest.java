package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.util.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	@Description("verifying login page title")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoginPageTitle() {
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, "Login page title did not match...");
	}

	@Test(priority = 3, enabled = true)
	@Description("verify all the text of login page")
	@Severity(SeverityLevel.MINOR)
	public void verifyTextOfLoginPageTest() {
		SoftAssert softAssert = new SoftAssert();

		String text = loginPage.getTextForCreateAccQue();
		softAssert.assertEquals(text, Constants.NO_ACC_QUE_TEXT, "Text is not matching");

		String emailLabel = loginPage.getEmailLabel();
		softAssert.assertEquals(emailLabel, Constants.EMAIL_LABEL);

		String pwdLabel = loginPage.getPasswordLabel();
		softAssert.assertEquals(pwdLabel, Constants.PASSWORD_LABEL);

		String showPwd = loginPage.getShowPassLinkText();
		softAssert.assertEquals(showPwd, Constants.SHOW_PASS);

		String hidePwd = loginPage.getkHidePassLinkText();
		softAssert.assertEquals(hidePwd, Constants.HIDE_PASS);

		String footerText = loginPage.getFooterText();
		System.out.println("Footer text is : " + footerText);
		softAssert.assertEquals(footerText, Constants.FOOTER_TEXT);

		softAssert.assertAll();
	}

	@Test(priority = 2, enabled = true)
	@Description("verify all the links of the login page")
	@Severity(SeverityLevel.BLOCKER)
	public void verifyIfLinkTextIsDisplayedInLoginPageTest() {

		SoftAssert softAssert = new SoftAssert();

		softAssert.assertEquals(loginPage.checkSignUpLinkIsPresent(), true);

		softAssert.assertEquals(loginPage.checkIfForgotMyPasLinkIsDisplayed(), true);

		softAssert.assertEquals(loginPage.checkRememberMeCheckBoxIsUnchecked(), false);

		softAssert.assertEquals(loginPage.checkIfGoogleSignInIsDisplayed(), true);

		softAssert.assertEquals(loginPage.checkIfSSOISGettingDisplayed(), true);

		softAssert.assertAll();
	}

	@Test(priority = 4, enabled = true)
	@Description("login to the page")
	@Severity(SeverityLevel.CRITICAL)
	public void loginTest() {
		HomePage homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		// Assert.assertEquals(homePage.getHomePageHeaderValue(),
		// Constants.HOME_PAGE_HEADER);
	}

}
