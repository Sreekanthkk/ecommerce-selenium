package com.luma.automation.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.luma.automation.ecommerce.utils.ElementUtil;

import io.qameta.allure.Step;

public class RegistrationPage {
	public WebDriver driver;
	private ElementUtil eleUtil;
	private	By pageTitle=By.cssSelector("h1.page-title span");
	private	By pageCreateInfoLegend=By.cssSelector("fieldset.create.info span");
	private	By firstNametxt=By.id("firstname");
	private	By lastNametxt=By.id("lastname");
	private	By pageCreateAcctLegend=By.cssSelector("fieldset.create.acount span");
	private	By emailAddrtxt=By.id("email_address");
	private	By passwordtxt=By.id("password");
	private	By passwordConfirmationtxt=By.id("password-confirmation");
	private By passwordStrengthMeterLabel=By.cssSelector("div.password-strength-meter>span");
	private By creatAcctBtn=By.cssSelector("button[title='Create an Account']");
	private By acctAlreadyExistsMsg=By.xpath("//div[contains(text(),'There is already an account with this email address')]");
	
	public RegistrationPage(WebDriver driver) {
		this.driver=driver;
		this.eleUtil=new ElementUtil(driver);
	}
	
	@Step("Doing registration")
	public AccountPage doRegister(String fname,String lname,String email,String password) {
		
		eleUtil.doSendKeys(firstNametxt, fname);
		eleUtil.doSendKeys(lastNametxt, lname);
		eleUtil.doSendKeys(emailAddrtxt, email);
		eleUtil.doSendKeys(passwordtxt, password);
		eleUtil.doSendKeys(passwordConfirmationtxt, password);
		eleUtil.doClick(creatAcctBtn);
		return new AccountPage(driver);
	}
	@Step("verifying Account Already Exists error message displayed")
	public boolean isAcctAlreadyExistsMsgDisplayed() {
		eleUtil.waitToBeVisible(acctAlreadyExistsMsg);
		return eleUtil.isDisplayed(acctAlreadyExistsMsg);
	}
	
}
