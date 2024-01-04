package com.luma.automation.ecommerce.pages;


import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.luma.automation.ecommerce.utils.Constants;
import com.luma.automation.ecommerce.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	WebDriver driver;
	ElementUtil eleUtil;
	
	private By email=By.id("email");
	private By password=By.cssSelector("input#pass");
	private By btnSignIn=By.xpath("//button/span[text()='Sign In']");
	private By linkForgotPwd=By.linkText("Forgot Your Password?");
	private By btnCreateAcct=By.linkText("Create an Account");
	//private static final Logger log=Logger.getLogger(LoginPage.class);
	
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public String getLoginPageTitle() {
		if (eleUtil.waitForTitle(Constants.DEFAULT_WAIT_TIMEOUT, Constants.LOGIN_PAGE_TITLE)) {
			//log.debug("Expected: "+Constants.LOGIN_PAGE_TITLE+" ,Actual: "+driver.getTitle());
			return driver.getTitle();
		}
		else {
			return driver.getTitle();
		}
	}
	
	public boolean isForgotPasswordDisplayed() {
		return eleUtil.isDisplayed(linkForgotPwd);
	}
	
	public boolean isCreateAcctBtnDisplayed() {
		return eleUtil.isDisplayed(btnCreateAcct);
	}
	
	public boolean isEmailFieldDisplayed() {
		return eleUtil.isDisplayed(email);
	}
	
	public boolean isPasswordFieldDisplayed() {
		return eleUtil.isDisplayed(password);
	}
	
	public boolean isSignInBtnDisplayed() {
		return eleUtil.isDisplayed(btnSignIn);
	}
	
	@Step("")
	public HomePage doLogin(String email,String password) {
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doClick(btnSignIn);
		return new HomePage(driver);
	}
	
	//<span class="not-logged-in" data-bind="html:&quot;Default welcome msg!&quot;">Default welcome msg!</span>
	
		
}
