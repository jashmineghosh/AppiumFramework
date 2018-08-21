package com.uiFramework.myComp.myApp.helper.deviceConfig;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.uiFramework.myComp.myApp.helper.logger.LoggerHelper;
import com.uiFramework.myComp.myApp.testBase.TestBaseRunner;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

//This is desired capabilities setup for real device
public class SetupReal {

	private Logger log = LoggerHelper.getLogger(SetupReal.class);
	public static AppiumDriver<WebElement> driver;
	public AppiumDriver<WebElement> setUpReal() throws MalformedURLException {
	
	
	File f = new File("src");
	File fs = new File(f, "eBay.apk");
	
     DesiredCapabilities capabilities = new DesiredCapabilities();
     
     capabilities.setCapability("deviceName", "21431e6d");
     capabilities.setCapability("platformName", "Android");
     capabilities.setCapability("app", fs.getAbsolutePath());
     capabilities.setCapability("version", "7.0");
     
     driver = new AndroidDriver<WebElement >(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
     return driver;
	}	
}
