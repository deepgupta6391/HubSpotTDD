package com.qa.hubspot.tests;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ExcelUtil;

public class ContactsPageTest extends BaseTest {

	HomePage homePage;
	ContactsPage contactsPage;

	@BeforeClass
	public void contactsPageSetUp() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = homePage.gotoContactsPage();
	}

	@Test(priority = 1)
	public void verifyContactsPageTitleTest() {
		String title = contactsPage.getPageTitle();
		System.out.println("Contacts page title : " + title);
		Assert.assertEquals(title, Constants.CONTACTS_PAGE_TITLE);
	}

	@DataProvider
	public Object[][] getContactsTestData(Method m) {
		String sheetName=m.getName();
		System.out.println("Method name is : "+m);
		Object data[][] = ExcelUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
		return data;
	}

	@Test(priority = 2, dataProvider = "getContactsTestData")
	public void createContact(Hashtable<String, String> data) {
		contactsPage.createContact(data.get("email"), data.get("firstname"), data.get("lastname"), data.get("jobtitle"),
				data.get("phonenumber"), data.get("leadstatus"));
		Assert.assertEquals(contactsPage.checkIfEmailIsPresent(data.get("email")), data.get("email"));
	}

}
