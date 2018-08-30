package com.uiFramework.myComp.myApp.utils;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.uiFramework.myComp.myApp.helper.logger.LoggerHelper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class GenericMethodsWithAppiumInit {

	private static Logger log = LoggerHelper.getLogger(GenericMethodsWithAppiumInit.class);
	private AppiumDriver<WebElement> driver;
	
	public GenericMethodsWithAppiumInit(AppiumDriver<WebElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator( driver), this);
		
	}

	protected static void clickOn(AppiumDriver driver,WebElement element) {
		try{
			WebElement el=element;
			el.click();
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	protected static void enterTextValue(WebElement element, String text) {
		log.info(" Entering Text Value");
		element.sendKeys(new CharSequence[] { text });
		log.info("Entered " + text + " Successfully");
	}
}
