package com.qa.hubspot.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hubspot.base.BasePage;

public class JavaScriptUtil extends BasePage {

	WebDriver driver;

	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
	}

	public void flash(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 10; i++) {
			changeColor("rgb(0,200,0)", element);
			changeColor(bgcolor, element);
		}
	}

	public void changeColor(String color, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.backgroundColor='" + color + "'", element);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void drawBorder(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	public String getTitleJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String title = js.executeScript("return document.title;").toString();
		return title;
	}

	public String getPageInnerText() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String pageText = js.executeScript("return document.documentElement.innerText;").toString();
		return pageText;
	}

	public void clickElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments.click();", element);
	}

	public void scrollPageDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}

	public void scrollPageUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}

	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void generateAlert(String message) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('" + message + "')");
	}

	public void refreshBrowserByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0)");
	}

	public String getBrowserInfo() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String uAgent = js.executeScript("return navigator.userAgent;").toString();
		return uAgent;
	}

	public void sendkeysUsingWithId(String id, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
	}

	public void checkPageIsReady() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (js.executeScript("return document.readyState;").toString().equals("complete")) {
			System.out.println("Page is loaded.");
			return;
		}

		for (int i = 0; i < 25; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (js.executeScript("return document.readyState;").toString().equals("complete")) {
				break;
			}
		}
	}

}
