package com.luma.automation.ecommerce.pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.luma.automation.ecommerce.utils.Constants;
import com.luma.automation.ecommerce.utils.ElementUtil;

public class AccountPage {
	
	WebDriver driver;
	ElementUtil eleUtil;
	private By lblMyAccountPageTitle=By.cssSelector("h1[class='page-title']");
	private By lblAccountInfoTitle=By.xpath("//div[@class='block-title']/strong[contains(text(),'Account Information')]");
	//private By lblContactInfo=By.xpath("//div[@class='box box-information']//span[contains(text(),'Contact Information')]");
	private By lblContactInfo=By.cssSelector("div[class*='box-information']>strong[class='box-title']");
	private By textContactInfo=By.xpath("//div[@class='box box-information']/div[@class='box-content']");
	private By menuCustomer=By.cssSelector("div.panel.header button.action.switch");
	private By logoutLink=By.linkText("Sign Out");
	private By linkEdit=By.linkText("Edit");
	private By linkChangePwd=By.linkText("Change Password");
	private By menuSidebarMain=By.cssSelector("ul.nav.items>li");
	private By menuWomen1=By.cssSelector("div#store\\.menu ul[role='menu']>li[class*='nav-1']");
	private By menuWomen2=By.linkText("Women");
	private By subMenu1=By.linkText("Tops");
	private By subMenu2=By.linkText("Jackets");
	private By acctCreateSuccessMsg=By.cssSelector("div[data-ui-id='message-success']>div");
	private By logOutSuccessMessage=By.cssSelector("h1.page-title>span");
	
	public AccountPage(WebDriver driver) {
		this.driver=driver;
		this.eleUtil=new ElementUtil(driver);
	}
	public String getAccountPageTitle() {
		return eleUtil.getText(lblMyAccountPageTitle);
	}
	
	public String getAccountInfoTitle() {
		return eleUtil.getText(lblAccountInfoTitle);
	}
	
	public String getContactInfoTitle() {
		return eleUtil.getText(lblContactInfo);
	}
	
	public List<String> getContactInfo() {
		
		return  Arrays.asList(eleUtil.getText(textContactInfo).split("\\n"));
	}
	
	public Boolean isEditLinkExists() {
		return eleUtil.isDisplayed(linkEdit);
	}
	
	public Boolean isChangePwdLinkExists() {
		return eleUtil.isDisplayed(linkChangePwd);
	}
	
	public List<WebElement> getMainMenuItems() {
		return eleUtil.getElements(menuSidebarMain);
	}
	
	public void clickOnWomenMenu() {
		eleUtil.selectSubMenu(menuWomen2, subMenu1,subMenu2);
	}
	
	public void clickOnLogout() {
		eleUtil.doClick(menuCustomer);
		eleUtil.doClick(logoutLink);
	}
	
	public String getLogoutMsg() {
		eleUtil.waitToBeVisible(logOutSuccessMessage);
		return eleUtil.getText(logOutSuccessMessage).trim();
	}
	
	public boolean isRegistrationSuccessful() {
		return eleUtil.isDisplayed(acctCreateSuccessMsg);
			
	}
	
	public String getRegistrationSuccessfulMsg() {
		return eleUtil.getText(acctCreateSuccessMsg);
	}
}
