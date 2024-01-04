package com.luma.automation.ecommerce.tests;



import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.luma.automation.ecommerce.base.BaseTest;
import com.luma.automation.ecommerce.listeners.Retry;
import com.luma.automation.ecommerce.utils.Constants;

import io.qameta.allure.Step;

public class HomePageTest extends BaseTest{
	
	@BeforeClass
	@Parameters({"emailid","password"})
	@Step("Validating login using {0} and {1}")
	public void doLogin(String email,String password) {
		loginPage=landingPage.getLoginPage();
		homePage=loginPage.doLogin(email,password);
	}
	
	@Test(retryAnalyzer=Retry.class)
	public void verifyWelcomeMsg() {
		Assert.assertTrue(homePage.getWelcomeText().equals(Constants.WELCOME_MESSAGE), "Actual: "+homePage.getWelcomeText()+", Expected: "+Constants.WELCOME_MESSAGE);
	}
	
	@Test
	public void verifyPageTitle() {
		Assert.assertEquals(homePage.getPageTitle(),Constants.HOME_PAGE_TITLE);
	}
	
	@Test
	public void verifyPromoSectionExist() {	
		Assert.assertTrue(homePage.isPromoSectionExist());	
	}
	
	@Test
	public void getPromos() {
		Assert.assertEquals(homePage.getPromos().size(), Constants.HOME_PAGE_PROMOS.size());;
	}
	
	@Test(retryAnalyzer=Retry.class)
	public void verifyHotSellerSectionExists()  {
		Assert.assertTrue(homePage.isHotSellerSectionExist());
	}
	
	@Test(retryAnalyzer=Retry.class)
	public void verifyHotSellerTitle() {
		Assert.assertEquals(homePage.getHotSellersTitle(),Constants.HOME_PAGE_HOT_SELLER_TITLE);
	}
	
	@Test(retryAnalyzer=Retry.class)
	public void verifyHotSellerSubTitle() {
		Assert.assertEquals(homePage.getHotSellersSubTitle(),Constants.HOME_PAGE_HOT_SELLER_SUB_TITLE_INFO);
	}
	
	
	//public List<String> getProductItems() {
		//return eleUtil.getTexts(productItems);
	//}
	
	@Test
	public void verifySignOutLinkDisplayed () {
		Assert.assertTrue(homePage.isSignOutLinkDisplayed());
	}
}
