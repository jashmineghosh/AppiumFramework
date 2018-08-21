package com.uiFramework.myComp.myApp.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.uiFramework.myComp.myApp.helper.logger.LoggerHelper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SignInPage {

	private Logger log = LoggerHelper.getLogger(SignInPage.class);
	@AndroidFindBy(xpath="//*[@resource-id='com.ebay.mobile:id/edit_text_username']")
	WebElement uname;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.ebay.mobile:id/edit_text_password']")
	WebElement pwd;
	
	@AndroidFindBy(xpath="//android.widget.Button[contains(@text,'SIGN IN')]")
	WebElement submit;

	@AndroidFindBy(xpath="//*[contains(@text,'NO THANKS')]")
	private WebElement noThanksBtn;
	
	private AppiumDriver<WebElement> driver;

	public SignInPage(AppiumDriver<WebElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator( driver), this);
		
	}

public void clickSubmit()
{
	log.info("entering user name");
	uname.sendKeys("abc@gmail.com");
	log.info("entering password");
	pwd.sendKeys("infosys12");
	log.info("click on submit");
	submit.click();
}
}
