package com.udemy.Listners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.udemy.utilities.TestUtils;
import com.relevantcodes.extentreports.LogStatus;
import com.udemy.base.*;
public class CustomListners extends TestBase implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test=report.startTest(result.getName().toUpperCase());
		
		if(!TestUtils.isTestRunnable(result.getName(), excel)){
			throw  new SkipException("Slippint this test"+ result.getName().toUpperCase()+ "as run mode is NO");
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
		test.log(LogStatus.PASS,result.getName().toUpperCase()+ "PASS");
		report.endTest(test);
		report.flush();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TestUtils.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		}
		
		test.log(LogStatus.FAIL, result.getName().toUpperCase()+"Failed with exception: "+result.getThrowable());
		test.log(LogStatus.FAIL,test.addScreenCapture(TestUtils.screenshotName));
		Reporter.log("Captureing screenshot");
		Reporter.log("<a target=\"_blank\" href="+TestUtils.screenshotName+">Screenshot</a>");
		report.endTest(test);
		report.flush();
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(LogStatus.SKIP, result.getName().toUpperCase()+" Skipped test");
		report.endTest(test);
		report.flush();
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
		// TODO Auto-generated method stub
		
	}

}
