package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage extends BasePage{

	WebDriver driver;
	ElementUtil elementUtil;

	//OR(object repository)
	
	
	By noAccountText=By.xpath("//div[@class='signup-link']/i18n-string");
	
	By signUp=By.linkText("Sign up");
	
	By emailLabel=By.xpath("//label[@for='username']/i18n-string");
	
	By email=By.id("username");
	
	By passwordLabel=By.xpath("//label[@for='password']/i18n-string");
	
	By showOrHidePass=By.xpath("//small[@id='password-help']//span");
	
	By password=By.id("password");
	
	By forgotPassword=By.linkText("Forgot my password");
	
	By rememberMeChkBox=By.xpath("//i18n-string[text()='Remember me']/parent::span/preceding-sibling::span");
	
	By loginBtn=By.id("loginBtn");
	
	By googleBtn=By.xpath("//a[@class='google-sign-in']");

	By loginSSO=By.id("ssoBtn");
	
	By footer=By.xpath("//footer[@class='copyright']");


	//constructor:	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil=new ElementUtil(driver);
	}
	
	//page actions	
	@Step("get login page title....")
	public String getLoginPageTitle() {	
		return elementUtil.waitForTitleToBePresentAndGetTitle(Constants.LOGIN_PAGE_TITLE, 10);
	}
	
	@Step("get text for sign up....")
	public String getTextForCreateAccQue() {
		return elementUtil.getElement(noAccountText).getText();
	}
	
	@Step("check if the Sign Up link is getting displayed")
	public boolean checkSignUpLinkIsPresent() {
		return elementUtil.doIsDisplayed(signUp);
	}
	
	@Step("Check the email label")
	public String getEmailLabel() {
		return elementUtil.doGetText(emailLabel);
	}
	
	@Step("check the password label")
	public String getPasswordLabel() {
		return elementUtil.doGetText(passwordLabel);
	}
	
	@Step("check if show password link is getting displayed")
	public String getShowPassLinkText() {
		return elementUtil.doGetText(showOrHidePass);
	}
	
	@Step("check if hide password link is getting displayed")
	public String getkHidePassLinkText() {
		elementUtil.doClick(showOrHidePass);
		return elementUtil.doGetText(showOrHidePass);
	}
	
	@Step("check if forgot my password link is getting displayed")
	public boolean checkIfForgotMyPasLinkIsDisplayed() {
		return elementUtil.doIsDisplayed(forgotPassword);
	}
	
	@Step("check if remember me checkbox is unchecked")
	public boolean checkRememberMeCheckBoxIsUnchecked() {
		return elementUtil.doIsSelected(rememberMeChkBox);
	}
	
	@Step("check if Sign in with google button is getting displayed")
	public boolean checkIfGoogleSignInIsDisplayed() {
		return elementUtil.doIsDisplayed(googleBtn);
	}
	
	@Step("check if login with SSO button is getting displayed")
	public boolean checkIfSSOISGettingDisplayed() {
		return elementUtil.doIsDisplayed(loginSSO);
	}
	
	@Step("getting text from footer")
	public String getFooterText() {
		//return elementUtil.doGetText(footer);
		String text=elementUtil.doGetText(footer);
		return text.substring(0, text.indexOf("Privacy")-1);
	}
	
	@Step("Login in to the application using username and password")
	public HomePage doLogin(String username,String password) {
		elementUtil.doSendKeys(email,username );
		elementUtil.doSendKeys(this.password,password );
		elementUtil.doClick(loginBtn);
		
		return new HomePage(driver);
	}
	

}