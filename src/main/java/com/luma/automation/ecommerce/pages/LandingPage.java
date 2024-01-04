package com.luma.automation.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.luma.automation.ecommerce.utils.ElementUtil;

import io.qameta.allure.Step;

public class LandingPage {
	
	WebDriver driver;
	ElementUtil eleUtil;
	private By linkSignIn=By.linkText("Sign In");
	private By linkCreateAcct=By.linkText("Create an Account");
	
	public LandingPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	@Step("Verfying Sign-in link is displayed")
	public boolean isSignInLinkDisplayed() {
		return eleUtil.isDisplayed(linkSignIn);
	}
	
	@Step("Verfying Create Account link is displayed")
	public boolean isLinkCreateAcctDisplayed() {
		return eleUtil.isDisplayed(linkCreateAcct);
	}
	
	@Step("Performing Login action")
	public LoginPage getLoginPage() {
		eleUtil.doClick(linkSignIn);
		return new LoginPage(driver);
	}
	
	@Step("Going to Registration Page")
	public RegistrationPage getCreateAcctPage() {
		eleUtil.doClick(linkCreateAcct);
		return new RegistrationPage(driver);
	}
	
}
