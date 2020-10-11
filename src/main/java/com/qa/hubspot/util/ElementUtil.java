package com.qa.hubspot.util;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

public class ElementUtil extends BasePage {

	WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public List<WebElement> getElements(By locator) {
		List<WebElement> elementsList = driver.findElements(locator);
		return elementsList;
	}

	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			System.out.println("locator is : " + locator);
			element = driver.findElement(locator);
			if (BasePage.highlight) {
				jsUtil.flash(element);
			}
			System.out.println("WebElement found successfully : " + locator);
		} catch (Exception e) {
			System.out.println("some exception got occurred with this locator : " + locator);
		}
		return element;
	}

	public void doSendKeys(By locator, String value) {
		waitForElementPresent(locator, 30);
		getElement(locator).sendKeys(value);
	}

	public void doClick(By locator) {
		waitForElementPresent(locator, 30);
		getElement(locator).click();
	}

	public String doGetText(By locator) {
		waitForElementPresent(locator, 30);
		return getElement(locator).getText().trim();
	}

	public boolean doIsDisplayed(By locator) {
		waitForElementPresent(locator, 30);
		return getElement(locator).isDisplayed();
	}

	public boolean doIsSelected(By locator) {
		waitForElementPresent(locator, 30);
		return getElement(locator).isSelected();
	}

	// ***************************Wait Utils***************************************

	public WebElement waitForElementPresent(By locator, int timeunit) {
		WebDriverWait wait = new WebDriverWait(driver, timeunit);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return element;
	}

	public WebElement waitForElementToBeClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public WebElement waitForElementToBeVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}

	public boolean waitForUrl(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.urlContains(url));
	}

	public Alert waitForAlertToBePresent(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		return alert;
	}

	public void clickWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	public String waitForTitleToBePresentAndGetTitle(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}
}
