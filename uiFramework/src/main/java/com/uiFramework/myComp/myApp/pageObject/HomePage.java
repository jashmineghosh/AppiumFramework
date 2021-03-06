package com.uiFramework.myComp.myApp.pageObject;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.uiFramework.myComp.myApp.helper.assertion.VerificationHelper;
import com.uiFramework.myComp.myApp.helper.deviceConfig.config.ObjectReader;
import com.uiFramework.myComp.myApp.helper.logger.LoggerHelper;
import com.uiFramework.myComp.myApp.helper.wait.WaitHelper;
import com.uiFramework.myComp.myApp.utils.GenericMethodsWithAppiumInit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage extends GenericMethodsWithAppiumInit{

	
	private Logger log = LoggerHelper.getLogger(HomePage.class);
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Search for anything']")
	WebElement searchbox;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='REGISTER']")
	WebElement register;
	
	@AndroidFindBy(xpath="//android.widget.Button[contains(@text,'SIGN IN')]")
	WebElement signin;
	
	@AndroidFindBy(xpath="//*[@id='com.ebay.mobile:id/capsule_selling']")
	WebElement selling;
	
	@AndroidFindBy(xpath="//*[@id='com.ebay.mobile:id/home_pill']")
	WebElement deals;
	
	@AndroidFindBy(xpath="//*[@id='com.ebay.mobile:id/capsule_categories']")
	WebElement categories;
	
	@AndroidFindBy(xpath="//*[@id='com.ebay.mobile:id/capsule_following']")
	WebElement saved;
	
	@AndroidFindBy(className="android.widget.TextView")
	WebElement prompt;
	
	private AppiumDriver<WebElement> driver;

//	public HomePage(AppiumDriver<WebElement> driver) {
//		this.driver = driver;
//		PageFactory.initElements(new AppiumFieldDecorator( driver), this);
//		
//	}
	
	public HomePage(AppiumDriver<WebElement> driver) {
		 super(driver);
		this.driver=driver;
	}
	
public  boolean  isProductPageDisplayed() {
		
		return new VerificationHelper(driver).isDisplayed(prompt);
	}

	public SignInPage clickOnSignInLink() throws InterruptedException{
		log.info("clicked on sign in link...");
//		Thread.sleep(5000);
		WaitHelper wait = new WaitHelper(driver);
		wait.pageLoadTime(ObjectReader.reader .getPageLoadTime(), TimeUnit.SECONDS);
		GenericMethodsWithAppiumInit.clickOn(driver, signin);
//		signin.click();
		return new SignInPage(driver);
	}
	
	public void clickOnRegisterLink(){
		log.info("clicked on register link...");
		GenericMethodsWithAppiumInit.clickOn(driver, register);
//		register.click();
	}
	
	public void enterInSearchBox(String text) throws InterruptedException{
		log.info("entering some text...."+text);
//		 Thread.sleep(5000);
		new WaitHelper(driver).setImplicitWait(10, TimeUnit.SECONDS);
		GenericMethodsWithAppiumInit.enterTextValue(searchbox, text);
//		searchbox.sendKeys(text);
	}
	
	
	public void clickOnSelling(){
		log.info("clicked on selling....");
		GenericMethodsWithAppiumInit.clickOn(driver, selling);
//		selling.click();
	}
	
	public void clickOnDeals(){
		log.info("clicked on deals....");
		GenericMethodsWithAppiumInit.clickOn(driver, deals);
//		deals.click();
	}

	public void clickOnCategories(){
		log.info("clicked on categories....");
		GenericMethodsWithAppiumInit.clickOn(driver, categories);
//		categories.click();
	}
	public void clickOnSaved(){
		log.info("clicked on saved....");
		GenericMethodsWithAppiumInit.clickOn(driver, saved);
//		saved.click();
}
}
