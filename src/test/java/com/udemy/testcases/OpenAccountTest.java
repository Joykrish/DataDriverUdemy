package com.udemy.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.udemy.base.TestBase;
import com.udemy.utilities.TestUtils;

public class OpenAccountTest extends TestBase {
	@Test(dataProviderClass=TestUtils.class, dataProvider="DP")
	public void openAccountTest(String Customer, String Currency,String alertText) throws InterruptedException {
		
		if(!TestUtils.isTestRunnable("openAccountTest", excel)){
			throw new SkipException("Slippint this test openAccountTest as run mode is NO");
		}
		click("btnOpenAccount_Xpath");
		System.out.println(Customer);
		System.out.println(Currency);
		select("dropDownCustomer_Xpath",Customer);
		select("dropDownCurrency_Xpath",Currency);
		click("btnProcess_Xpath");
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(alertText));
		Thread.sleep(5000);
		alert.accept();

	}

}
