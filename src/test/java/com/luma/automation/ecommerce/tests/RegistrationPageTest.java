package com.luma.automation.ecommerce.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.luma.automation.ecommerce.base.BaseTest;
import com.luma.automation.ecommerce.utils.Constants;
import com.luma.automation.ecommerce.utils.ExcelUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Test cases to verify Registration functionalities")
public class RegistrationPageTest extends BaseTest{
	@BeforeClass
	public void registrationPageSetup() {
		registrationPage=landingPage.getCreateAcctPage();
	}
	
	@DataProvider
	public Object[][] getRegisterationData(){
		String filePath=prop.getProperty("dataFilePath");
		return ExcelUtils.getData(filePath,"NewUsers");
	}
	
	@Test(dataProvider="getRegisterationData")
	@Description("Test to verify User Registration functionality")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyRegistration(String fname,String lname,String email,String password) {
		accountPage=registrationPage.doRegister(fname, lname, email, password);
		if(registrationPage.isAcctAlreadyExistsMsgDisplayed())
			Assert.fail("Data Aleady exists");
		else if(accountPage.isRegistrationSuccessful()) {
			Assert.assertTrue(accountPage.getRegistrationSuccessfulMsg().equalsIgnoreCase(Constants.REGISTRATION_PAGE_SUCCESS));
			accountPage.clickOnLogout();
			Assert.assertTrue(accountPage.getLogoutMsg().equals(Constants.ACCOUNT_LOGOUT_MESSAGE));
			landingPage.getCreateAcctPage();	
		}
		
	}
}
