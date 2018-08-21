package com.uiFramework.myComp.myApp.utils;

import org.apache.log4j.Logger;
import org.testng.Reporter;

import com.uiFramework.myComp.myApp.helper.excel.ExcelHelper;
import com.uiFramework.myComp.myApp.helper.logger.LoggerHelper;
import com.uiFramework.myComp.myApp.helper.resource.ResourceHelper;
import com.uiFramework.myComp.myApp.testBase.TestBaseRunner;

public class ExcelReadWrtite {

	public static ExcelHelper excelHelper;
	private static  Logger log = LoggerHelper.getLogger(ExcelReadWrtite.class);
	public  static Object[][] getExcelData(String excelName, String sheetName)
	 {
//		 Here we are calling the test to fetch data from excel, ExcelHelper class has the details on reading the data.
		
			String excelLocation = ResourceHelper.getResourcePath("src/main/resources/configFile/")+excelName;
			log.info("excel location "+excelLocation);
			 excelHelper = new ExcelHelper();
			Object[][] data = excelHelper.getExcelData(excelLocation, sheetName);
			return data;
		}
	 
	 public static void updateResult(String excelName, String sheetName, String testCaseName, String testStatus)
	 {
//		 Here we are calling the test to copy data into excel, ExcelHelper class has the details on updating the excel.
		 
			String excelLocation = ResourceHelper.getResourcePath("src/main/resources/configFile/")+excelName;
			Reporter.log("excel location "+excelLocation);
			 excelHelper = new ExcelHelper();
			 excelHelper.updateResult( excelLocation,  sheetName,  testCaseName,  testStatus);
			
		}
}
