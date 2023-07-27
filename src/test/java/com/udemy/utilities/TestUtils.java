package com.udemy.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.udemy.base.TestBase;

public class TestUtils extends TestBase {
public static String screenshotName;
public static String screenshotPath;
	public static void captureScreenshot() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Date d=new Date();
		String ScreenshotName=d.toString().replace(":","_").replace(" ", "_");
		screenshotName=ScreenshotName+".JPG";
		System.out.println(" Screenshot location is "+System.getProperty("user.dir") + "target\\surefire-reports\\html\\"+screenshotName);
		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\"+screenshotName));
	}
	@DataProvider(name="DP")
	public Object[][] getData(Method m) {
		
		String sheetName=m.getName();
		System.out.println("Sheet name is "+sheetName);
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		

		Object[][] data = new Object[rows - 1][cols];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			for (int colNum = 0; colNum < cols; colNum++) {

				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);

			}

		}
		return data;
	}
	
	public static boolean isTestRunnable(String testName,ExcelReader excel) {
		String excelSheet="TestSuite";
		int rows=excel.getRowCount(excelSheet);
		for(int rNum=2;rNum<=rows;rNum++) {
			String testCase=excel.getCellData(excelSheet, "TCID", rNum);
			if(testCase.equalsIgnoreCase(testName)) {
				String runmode=excel.getCellData(excelSheet, "RUNMODE", rNum);
				if(runmode.equalsIgnoreCase("Y")) 
					return true;
					else 
						return false;
				}
			}
		return false;
		}
		
	}

