package com.uiFramework.myComp.myApp.testScripts;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.uiFramework.myComp.myApp.helper.assertion.AssertionHelper;
import com.uiFramework.myComp.myApp.helper.logger.LoggerHelper;
import com.uiFramework.myComp.myApp.helper.wait.WaitHelper;
import com.uiFramework.myComp.myApp.pageObject.ProductDetails;
import com.uiFramework.myComp.myApp.pageObject.ProductPage;
import com.uiFramework.myComp.myApp.testBase.TestBaseRunner;

public class ProductDetailsTest extends TestBaseRunner{

	ProductPage productpage;
	ProductDetails productdetails;
	private Logger log = LoggerHelper.getLogger(ProductDetailsTest.class);
	 @Test
		 public void clickSelectedItemAndCompare() throws InterruptedException
		 {
//			 Here we are calling the test to compare item selected on product display page verses product details page, the result is updated in the excel sheet.
//			 We are making use of assertion helper class
			 
			 productpage = new ProductPage(driver);
			 productpage.clickOnSomePrompt();
			 int num = productpage.numberOfItemsDisplayed();
			 log.info("number of items"  +num);
			 long itemPrice1 = productpage.getSelectedItemPrice(num-1);
			 String itemText1 = productpage.getSelectedItemText(num-1);
			 log.info("text of items on product page"  +itemText1);
			 log.info("price of items on product page"  +itemPrice1);
			 productdetails = productpage.clickSelectItem(num-1);
			 new WaitHelper(driver).waitForElement(new ProductDetails(driver).itemPrice, 15);
			 long itemPrice2 = productdetails.getItemPrice();
			 String itemText2 = productdetails.getItemText();
			 log.info("text of items on details page"  +itemText2);
			 log.info("price of items on details page"  +itemPrice2);
			 if(itemText1.equals(itemText2) && itemPrice1==(itemPrice2) )
		       {
				 AssertionHelper.markPass("matching");
		       }
		       else
		       {
		    	   AssertionHelper.markFail("not matching");
		       }
			 boolean status = productdetails.isProductDEtailsPageDisplayed();
				AssertionHelper.updateTestStatus(status);
			 	TestBaseRunner.result = TestBaseRunner.passOrFail(status);
				TestBaseRunner.updateResult("testData.xlsx", "TestScripts", "clickSelectedItemAndCompare", result);
		       }
}
