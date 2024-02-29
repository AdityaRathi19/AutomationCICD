package adityaRathi.TestComponent;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import adityaRathi.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread safe

	public void onTestStart(ITestResult result) 
	{
		//this will get the test case name
	 	test =   extent.createTest(result.getMethod().getMethodName());
	 	extentTest.set(test); //assign unique thread id(ErrorValidationTest-> test

	}

	public void onTestSuccess(ITestResult result) 
	{
		extentTest.get().log(Status.PASS, "Test Passed");

	}

	public void onTestFailure(ITestResult result) 
	{
		// getThrowable will give all the error message
		extentTest.get().fail(result.getThrowable());
		
		
		try {
			driver= (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		//Step 1- Take Screenshot, 2- Attach to report
}
	public void onTestSkipped (ITestResult result) 
	{
		
	}
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		
	}
	public void onStart(ITestContext context) 
	{
		
	}
	public void onFinish(ITestContext context) 
	{
		extent.flush();
	}
	
}
