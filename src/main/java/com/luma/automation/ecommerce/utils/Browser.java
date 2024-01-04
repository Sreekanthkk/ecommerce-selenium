package com.luma.automation.ecommerce.utils;



public interface Browser {

	public static String CHROME_BROWSER_VAUE = "chrome";
	public static String FIREFOX_BROWSER_VAUE = "firefox";
	public static String EDGE_BROWSER_VAUE = "edge";
	public static String SAFARI_BROWSER_VAUE = "safari";
	
	public static String CHROME_DRIVER_BINARY_KEY = "webdriver.chrome.driver";
	public static String GECKO_DRIVER_BINARY_KEY = "webdriver.gecko.driver";
	
	public static String CHROME_DRIVER_PATH = "./src/test/resources/drivers/chromedriver";
	public static String FIREFOX_DRIVER_PATH = "./src/test/resources/drivers/geckodriver";

	
}
