package com.uiFramework.myComp.myApp.testScripts;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.uiFramework.myComp.myApp.helper.assertion.AssertionHelper;
import com.uiFramework.myComp.myApp.helper.logger.LoggerHelper;
import com.uiFramework.myComp.myApp.helper.wait.WaitHelper;
import com.uiFramework.myComp.myApp.pageObject.ProductDetails;
import com.uiFramework.myComp.myApp.pageObject.ProductPage;
import com.uiFramework.myComp.myApp.testBase.TestBaseRunner;

public class ProductPageTest extends TestBaseRunner {

	ProductPage productpage;
	ProductDetails productdetails;
	private Logger log = LoggerHelper.getLogger(ProductPageTest.class);
	 @Test
	 public void randomItem() throws InterruptedException
	 {
//		 Here we are calling the test to click random item from the displayed list, the result is updated in the excel sheet.
//		 We are making use of assertion helper class
		 
		 productpage = new ProductPage(driver);
		 productpage.clickOnSomePrompt();
		 productdetails = productpage.clickOnRandomItem();
			boolean status = productdetails.isProductDEtailsPageDisplayed();
			AssertionHelper.updateTestStatus(status);
			TestBaseRunner.result = TestBaseRunner.passOrFail(status);
			TestBaseRunner.updateResult("testData.xlsx", "TestScripts", "randomItem", result);
	 }
	 
	 @Test
	 public void scrollAndClick() throws InterruptedException
	 {
//		 Here we are calling the test to scroll and click, the result is updated in the excel sheet.
//		 We are making use of assertion helper class
		 
		 productpage = new ProductPage(driver);
		 productpage.clickOnSomePrompt();
		 int num = productpage.numberOfItemsDisplayed();
		 log.info("number of items"  +num);
		 productdetails = productpage.clickOnLastItem();
		 boolean status = productdetails.isProductDEtailsPageDisplayed();
			AssertionHelper.updateTestStatus(status);
			TestBaseRunner.result = TestBaseRunner.passOrFail(status);
			TestBaseRunner.updateResult("testData.xlsx", "TestScripts", "scrollAndClick", result);
	 }
	 
}
