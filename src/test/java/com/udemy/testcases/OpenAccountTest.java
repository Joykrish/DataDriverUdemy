package com.udemy.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.udemy.base.TestBase;

public class OpenAccountTest extends TestBase {
	@Test(dataProvider = "getData")
	public void openAccountTest(String Customer, String Currency)
			throws InterruptedException {

	

	}

	@DataProvider
	public Object[][] getData() {

		int rows = excel.getRowCount("openAccountTest");
		int cols = excel.getColumnCount("openAccountTest");

		Object[][] data = new Object[rows - 1][cols];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			for (int colNum = 0; colNum < cols; colNum++) {

				data[rowNum - 2][colNum] = excel.getCellData("AddCustomerTest", colNum, rowNum);

			}

		}
		return data;
	}

}
