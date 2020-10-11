package com.qa.hubspot.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.util.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class HomePageTest extends BaseTest {

	HomePage homePage;

	@BeforeClass
	public void homePageSetUp() {
		System.out.println("Running before class----------HOME PAGE TEST");
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	@Description("verify the home page title")
	@Severity(SeverityLevel.MINOR)
	public void verifyHomePageTitleTest() {
		String title = homePage.getHomePageTitle();
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
	}

	@Test(priority = 2)
	@Description("verify the header of the home page")
	@Severity(SeverityLevel.MINOR)
	public void verifyHomePageHeaderTest() {
		String header = homePage.getHomePageHeaderValue();
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);
	}

	@Test(priority = 4)
	@Description("verify loggedin username and user id")
	@Severity(SeverityLevel.NORMAL)
	public void verifyHomePageLoggedInUserNameAndUserIDTest() {
		SoftAssert softAssert = new SoftAssert();

		String userName = homePage.getLoggedInUserName();
		System.out.println("UserName is : "+userName);
		softAssert.assertEquals(userName, prop.getProperty("accountName"));

		String userID = homePage.getLoggedInUserAccountID();
		System.out.println("Account ID is : "+userID);
		softAssert.assertEquals(userID, prop.getProperty("accountID"));
		
		softAssert.assertAll();
	}
	
	@Test(priority = 3)
	@Description("verify hubspot navigation links text")
	public void verifyHeaderNavigationLinks() {
		List<String> textList=homePage.getHubSpotNavigationLinksText();
		System.out.println(textList);
		Assert.assertEquals(textList, Constants.HUBSPOT_NAVIGATION_LINKS);
	}

}
