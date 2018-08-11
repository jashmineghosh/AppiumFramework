package com.uiFramework.myComp.myApp.testScripts;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.uiFramework.myComp.myApp.helper.assertion.AssertionHelper;
import com.uiFramework.myComp.myApp.helper.wait.WaitHelper;
import com.uiFramework.myComp.myApp.pageObject.HomePage;
import com.uiFramework.myComp.myApp.testBase.TestBaseRunner;

import io.appium.java_client.PressesKeyCode;
import io.appium.java_client.android.AndroidKeyCode;

public class HomePageTest extends TestBaseRunner{

	@Test
	public void enterSearchCriteria() throws InterruptedException
	 {
//		 Here we are calling the test to enter text in search field, the result is updated in the excel sheet.
//		 We are making use of assertion helper class
		 
		 HomePage homepage = new HomePage(driver);
		 homepage.enterInSearchBox("65 inch tv");
		 ((PressesKeyCode) driver).pressKeyCode(AndroidKeyCode.ENTER);
		 new WaitHelper(driver).pageLoadTime(20, TimeUnit.SECONDS);
		 boolean status = homepage.isProductPageDisplayed();
			AssertionHelper.updateTestStatus(status);
			TestBaseRunner.result = TestBaseRunner.passOrFail(status);
			TestBaseRunner.updateResult("testData.xlsx", "TestScripts", "enterSearchCriteria", result);
	 } 
}
