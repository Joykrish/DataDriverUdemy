package com.udemy.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.udemy.base.TestBase;
import com.udemy.utilities.TestUtils;

public class AddCustomerTest extends TestBase {
	@Test(dataProviderClass=TestUtils.class,dataProvider="DP")
	public void addCustomerTest(String firstName, String lastName, String pincode, String alertText)
			throws InterruptedException {

		click("btnAddcust_Xpath");

		type("txtFirstName_Xpath",firstName );

		type("txtLastName_Xpath", lastName);

		type("txtPostCode_Xpath", pincode);

		click("buttonAddCust_Xpath");
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(alertText));
		Thread.sleep(5000);
		alert.accept();

	}



}
