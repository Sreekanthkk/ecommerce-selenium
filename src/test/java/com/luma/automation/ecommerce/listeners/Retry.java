package com.luma.automation.ecommerce.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	int count=0;
	@Override
	public boolean retry(ITestResult result) {
		
		if(count<2) {
			this.count++;
			return true;
		}
		return false;
	}

}
