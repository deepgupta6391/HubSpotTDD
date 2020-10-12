package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.ElementUtil;

public class DealsPage extends BasePage{

	WebDriver driver;
	
	//OR:
	By dealsHeader=By.xpath("//span[@class='private-dropdown__item__label' and text()='Deals']");
	By createDealBtn=By.xpath("//span[text()='Create deal']");

	public DealsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil=new ElementUtil(driver);
	}
	
	
}
