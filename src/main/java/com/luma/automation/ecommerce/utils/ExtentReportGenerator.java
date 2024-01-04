package com.luma.automation.ecommerce.utils;




import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportGenerator {
	private static String reportPath=System.getProperty("user.dir")+"\\reports";
	private static String reportFile="index.html";
	private static String reportFilePath=reportPath+"\\"+reportFile;
	
	public static ExtentReports initConfig() {
		ExtentSparkReporter reporter=new ExtentSparkReporter(reportFilePath);
		reporter.config().setDocumentTitle("Automation Test Results");
		reporter.config().setReportName("Automation Cycle-1 Execution Report");
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("Tester", "Sreekanth");
		extent.setSystemInfo("Cylce", "Cylce1");
		
		return extent;
	}
	
}
