package com.luma.automation.ecommerce.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.luma.automation.ecommerce.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import com.luma.automation.ecommerce.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Login Test functionalities")
public class LoginPageTest extends BaseTest
{
	@BeforeTest
	public void doSignIn() {
		loginPage=landingPage.getLoginPage();
	}
	
	@Test	
	@Description("Test to check whether Create Account Button is displayed or not")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyCreateAcctBtnDisplayed() {
		Assert.assertTrue(loginPage.isCreateAcctBtnDisplayed());
	}
	
	@Test
	@Description("Test to check whether Forgot Password Button is displayed or not")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyForgotPwdBtnDisplayed() {
		Assert.assertTrue(loginPage.isForgotPasswordDisplayed());
	}
	
	@Test
	@Description("Test to validate Login page title")
	@Severity(SeverityLevel.TRIVIAL)
	public void verifyLoginPageTitle() {
		Assert.assertEquals(loginPage.getLoginPageTitle(), Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	@Description("Test to check whether Email Field is displayed or not")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyEmailFieldDisplayed() {
		Assert.assertTrue(loginPage.isEmailFieldDisplayed());
	}
	
	@Test
	@Description("Test to check whether Password Field is displayed or not")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyPasswordFieldDisplayed() {
		Assert.assertTrue(loginPage.isPasswordFieldDisplayed());
	}
	
	@Test
	@Description("Test to check whether SignIn button is displayed or not")
	@Severity(SeverityLevel.CRITICAL)
	public void verifySignInBtnDisplayed() {
		Assert.assertTrue(loginPage.isSignInBtnDisplayed());
	}

}
