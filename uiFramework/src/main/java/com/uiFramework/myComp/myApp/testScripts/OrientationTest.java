package com.uiFramework.myComp.myApp.testScripts;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.uiFramework.myComp.myApp.helper.assertion.AssertionHelper;
import com.uiFramework.myComp.myApp.helper.logger.LoggerHelper;
import com.uiFramework.myComp.myApp.pageObject.ProductDetails;
import com.uiFramework.myComp.myApp.pageObject.ProductPage;
import com.uiFramework.myComp.myApp.testBase.TestBaseRunner;
import com.uiFramework.myComp.myApp.utils.ExcelReadWrtite;

import io.appium.java_client.AppiumDriver;

public class OrientationTest {

	private Logger log = LoggerHelper.getLogger(OrientationTest.class);
	private AppiumDriver<WebElement> driver;
	 @Test
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
		ExcelReadWrtite.updateResult("testData.xlsx", "TestScripts", "performOrientation", TestBaseRunner.result);
	 }
}
