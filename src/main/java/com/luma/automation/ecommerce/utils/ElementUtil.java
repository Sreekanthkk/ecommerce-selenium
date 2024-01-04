package com.luma.automation.ecommerce.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.luma.automation.ecommerce.factory.DriverFactory;

 


public class ElementUtil {
	private WebDriver driver;
	private JSUtils jsUtils;
	//private static final Logger log=LogManager.getLogger(ElementUtil.class);
	
	public ElementUtil(WebDriver driver){
		this.driver=driver;
		this.jsUtils=new JSUtils(driver);
	}
	
	public WebElement getElement(By locator) {
		WebElement ele=driver.findElement(locator);
		if(DriverFactory.highlight) {
			jsUtils.highlight(10, ele);
		}
		return ele;
	}
	
	
	public WebElement getElement(String locator,String type) {
		return driver.findElement(getBy(locator,type));
	}
	
	public List<WebElement> getElements(By locator){
		return driver.findElements(locator);
	}
	
	
	
	public By getBy(String locator,String type) {
		switch(type.toLowerCase()){
		case "id":
			return By.id(locator);
		case "name":
			return By.name(locator);
		case "class":
			return By.className(locator);
		case "xpath":
			return By.xpath(locator);
		case "css":
			return By.cssSelector(locator);
		case "linktext":
			return By.linkText(locator);
		case "partiallinktext":
			return By.partialLinkText(locator);
		default:
			return null;
		}
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public void doSendKeys(By locator,String value) {
		getElement(locator).sendKeys(value);
		
	}
	public void doSendKeys(By locator,Keys key) {
		getElement(locator).sendKeys(key);
		
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
		
	}
	
	public void clickAction(By locator) {
		WebElement element=getElement(locator);
		Actions actions= new Actions(driver);
		actions.moveToElement(element).click().perform();
	}
	
	public String getText(By locator){
		WebElement element=getElement(locator);
		return element.getText().trim();
		 
	}
	
	public List<String> getTexts(By locator){
		List<WebElement> elements=new ArrayList<>();
		List<String> elementsText=new ArrayList<>();
		elements=getElements(locator);
		for(WebElement element:elements) {
			if(!element.getText().isEmpty()) {
				elementsText.add(element.getText().trim());
			}
		}
		return elementsText;
	}
	/**
	 * 
	 * To check whether element is present or not
	 * Find Elements and the List size is >0, then the element is present - No Exception thrown in this case
	 * 
	 * @param locator
	 * @return
	 */
	
	public boolean isExist(By locator) {
		List<WebElement> elements=new ArrayList<>();
		elements=getElements(locator);
		if(elements.size()>0) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	/**
	 * 
	 * To see the element is displayed in the page
	 * @param locator
	 * @return
	 */
	public boolean isDisplayed(By locator) {
		waitToBeVisible(locator);
		return getElement(locator).isDisplayed();
	}
	
	//****************Drop down -Select*********************
	
	/*
	 * deselect, multipleSelect(isMultiple()),getAllSelected() etc
	 */
	
	public void doSelectByVisibleText(By locator,String item) {
		Select selectMenu=new Select(getElement(locator));
		selectMenu.selectByVisibleText(item);
	}
	
	public void doSelectByText(By locator,String item) {
		Select selectMenu=new Select(getElement(locator));
		selectMenu.selectByValue(item);
	}
	
	public void doSelectByIndex(By locator,int item) {
		Select selectMenu=new Select(getElement(locator));
		selectMenu.selectByIndex(item);
	}
	
	/**
	 * uses select menu options for selection
	 * @param locator
	 * @param country
	 */
	public void doSelectFromDropdownOptions(By locator,String country){
		Select selectMenu=new Select(getElement(locator));
		List<WebElement> options=selectMenu.getOptions();
		System.out.println(options.size());
		for(WebElement option:options) {			
			String countryText=option.getText();
			if(countryText.equalsIgnoreCase(country)) {				
				option.click();
				break;
			}
		}
		
	}
	
	/**
	 * does not use selection object for selection
	 * @param locator
	 * @param country
	 */
	public void doSelectFromDropdownWithoutSelect(By locator,String country){
		getElement(locator).sendKeys(country);
	}
	
	/**
	 * Options will be retrieved as WebElement Objects and click on desired option
	 * @param locator
	 * @param country
	 */
	public void doSelectFromDropdown(By locator,String country){
		List<WebElement> options=getElements(locator);
		System.out.println(options.size());
		for(WebElement option:options) {
			String countryText=option.getText();
			if(countryText.equalsIgnoreCase(country)) {				
				option.click();
				break;
			}
		}
	}
	/**
	 * Returns the list of options available
	 * @param locator
	 * @return
	 */
	public List<String> getDropdownOptions(By locator){
		Select selectMenu=new Select(getElement(locator));
		List<WebElement> options=selectMenu.getOptions();
		List<String> optionTextList=new ArrayList<>();
		for(WebElement option:options) {
			optionTextList.add(option.getText());
		}
		return optionTextList;
	}
	
	//********************Dropdown - JQuery Selection**************
	
	/**
	 * Select all the given options
	 * @param locator
	 */
	public void doSelectAllFromJQueryCT(By locator){
		List<WebElement> options=getElements(locator);
		for(WebElement option:options) {			
				try {
					option.click();
				} catch (Exception e) {
					System.out.println("Selected all the options");
					break;
				}
			}		
	}
	
	/**
	 * Select the given option
	 * @param locator
	 */
	public void doSelectFromJQueryCT(By locator,String opted){
		List<WebElement> options=getElements(locator);
		for(WebElement option:options) {			
				try {
						String text=option.getText();
						if(text.equalsIgnoreCase(opted)) {
							option.click();
							break;
						}							
				} catch (Exception e) {
					System.out.println("Unable to select "+e.getMessage());
					break;
				}
			}		
	}
	
	/**
	 * Select multiple options
	 * @param locator
	 * @param opted
	 */
	public void doMultiSelectFromJQueryCT(By locator,String... optedList){
		Map<Integer,String> selectedValues=new HashMap<>();
		for(int i=0;i<optedList.length;i++) {
			selectedValues.put(i, optedList[i]);
		}
		List<WebElement> options=getElements(locator);
		for(WebElement option:options) {	
			
				try {
						String text=option.getText();
						for(Map.Entry<Integer, String> optedItem:selectedValues.entrySet()) {
							if(text.equalsIgnoreCase(optedItem.getValue())) {
								option.click();
								selectedValues.remove(optedItem.getKey());
								break;
							}	
						}
												
				} catch (ElementNotInteractableException e) {
					System.out.println("Unable to select "+e.getMessage());
					
				}
				if(selectedValues.isEmpty()) {
					break;
				}
			}
		if(!selectedValues.isEmpty()) {
			System.out.println("Unable to select these options: "+selectedValues.toString());
		}
	}
	
	//********************Suggestion box
	
	public void doSelectFromSuggestionList(By locator,String textToBeSelected){
		List<WebElement> suggestionListEntries=new ArrayList<>();
		suggestionListEntries=getElements(locator);
		
		for(WebElement item:suggestionListEntries) {
			System.out.println(item.getText());
			if(item.getText().equalsIgnoreCase(textToBeSelected)) {
				item.click();
				break;
			}
		}
	}
	// try to return all the suggestions in a list
	
	//*****************Actions*****************//
	
	public void selectSubMenu(By parentMenu,By childMenu) {
		Actions act=new Actions(driver);
		act.moveToElement(getElement(parentMenu)).perform();
		// div#store\.menu ul[role="menu"]>li[class*='nav-1']
		getElement(childMenu).click();
	}
	
	public void selectSubMenu(By parentMenu,By childMenu1,By childMenu2) {
		Actions act=new Actions(driver);
		act.moveToElement(getElement(parentMenu)).perform();
		act.moveToElement(getElement(childMenu1)).perform();
		// div#store\.menu ul[role="menu"]>li[class*='nav-1']
		getElement(childMenu2).click();
	}
	
	//*****************Footer List*****************
	
	
	
	//********************Wait****************//
	
	public void waitToBePresent(By locator) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public void waitToBeVisible(By locator) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public void waitToBeInvisible(By locator) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public WebElement waitToBeVisible(WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public Boolean waitToBeRefreshed(WebElement element,String text) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}
	
	public void waitToBeClickable(By locator) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public void waitToPageLoad(long waitTimeInMillis) {
		long maxWaitTime=System.currentTimeMillis()+waitTimeInMillis;
		JavascriptExecutor js=(JavascriptExecutor)driver;
		
		while(System.currentTimeMillis()<=maxWaitTime) {
			String pageLoadState=(String)js.executeScript("return document.readyState");
			if(pageLoadState.equals("complete")) {
				//System.out.println("Page loaded successfully!");
				break;
			}
				
		}
	}
	
	public boolean waitForTitle(long timeOut,String title) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleIs(title));
	}
}
