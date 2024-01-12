package com.luma.automation.ecommerce.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	Properties prop;
	OptionsManager(Properties prop) {
		this.prop=prop;
	}
	
	public ChromeOptions getChromeOptions() {
		ChromeOptions chromeOptions=new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			chromeOptions.addArguments("--headless=new");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			chromeOptions.addArguments("--incognito");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			chromeOptions.setPlatformName("linux");
			//chromeOptions.setCapability("enableVNC", true);
			//chromeOptions.setBrowserVersion(prop.getProperty("browserVersion"));
		}
		
		return chromeOptions;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		FirefoxOptions firefoxOptions=new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) firefoxOptions.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) firefoxOptions.addArguments("--incognito");
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			firefoxOptions.setPlatformName("linux");
			firefoxOptions.setCapability("enableVNC", true);
			firefoxOptions.setBrowserVersion(prop.getProperty("browserversion"));
		}
		
		return firefoxOptions;
	}
	
	public EdgeOptions getEdgeOptions() {
		EdgeOptions edgeOptions=new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) edgeOptions.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) edgeOptions.addArguments("--incognito");
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			edgeOptions.setPlatformName("linux");
			edgeOptions.setCapability("enableVNC", true);
			edgeOptions.setBrowserVersion(prop.getProperty("browserversion"));
		}
		
		return edgeOptions;
	}
}
