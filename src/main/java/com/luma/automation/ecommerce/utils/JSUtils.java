package com.luma.automation.ecommerce.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSUtils {
	private WebDriver driver;
	public JSUtils(WebDriver driver) {
		this.driver=driver;
	}
	public void doScrollIntoView(WebElement webElement) {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true);", webElement);
		}
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	public void doScrolltoBottom() {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	public void highlight(int duration,WebElement webElement) {
		String backgroundColor=webElement.getCssValue("backgroundColor");
		String flashColor="rgb(0,200,0)";
		for(int i=0;i<duration;i++) {
			changeColor(flashColor,webElement);
			changeColor(backgroundColor,webElement);
		}
		
	}
	public void changeColor(String color,WebElement webElement) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.backgroundColor='"+color+"'",webElement);
	}
}
