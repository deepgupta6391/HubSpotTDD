package com.qa.hubspot.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;

public class HomePage extends BasePage {

	WebDriver driver;

	// OR
	By header = By.xpath("//h1[@class='dashboard-selector__title']");

	By accountMenuLink = By.id("account-menu-container");
	By accountName = By.xpath("//a[@id='navAccount-current']/div[@class='navAccount-accountName']");
	By accountId = By.xpath("//a[@id='navAccount-current']/div[@class='navAccount-portalId']");

	By hubspotNavigationLinks = By.xpath("//div[@class='desktop-nav-left-container']//a[@class='primary-link']");

	By contactsPriLink = By.id("nav-primary-contacts-branch");
	By contactSecLink = By.id("nav-secondary-contacts");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getHomePageTitle() {
		return elementUtil.waitForTitleToBePresentAndGetTitle(Constants.HOME_PAGE_TITLE, 20);
	}

	public String getHomePageHeaderValue() {
		return elementUtil.doGetText(header);
	}

	public String getLoggedInUserName() {
		if (!elementUtil.doIsDisplayed(accountName)) {
			elementUtil.doClick(accountMenuLink);
		}
		return elementUtil.doGetText(accountName);
	}

	public String getLoggedInUserAccountID() {
		if (!elementUtil.doIsDisplayed(accountId)) {
			elementUtil.doClick(accountMenuLink);
		}
		return elementUtil.doGetText(accountId);
	}

	public List<String> getHubSpotNavigationLinksText() {
		List<WebElement> navigationLinks = elementUtil.getElements(hubspotNavigationLinks);
		List<String> navigationLinksText = new ArrayList<String>();
		for (int i = 1; i < navigationLinks.size(); i++) {
			navigationLinksText.add(navigationLinks.get(i).getAttribute("textContent").trim());
		}
		return navigationLinksText;
	}

	public void clickOnContacts() {
		elementUtil.doClick(contactsPriLink);
		elementUtil.doClick(contactSecLink);
	}

	public ContactsPage gotoContactsPage() {
		clickOnContacts();
		return new ContactsPage(driver);
	}

}
