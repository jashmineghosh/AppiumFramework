package com.uiFramework.myComp.myApp.testScripts;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.myComp.myApp.helper.assertion.AssertionHelper;
import com.uiFramework.myComp.myApp.helper.logger.LoggerHelper;
import com.uiFramework.myComp.myApp.pageObject.HomePage;
import com.uiFramework.myComp.myApp.testBase.TestBaseRunner;
import com.uiFramework.myComp.myApp.utils.ExcelReadWrtite;

import io.appium.java_client.PressesKeyCode;
import io.appium.java_client.android.AndroidKeyCode;

public class HomePageTestDataDrivenTest extends TestBaseRunner{

	
	private Logger log = LoggerHelper.getLogger(HomePageTestDataDrivenTest.class);
	
	@DataProvider(name="testData")
	public Object[][] testData(){
		Object[][] data = ExcelReadWrtite.getExcelData("testData.xlsx", "SearchData");
		return data;
	}
	@Test(dataProvider="testData")
	public void enterSearchCriteria(String ItemDescription,  String runMode) throws InterruptedException{
		
		if(runMode.equalsIgnoreCase("n")){
			throw new SkipException("Run mode for this set of data is marked N");
		}
		HomePage homepage = new HomePage(driver);
		homepage.enterInSearchBox(ItemDescription);
		((PressesKeyCode) driver).pressKeyCode(AndroidKeyCode.ENTER);
		boolean status = homepage.isProductPageDisplayed();
		AssertionHelper.updateTestStatus(status);
		TestBaseRunner.result = TestBaseRunner.passOrFail(status);
		ExcelReadWrtite.updateResult("testData.xlsx", "TestScripts", "enterSearchCriteria", result);
	}
	
}
