package com.luma.automation.ecommerce.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.luma.automation.ecommerce.base.BaseTest;
import com.luma.automation.ecommerce.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Test cases to validate Landing page")
public class LandingPageTest extends BaseTest{
	@Test
	@Description("Test to check whether Sign-In link is displayed or not")
	@Severity(SeverityLevel.CRITICAL)
	public void verifySignInLinkDisplayed() {
		Assert.assertTrue(landingPage.isSignInLinkDisplayed());
	}
	
	@Test
	@Description("Test to check whether Create Account Link is displayed or not")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyCreateAcctLinkDisplayed() {
		Assert.assertTrue(landingPage.isLinkCreateAcctDisplayed());
	}
	
	@Test(enabled=false)
	@Description("Test to verify Sign-in finctionality")
	@Severity(SeverityLevel.CRITICAL)
	public void verifySignInLink() {
		loginPage=landingPage.getLoginPage();
		Assert.assertEquals(loginPage.getLoginPageTitle(), Constants.LOGIN_PAGE_TITLE);
	}
}
