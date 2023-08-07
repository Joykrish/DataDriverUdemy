package com.udemy.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.udemy.base.TestBase;
import com.udemy.utilities.TestUtils;

public class OpenAccountTest extends TestBase {
	@Test(dataProviderClass=TestUtils.class, dataProvider="DP")
	public void openAccountTest(Hashtable<String,String> data) throws InterruptedException {
		
		
		  
		 
		click("btnOpenAccount_Xpath");
		System.out.println(data.get("Customer"));
		System.out.println(data.get("Currency"));
		select("dropDownCustomer_Xpath",data.get("Customer"));
		select("dropDownCurrency_Xpath",data.get("Currency"));
		click("btnProcess_Xpath");
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("AlertText")));
		Thread.sleep(5000);
		alert.accept();

	}

}
