package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.JavaScriptUtil;
import com.qa.hubspot.util.LocatorUtil;

public class ContactsPage extends BasePage {

	WebDriver driver;
	JavaScriptUtil jsUtil;
	String emailXpathStart = "//a[text()='";

	// OR:
	By contactsHeader = By.xpath("//span[@class='private-dropdown__item__label']");
	By createContactBtn = By.xpath("(//span[text()='Create contact'])[1]");

	// *******************Locators of create account form*************************
	By email = By.xpath("//input[@data-field='email']");
	By firstName = By.xpath("//input[@data-field='firstname']");
	By lastName = By.xpath("//input[@data-field='lastname']");
	By msgIfEmailAlreadyPresent = By.xpath("//div[@class='text-center m-x-6']/i18n-string");
	By jobTitle = By.xpath("//textarea[@data-field='jobtitle']");
	By phoneNumber = By.xpath("//textarea[@data-field='phone']");
	By leadStatus = By.xpath(
			"(//span[@class='private-typeahead-result private-typeahead-result--selectable'])[last()]/parent::span");
	By leadStatusSearch = By.xpath("(//input[@placeholder='Search'])[last()]");
	By formCreateContactBtn = By.xpath("(//span[text()='Create contact'])[last()]");

	// ***********Locators of account created confirmation*************************
	By contactsLinkOfConfPage = By.xpath("(//i18n-string[text()='Contacts'])[last()]");

	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		jsUtil=new JavaScriptUtil(driver);
	}

	public String getPageTitle() {
		return elementUtil.waitForTitleToBePresentAndGetTitle(Constants.CONTACTS_PAGE_TITLE, 15);
	}

	public String verifyPageHeader() {
		return elementUtil.doGetText(contactsHeader);
	}

	public void createContact(String email, String firstName, String lastName, String jobTitle, String phoneNumber,
			String leadStatusVal) {
		elementUtil.clickWhenReady(createContactBtn, 15);

		// enter data in form
		elementUtil.waitForElementPresent(this.email, 10);
		elementUtil.doSendKeys(this.email, email);
		elementUtil.doSendKeys(this.firstName, firstName);
		elementUtil.doSendKeys(this.lastName, lastName);
		elementUtil.doSendKeys(this.jobTitle, jobTitle);
		elementUtil.doSendKeys(this.phoneNumber, phoneNumber);
		elementUtil.doClick(this.leadStatus);
		elementUtil.waitForElementPresent(leadStatusSearch, 15);
		elementUtil.doSendKeys(leadStatusSearch, leadStatusVal);
		elementUtil.doSendKeys(leadStatusSearch, leadStatusVal);
		elementUtil.doClick(formCreateContactBtn);

		elementUtil.waitForElementPresent(contactsLinkOfConfPage, 20);
		elementUtil.doClick(contactsLinkOfConfPage);
	}

	public String checkIfEmailIsPresent(String emailValue) {
		By validateEmailPresent = LocatorUtil.getByLocatorUsingXpath(emailXpathStart, emailValue);
		jsUtil.refreshBrowserByJS();
		jsUtil.checkPageIsReady();
		elementUtil.waitForElementPresent(validateEmailPresent, 20);
		return elementUtil.doGetText(validateEmailPresent);
	}
}
