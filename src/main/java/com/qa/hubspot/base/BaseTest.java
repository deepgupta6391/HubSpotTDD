package com.qa.hubspot.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.hubspot.pages.LoginPage;

public class BaseTest {

	public WebDriver driver;
	public BasePage basePage;
	public Properties prop;
	public LoginPage loginPage;

	@BeforeClass
	public void beforeClass() {
		System.out.println("Running before class----------BASE TEST");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("Running after class----------BASE TEST");
	}

	@BeforeTest
	@Parameters(value = { "browser" })
	public void setUp(@Optional String browser) {

		System.out.println("Running before test----------BASE TEST");
		String browserName = null;

		basePage = new BasePage();
		prop = basePage.init_prop();

		if (System.getProperty("browser") == null) {
			if (browser == null) {
				browserName = prop.getProperty("browser");
			} else {
				browserName = browser;
			}
		} else {
			browserName = System.getProperty("browser");
		}
		driver = basePage.init_driver(prop, browserName);
		loginPage = new LoginPage(driver);
	}

	@AfterTest
	public void tearDown() {
		System.out.println("running after test----------BASE TEST");
		driver.quit();
	}

}
