package com.uiFramework.myComp.myApp.testBase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.uiFramework.myComp.myApp.helper.assertion.AssertionHelper;
import com.uiFramework.myComp.myApp.helper.deviceConfig.DeviceType;
import com.uiFramework.myComp.myApp.helper.deviceConfig.SetupReal;
import com.uiFramework.myComp.myApp.helper.deviceConfig.config.ObjectReader;
import com.uiFramework.myComp.myApp.helper.deviceConfig.config.PropertyReader;
import com.uiFramework.myComp.myApp.helper.excel.ExcelHelper;
import com.uiFramework.myComp.myApp.helper.wait.WaitHelper;
import com.uiFramework.myComp.myApp.helper.logger.LoggerHelper;
import com.uiFramework.myComp.myApp.helper.resource.ResourceHelper;
import com.uiFramework.myComp.myApp.pageObject.HomePage;
import com.uiFramework.myComp.myApp.pageObject.ProductDetails;
import com.uiFramework.myComp.myApp.pageObject.ProductPage;
import com.uiFramework.myComp.myApp.utils.ExcelReadWrtite;
import com.uiFramework.myComp.myApp.utils.TakeSceens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PressesKeyCode;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class TestBaseRunner {

private Logger log = LoggerHelper.getLogger(TestBaseRunner.class);
public static String result;
public static File reportDirectory;	
public   AppiumDriver<WebElement> driver ;
HomePage homepage;
ProductPage productpage;
ProductDetails productDetails;
//public static ExcelReadWrtite excelrw ;
 @BeforeTest
 public void setUp() throws Exception
 {
//	 Get the .apk file and setup desired capabilities, which is required test for every other test execution, in any suite file or class
//	 	File f = new File("src");
//		File fs = new File(f, "eBay.apk");
//		
//         DesiredCapabilities capabilities = new DesiredCapabilities();
//         capabilities.setCapability("deviceName", "21431e6d");
//         capabilities.setCapability("platformName", "Android");
//         capabilities.setCapability("app", fs.getAbsolutePath());
//         capabilities.setCapability("version", "7.0");
//         
//         driver = new AndroidDriver<WebElement >(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	
	 ObjectReader.reader  = new PropertyReader(); //Initializing the instance of config reader
	 setupDriver(ObjectReader.reader.getDeviceType()); //This will return the instance of driver
	 
//         Create screenshot folder where all screenshots are captured after every method
         reportDirectory = new File(ResourceHelper.getResourcePath("src/main/resources/screenShots"));
 }
 
 public AppiumDriver<WebElement> getDeviceObject(DeviceType dtype) throws Exception{
		
		try{
			switch(dtype){
			case Real:
				
				SetupReal real = SetupReal.class.newInstance();
				return real.setUpReal();		
				 
//			case Emulator:
				
				 
			default:
				throw new Exception("Driver not Found: "+dtype.name());
			}
		}
		catch(Exception e){
			log.info(e.getMessage());
//			throw e;  //either throw or return null as below
		}
		return null;
		
}
 
 public void setupDriver(DeviceType dtype) throws Exception
 {
 	 driver = getDeviceObject(dtype);
 	log.info("Initialize Appium driver: "+driver.hashCode());
// 	WaitHelper wait = new WaitHelper(driver);
// 	wait.setImplicitWait(ObjectReader.reader .getExplicitWait(), TimeUnit.SECONDS);
// 	wait.pageLoadTime(ObjectReader.reader .getPageLoadTime(), TimeUnit.SECONDS);
 	 
 			}

 @BeforeMethod(description = "capture the name of method called")
	public void beforeMethod(Method methodname) {  
		log.info(methodname.getName()+"....test started...");
	}
 
 @AfterMethod(description = "capture the screenshot of the method called")
	public void afterMethod(ITestResult result) throws IOException 
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{
			log.error(result.getName());
			log.error(result.getThrowable());
			String imagPath = TakeSceens.captureScreen(result.getTestName(), driver);
			
		}
		else if(result.getStatus() == ITestResult.SUCCESS){
			log.info( result.getName()+" is pass");
			String imagPath = TakeSceens.captureScreen(result.getTestName(), driver);
		}
		else if(result.getStatus() == ITestResult.SKIP){
			log.info( result.getThrowable());
		}
		log.info("**************"+result.getName()+"Finished***************");
	}
 
// @Test(priority = 0)
 public void enterSearchCriteria() throws InterruptedException
 {
//	 Here we are calling the test to enter text in search field, the result is updated in the excel sheet.
//	 We are making use of assertion helper class
	 
	 homepage = new HomePage(driver);
	 homepage.enterInSearchBox("65 inch tv");
	 ((PressesKeyCode) driver).pressKeyCode(AndroidKeyCode.ENTER);
	 new WaitHelper(driver).pageLoadTime(20, TimeUnit.SECONDS);
	 boolean status = homepage.isProductPageDisplayed();
		AssertionHelper.updateTestStatus(status);
		TestBaseRunner.result = TestBaseRunner.passOrFail(status);
		ExcelReadWrtite.updateResult("testData.xlsx", "TestScripts", "enterSearchCriteria", result);
 } 

// @Test(dependsOnMethods = { "enterSearchCriteria" })
 public void randomItem() throws InterruptedException
 {
//	 Here we are calling the test to click random item from the displayed list, the result is updated in the excel sheet.
//	 We are making use of assertion helper class
	 
	 productpage = new ProductPage(driver);
	 productpage.clickOnSomePrompt();
	 productDetails = productpage.clickOnRandomItem();
		boolean status = productDetails.isProductDEtailsPageDisplayed();
		AssertionHelper.updateTestStatus(status);
		TestBaseRunner.result = TestBaseRunner.passOrFail(status);
		ExcelReadWrtite.updateResult("testData.xlsx", "TestScripts", "randomItem", result);
 }
 
// @Test(priority = 1)
 public void clickSelectedItemAndCompare() throws InterruptedException
 {
//	 Here we are calling the test to compare item selected on product display page verses product details page, the result is updated in the excel sheet.
//	 We are making use of assertion helper class
	 
	 productpage = new ProductPage(driver);
	 productpage.clickOnSomePrompt();
	 int num = productpage.numberOfItemsDisplayed();
	 log.info("number of items"  +num);
	 long itemPrice1 = productpage.getSelectedItemPrice(num-1);
	 String itemText1 = productpage.getSelectedItemText(num-1);
	 log.info("text of items"  +itemText1);
	 log.info("price of items"  +itemPrice1);
	 productDetails = productpage.clickSelectItem(num-1);
	 new WaitHelper(driver).waitForElement(new ProductDetails(driver).itemPrice, 15);
	 long itemPrice2 = productDetails.getItemPrice();
	 String itemText2 = productDetails.getItemText();
	 log.info("text of items"  +itemText2);
	 log.info("price of items"  +itemPrice2);
	 if(itemText1.equals(itemText2) && itemPrice1==(itemPrice2) )
       {
		 AssertionHelper.markPass("matching");
       }
       else
       {
    	   AssertionHelper.markFail("not matching");
       }
	 boolean status = productDetails.isProductDEtailsPageDisplayed();
		AssertionHelper.updateTestStatus(status);
	 	TestBaseRunner.result = TestBaseRunner.passOrFail(status);
	 	ExcelReadWrtite.updateResult("testData.xlsx", "TestScripts", "clickSelectedItemAndCompare", result);
       }
// @Test
 public void scrollAndClick() throws InterruptedException
 {
//	 Here we are calling the test to scroll and click, the result is updated in the excel sheet.
//	 We are making use of assertion helper class
	 
	 productpage = new ProductPage(driver);
	 productpage.clickOnSomePrompt();
	 int num = productpage.numberOfItemsDisplayed();
	 log.info("number of items"  +num);
	 productDetails = productpage.clickOnLastItem();
	 boolean status = productDetails.isProductDEtailsPageDisplayed();
		AssertionHelper.updateTestStatus(status);
		TestBaseRunner.result = TestBaseRunner.passOrFail(status);
		ExcelReadWrtite.updateResult("testData.xlsx", "TestScripts", "scrollAndClick", result);
 }
 
// @Test
 public void performOrientation() throws InterruptedException {
  //Get and print current screen orientation.
  System.out.println("*--*--*-- Current screen orientation Is : " + driver.getOrientation());
  System.out.println("*--*--*-- Changing screen Orientation to LANDSCAPE.");
  //Changing screen Orientation to LANDSCAPE.
  driver.rotate(org.openqa.selenium.ScreenOrientation.LANDSCAPE);
  //Get and print screen orientation after changing It.
  System.out.println("*--*--*-- Now screen orientation Is : "+ driver.getOrientation());
  Thread.sleep(5000);
  // Scroll to get element which was visible on screen for portrait mode
  ProductDetails productDetails = new ProductPage(driver).landscapeTest();
   
  System.out.println("*--*--*-- Changing screen Orientation to PORTRAIT.");
  //Changing screen Orientation to PORTRAIT.
  driver.rotate(org.openqa.selenium.ScreenOrientation.PORTRAIT);
  //Get and print screen orientation after changing It.
  System.out.println("*--*--*-- Now screen orientation Is : "+ driver.getOrientation());
  Thread.sleep(5000);
  boolean status = productDetails.isProductDEtailsPageDisplayed();
	AssertionHelper.updateTestStatus(status);
	TestBaseRunner.result = TestBaseRunner.passOrFail(status);
	ExcelReadWrtite.updateResult("testData.xlsx", "TestScripts", "performOrientation", result);
 }

// public String captureScreen(String fileName, WebDriver driver){
//		if(driver == null){
//			log.info("driver is null..");
//		}
//		if(fileName==""){
//			fileName = "blank";
//		}
//		Reporter.log("captureScreen method called");
//		File destFile = null;
//		Calendar calendar = Calendar.getInstance();
//		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
//		File screFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		try{
//			destFile = new File(reportDirectory+"/"+fileName+"_"+formater.format(calendar.getTime())+".png");
//			Files.copy(screFile.toPath(), destFile.toPath());
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		return destFile.toString();
// }
 
/* public Object[][] getExcelData(String excelName, String sheetName)
 {
//	 Here we are calling the test to fetch data from excel, ExcelHelper class has the details on reading the data.
	
		String excelLocation = ResourceHelper.getResourcePath("src/main/resources/configFile/")+excelName;
		log.info("excel location "+excelLocation);
		ExcelHelper excelHelper = new ExcelHelper();
		Object[][] data = excelHelper.getExcelData(excelLocation, sheetName);
		return data;
	}
 
 public static void updateResult(String excelName, String sheetName, String testCaseName, String testStatus)
 {
//	 Here we are calling the test to copy data into excel, ExcelHelper class has the details on updating the excel.
	 
		String excelLocation = ResourceHelper.getResourcePath("src/main/resources/configFile/")+excelName;
		Reporter.log("excel location "+excelLocation);
		ExcelHelper excelHelper = new ExcelHelper();
		 excelHelper.updateResult( excelLocation,  sheetName,  testCaseName,  testStatus);
		
	}*/
 
 public  static String passOrFail(Boolean status)
 {
//	 This is Generic method to update pass or fail status
	 if(status) {
		 result = "pass";
	}
	else
	{
		 result = "fail";
	}
	 return result;
 }
 @AfterTest 
 public void afterTest() {
     if (driver != null) {
         driver.quit();
     } 
 }
}

