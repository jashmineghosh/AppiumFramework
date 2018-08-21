package com.uiFramework.myComp.myApp.utils;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.uiFramework.myComp.myApp.helper.logger.LoggerHelper;
import com.uiFramework.myComp.myApp.testBase.TestBaseRunner;

public class TakeSceens extends TestBaseRunner{

	private static Logger log = LoggerHelper.getLogger(TakeSceens.class);
//	public static File reportDirectory;
	
	public static String captureScreen(String fileName, WebDriver driver){
		if(driver == null){
			log.info("driver is null..");
		}
		if(fileName==""){
			fileName = "blank";
		}
		Reporter.log("captureScreen method called");
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File screFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{
			destFile = new File(reportDirectory+"/"+fileName+"_"+formater.format(calendar.getTime())+".png");
			Files.copy(screFile.toPath(), destFile.toPath());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return destFile.toString();
 }
}
