package com.luma.automation.ecommerce.base;


import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.luma.automation.ecommerce.pages.AccountPage;
import com.luma.automation.ecommerce.pages.HomePage;
import com.luma.automation.ecommerce.pages.LandingPage;
import com.luma.automation.ecommerce.pages.LoginPage;
import com.luma.automation.ecommerce.pages.RegistrationPage;
import com.luma.automation.ecommerce.factory.DriverFactory;

public class BaseTest {
	
	
	public Properties prop;
	public DriverFactory df;
	public WebDriver driver;
	public LandingPage landingPage;
	public AccountPage accountPage;
	public LoginPage loginPage;
	public HomePage homePage;
	public RegistrationPage registrationPage;
	
	@Parameters({"browser","browserversion"})
	@BeforeTest
	public void setup(@Optional String browser,@Optional String browserVersion) {
		
		df=new DriverFactory();
		prop=df.initProperties();
		if (browser!=null && browserVersion!=null) {
			prop.setProperty("browser", browser);
			prop.setProperty("browserversion", browserVersion);
		}
		driver=df.initDriver(prop);
		landingPage=new LandingPage(driver);
		//landingPage.getLoginPage();		
		
	}
	
	@AfterTest
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
		
	}
	
	//,WebDriver driver
	public String takeScreenShot(String testName,WebDriver driver) {

		String screenshotPath = System.getProperty("user.dir") + "\\screenshots\\" + testName
				+ System.currentTimeMillis() + ".jpg";
		File screenshotFile = new File(screenshotPath);
		TakesScreenshot sc = (TakesScreenshot) driver;
		File source = sc.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source, screenshotFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenshotPath;

	}
	


}
