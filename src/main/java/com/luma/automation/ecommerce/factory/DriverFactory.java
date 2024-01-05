package com.luma.automation.ecommerce.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hslf.util.SystemTimeUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.luma.automation.ecommerce.utils.Browser;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	private WebDriver driver;
	private ThreadLocal<WebDriver> tlDriver;
	private Properties prop;
	private String env;
	private String browser;
	private OptionsManager optionsManager;
	public static boolean highlight;

	public WebDriver initDriver(Properties prop) {
		browser = prop.getProperty("browser").trim();
		String url = prop.getProperty("url");
		highlight = Boolean.parseBoolean(prop.getProperty("highlight").trim());
		optionsManager = new OptionsManager(prop);
		tlDriver = new ThreadLocal<>();
		System.out.println("Suite is running on " + env);
		if (browser.equalsIgnoreCase(Browser.CHROME_BROWSER_VAUE)) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				initRemoteDriver(browser);
			} else {
				// System.setProperty("webdriver.chrome.driver","C:\\MyLearning\\Selenium\\Drivers\\chromedriver_win32\\chromedriver.exe");
				WebDriverManager.chromedriver().setup();
				ChromeOptions co=optionsManager.getChromeOptions();
				System.out.println(co.getCapabilityNames());
				System.out.println(co.getCapability("headless"));
				driver = new ChromeDriver(optionsManager.getChromeOptions());
				tlDriver.set(driver);
			}

		} else if (browser.equalsIgnoreCase(Browser.FIREFOX_BROWSER_VAUE)) {
			// System.setProperty("webdriver.gecko.driver","C:\\MyLearning\\Selenium\\Drivers\\geckodriver-v0.32.2-win64\\geckodriver.exe");

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				initRemoteDriver(browser);
			} else {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
				tlDriver.set(driver);
			}
		} else if (browser.equalsIgnoreCase(Browser.EDGE_BROWSER_VAUE)) {
			// System.setProperty("webdriver.gecko.driver","C:\\MyLearning\\Selenium\\Drivers\\edgedriver_win64\\msedgedriver.exe");
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				initRemoteDriver(browser);
			} else {
				WebDriverManager.edgedriver().setup();
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
		} else {
			System.out.println("Please provide a valid browser name");
		}
		try {
			getUrl(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getDriver();
	}

	public WebDriver getDriver() {
		return tlDriver.get();
	}

	public void initRemoteDriver(String browser) {
		String hubUrl = prop.getProperty("huburl");
		if (browser.equalsIgnoreCase(Browser.CHROME_BROWSER_VAUE)) {
			try {
				tlDriver.set(new RemoteWebDriver(new URL(hubUrl), optionsManager.getChromeOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else if (browser.equalsIgnoreCase(Browser.FIREFOX_BROWSER_VAUE)) {
			try {
				tlDriver.set(new RemoteWebDriver(new URL(hubUrl), optionsManager.getFirefoxOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else if (browser.equalsIgnoreCase(Browser.EDGE_BROWSER_VAUE)) {
			try {
				tlDriver.set(new RemoteWebDriver(new URL(hubUrl), optionsManager.getEdgeOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}

	public void getUrl(String url) throws Exception {
		if (url.isEmpty() || url == null)
			System.out.println("Not a valid URL");
		else if (url.contains("https") || url.contains("http")) {
			getDriver().manage().deleteAllCookies();
			getDriver().get(url);
			// tlDriver.get().manage().window().maximize();
			getDriver().manage().window().fullscreen();
		} else {
			try {
				throw new MalformedURLException("INVALID URL EXCEPTION");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} finally {
				driver.close();
				try {
					throw new Exception("No driver instances available");
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}

	}

	public Properties initProperties() {
		String env = System.getProperty("env");
		System.out.println("Test environment: "+env);
		String filePath=null;
		FileInputStream ip = null;
		if(env==null||env.isEmpty()) {
			filePath = "src/test/resources/config/config.properties";
		}
		else {
			switch(env.toLowerCase().trim()) {
				case "qa": filePath = "src/test/resources/config/qa_config.properties";
				case "dev": filePath = "src/test/resources/config/dev_config.properties";
				case "prod": filePath = "src/test/resources/config/config.properties";
				case "uat": filePath = "src/test/resources/config/uat_config.properties";
				default: filePath = "src/test/resources/config/config.properties";
			}
		}
		try {
			System.out.println("File Path: "+filePath);
			ip = new FileInputStream(new File(filePath));
			prop = new Properties();
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	/*
	 * public String takeScreenShot(String testName) {
	 * 
	 * String screenshotPath = System.getProperty("user.dir") + "\\screenshots\\" +
	 * testName + System.currentTimeMillis() + ".jpg"; File screenshotFile = new
	 * File(screenshotPath); TakesScreenshot sc = (TakesScreenshot) getDriver();
	 * File source = sc.getScreenshotAs(OutputType.FILE); try {
	 * FileUtils.copyFile(source, screenshotFile); } catch (IOException e) {
	 * e.printStackTrace(); } return screenshotPath;
	 * 
	 * }
	 */
	 

}
