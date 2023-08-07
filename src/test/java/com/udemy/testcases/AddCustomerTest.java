package com.udemy.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.udemy.base.TestBase;
import com.udemy.utilities.TestUtils;

public class AddCustomerTest extends TestBase {
	@Test(dataProviderClass = TestUtils.class, dataProvider = "DP")
	public void addCustomerTest(Hashtable<String, String> data) throws InterruptedException {

		if (!data.get("RunMode").equals("Y")) {
			throw new SkipException("Skipping the test since run mode is set to NO");
		}
		click("btnAddcust_Xpath");
		System.out.println("firstname is " + data.get("FirstName"));
		System.out.println("lastName is " + data.get("Lastname"));
		type("txtFirstName_Xpath", data.get("FirstName"));

		type("txtLastName_Xpath", data.get("Lastname"));

		type("txtPostCode_Xpath", data.get("PostCode"));

		click("buttonAddCust_Xpath");
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("AlertText")));
		Thread.sleep(5000);
		alert.accept();

	}

}
