package com.udemy.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.udemy.base.TestBase;





public class LoginTest extends TestBase {
	@Test
	public void loginTest() throws InterruptedException, IOException {
		
		log.debug("Inside Login test");
		verifyEqual("abc", "xyz");
		
		Thread.sleep(4000);
		click("bmlbutton_Xpath");
		Thread.sleep(4000);
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("btnAddcust_Xpath"))));
		log.debug("Login successfully executed");
		//Assert.fail("Test Failed");
		

	}

}
