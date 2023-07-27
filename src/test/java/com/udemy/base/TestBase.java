 package com.udemy.base;




import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.udemy.utilities.ExcelReader;
import com.udemy.utilities.ExtentManager;
import com.udemy.utilities.TestUtils;

public class TestBase {
public static  WebDriver driver;
public static Properties config=new Properties();
public static Properties OR=new Properties();
public static FileInputStream fis;
public static Logger log=Logger.getLogger("devpinoyLogger");
public static WebDriverWait wait;

public static ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"//src//test//resources//excel//TestData.xlsx");


public  ExtentReports report=ExtentManager.getInstance();
public static ExtentTest test;

@BeforeSuite
public void setUp() {
	
	if(driver==null) {
		
		 try {
			fis=new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//properties//Config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			config.load(fis);
			log.debug("Config file loaded!!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis=new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//properties//OR.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			OR.load(fis);
			log.debug("OR file loaded!!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(config.getProperty("browser").equals("chrome")){
			
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
			//C:\Users\Admin\eclipse-workspace\UdemyDataDrivenFramework\src\test\resources\executables\chromedriver.exe
			System.out.println(System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
			driver=new ChromeDriver();
			log.debug("Chrome Launched");
		}
		else if((config.getProperty("browser").equals("edge"))){
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\scr\\test\\resources\\executables\\msedgedriver.exe");
			System.out.println(System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\msedgedriver.exe");
			driver=new EdgeDriver();
		}
		driver.get(config.getProperty("testsiteurl"));
		log.debug("URL launched =" +config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
		wait=new WebDriverWait(driver,5);
		
	}
	
	
}

public static void verifyEqual(String expected,String Actual) throws IOException {
	
	try {
		Assert.assertEquals(expected, Actual);
		
	} catch (Throwable  e) {
		TestUtils.captureScreenshot();
		
		test.log(LogStatus.FAIL, "Failed with exception: "+e.getMessage());
		test.log(LogStatus.FAIL,test.addScreenCapture(TestUtils.screenshotName));
		
		
		Reporter.log("Captureing screenshot");
		Reporter.log("<a target=\"_blank\" href="+TestUtils.screenshotName+">Screenshot</a>");
		TestBase t=new TestBase();
		t.report.endTest(test);
		t.report.flush();
	}
	
}

@AfterSuite
public void  tearDown() {
	if(driver!=null) {
		driver.close();
	}
	log.debug("Test execution completed");
	log.info("Testing log");

}


public void click(String locator) {
	if(locator.endsWith("_Xpath")) {
	driver.findElement(By.xpath(OR.getProperty(locator))).click();
	}
	test.log(LogStatus.INFO, "Clicking on "+locator);
}

public void type(String locator,String value) {
	if(locator.endsWith("_Xpath")) {
	driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
	}
	test.log((LogStatus.INFO), "Typeing in "+locator+"Entered value as  "+value);
}
static WebElement dropdown;
public void select(String locator,String value) {
	
	if(locator.endsWith("_Xpath")) {
		dropdown=driver.findElement(By.xpath(OR.getProperty(locator)));
	}
	
	Select select=new  Select(dropdown);
	select.selectByVisibleText(value);
	test.log((LogStatus.INFO), "Selecting "+locator+ " with  "+value);
}

public boolean isElementPresent(By by) {
	try {
		log.debug("into try");	
		driver.findElement(by);
		return true;
	} catch (NoSuchElementException e) {
		// TODO: handle exception
log.debug("Exception caught");		
return false;
	}
	
}
}
