package com.uiFramework.myComp.myApp.pageObject;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


import com.uiFramework.myComp.myApp.helper.logger.LoggerHelper;
import com.uiFramework.myComp.myApp.helper.wait.WaitHelper;
import com.uiFramework.myComp.myApp.utils.GenericMethodsWithAppiumInit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductPage extends GenericMethodsWithAppiumInit{

	private Logger log = LoggerHelper.getLogger(HomePage.class);
	
	@AndroidFindBy(id="com.ebay.mobile:id/button_sort")
	WebElement sort;
	
	@AndroidFindBy(id="com.ebay.mobile:id/button_filter")
	WebElement filter;
	
	@AndroidFindBy(className="android.widget.TextView")
	WebElement prompt;
	
	@AndroidFindBy(id="com.ebay.mobile:id/textview_item_title")
	List <WebElement> itemDesc;
	
	@AndroidFindBy(id="com.ebay.mobile:id/textview_item_price")
	List <WebElement> itemPrice;
	
	@AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView(textContains(\"MX Ultra Slim LCD LED TV\"));")
	WebElement lastItem;
	
	@AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView(textContains(\"MX Heavy Duty LCD LED\"));")
	WebElement orientationCheckItem;
	
	private AppiumDriver<WebElement> driver;

//	public ProductPage(AppiumDriver<WebElement> driver) {
//		this.driver = driver;
//		PageFactory.initElements(new AppiumFieldDecorator( driver), this);
//		
//	}
	
	public ProductPage(AppiumDriver<WebElement> driver) {
		 super(driver);
		this.driver=driver;
	}
	public void clickOnSomePrompt()
	{
		GenericMethodsWithAppiumInit.clickOn(driver, prompt);
//		prompt.click();
	}
	
	public ProductDetails landscapeTest()
	{
		log.info("scroll and click on item after orientation has chnaged to landscape" );
		GenericMethodsWithAppiumInit.clickOn(driver, orientationCheckItem);
//		orientationCheckItem.click();
//		new WaitHelper(driver).pageLoadTime(30, TimeUnit.SECONDS);
		return new ProductDetails(driver);
		}
	
	public ProductDetails clickOnLastItem() throws InterruptedException
	{
		log.info("scroll and click on last item" );
		GenericMethodsWithAppiumInit.clickOn(driver, lastItem);
//		lastItem.click();
//		new WaitHelper(driver).pageLoadTime(60, TimeUnit.SECONDS);
		Thread.sleep(7000);
		return new ProductDetails(driver);
		}
	
	public  int numberOfItemsDisplayed()
	{
		log.info("numbers of items on page" +itemDesc.size());
		 int total = itemDesc.size();
		 return total;
	}
	public ProductDetails selectFirstItem()
	{
		log.debug("selecting first item...");
		GenericMethodsWithAppiumInit.clickOn(driver, itemDesc.get(0));
//		itemDesc.get(0).click();
		return new ProductDetails(driver);
	}
	
	public ProductDetails clickSelectItem(Integer num)
	{
		log.debug("clicking chosen item...");
		GenericMethodsWithAppiumInit.clickOn(driver, itemDesc.get(num));
//		itemDesc.get(num).click();
		return new ProductDetails(driver);
	}
	
	public ProductDetails clickOnRandomItem() throws InterruptedException{
		log.info("clicking on random item...");
		
		Random rand = new Random();
      int index = rand.nextInt(itemDesc.size()-1); // -1 because index will start from 0
      GenericMethodsWithAppiumInit.clickOn(driver, itemDesc.get(index));
//      itemDesc.get(index).click();
      Thread.sleep(7000);
      
		return new ProductDetails(driver);
	}
	
	public long getSelectedItemPrice(Integer num){
		String priceWithSymbol = itemPrice.get(num).getText().trim();
		  String price = priceWithSymbol.substring(1);
		  int cost = parseStringToInt(price);
		return cost;
	}
	
	public String getSelectedItemText(Integer num){
		String text1 = itemDesc.get(num).getText();
		return text1;
	}
	public static int parseStringToInt(String s){
        s = s.replaceAll(",", ""); //remove commas
        return (int)Math.round(Double.parseDouble(s)); //return rounded double cast to int
    }
}
