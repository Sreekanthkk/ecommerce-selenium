package com.luma.automation.ecommerce.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.luma.automation.ecommerce.utils.ElementUtil;

public class HomePage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By promoDiv=By.cssSelector("div.blocks-promo");
	private By promos=By.cssSelector("div.blocks-promo .content");
	private By promosContentTitle=By.cssSelector("div.blocks-promo .content>strong.title");
	private By hotSellersTitle=By.xpath("//div[@class='class-heading']/h2[@class='title']");
	private By hotSellersSubTitle=By.xpath("//div[@class='class-heading']/p[@class='info']");
	private By productItems=By.cssSelector("ol.product-items>li strong.product-item-name>a");
	private By customerMenu=By.cssSelector("span.customer-name>button.action");
	private By linkMyAccont=By.cssSelector("ul.header.links>li>a[href$='customer/account/']:nth-of-type(1)");
	private By linkSignOut=By.xpath("//div[@class='customer-menu' and @aria-hidden='false']//li[@class='authorization-link']/a[contains(text(),'Sign Out')]");
	private By preLoginWelcomeMsg=By.cssSelector("header.page-header span.not-logged-in");
	private By welcomeMessage=By.cssSelector("header.page-header li.welcome>span");
	
	
	HomePage(WebDriver driver){
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public String getPageTitle() {
		return eleUtil.getPageTitle();
	}
	public boolean isPromoSectionExist() {	
		return eleUtil.isExist(promoDiv) && eleUtil.getElements(promos).size()>0?true:false;	
	}
	
	public List<String> getPromos() {
		return eleUtil.getTexts(promosContentTitle);
	}
	
	
	public boolean isHotSellerSectionExist() {
		return eleUtil.isDisplayed(hotSellersTitle)? true:false;
	}
	
	public String getHotSellersTitle() {
		return eleUtil.getText(hotSellersTitle);
	}
	
	public String getHotSellersSubTitle() {
		return eleUtil.getText(hotSellersSubTitle);
	}
	
	public List<String> getProductItems() {
		return eleUtil.getTexts(productItems);
	}
	
	public boolean isSignOutLinkDisplayed() {
		eleUtil.doClick(customerMenu);
		return eleUtil.isDisplayed(linkSignOut);
	}
	
	public void doSignOut() {		
			eleUtil.doClick(customerMenu);
			eleUtil.doClick(linkSignOut);
			//return new AccountPage(driver);
		}
	
	
	public String getWelcomeText() {
		//eleUtil.waitToBeRefreshed(eleUtil.getElement(welcomeMessage),"test ");
		//eleUtil.getElement(welcomeMessage).
		eleUtil.waitToBeInvisible(preLoginWelcomeMsg);
		return eleUtil.getElement(welcomeMessage).getText();
	}
	
	public AccountPage goToMyAccountPage() {
		eleUtil.doClick(customerMenu);
		eleUtil.doClick(linkMyAccont);
		return new AccountPage(driver);
	}
	
}
