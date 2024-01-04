package com.luma.automation.ecommerce.listeners;


import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.luma.automation.ecommerce.base.BaseTest;
import com.luma.automation.ecommerce.utils.ExtentReportGenerator;

public class ExtentReportListnr extends BaseTest implements ITestListener {
	
	private ExtentReports extent=ExtentReportGenerator.initConfig();
	WebDriver driver;
	private ExtentTest extentTest;
	private ThreadLocal<ExtentTest> test=new ThreadLocal<>();
	@Override
	public void onTestStart(ITestResult result) {
		
		String testName=result.getMethod().getMethodName();
		extentTest=extent.createTest(testName);
		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().pass("PASSED");
		test.get().log(Status.PASS, result.getName());
		String testName=result.getMethod().getMethodName();
		//test.get().addScreenCaptureFromPath(takeScreenShot(testName));	
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName=result.getMethod().getMethodName();
		  try { 
			  System.out.println(result.getTestClass().getRealClass().toString());
			  System.out.println(result.getTestClass().getRealClass().getField("driver").toString());
			  driver=(WebDriver) result.getTestClass().getRealClass().getField("driver")
					  .get(result.getInstance());
		  } 
		  catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) { 
		   e.printStackTrace(); 
		  }
		 
		//,driver
		test.get().fail(result.getThrowable());
		test.get().addScreenCaptureFromPath(takeScreenShot(testName,driver));	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		test.remove();
	}



}
