package com.qa.hubspot.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;

public class LocatorUtil extends BasePage{

	
	public static By getByLocatorUsingXpath(String xpathStart,String value) {
		String resultXpath=xpathStart+value+"']";
		return By.xpath(resultXpath);
	}
}
