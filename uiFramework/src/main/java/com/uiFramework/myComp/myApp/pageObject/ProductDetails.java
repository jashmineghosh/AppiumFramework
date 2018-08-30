package com.uiFramework.myComp.myApp.pageObject;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.uiFramework.myComp.myApp.helper.assertion.VerificationHelper;
import com.uiFramework.myComp.myApp.helper.logger.LoggerHelper;
import com.uiFramework.myComp.myApp.utils.GenericMethodsWithAppiumInit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductDetails extends GenericMethodsWithAppiumInit{

	private Logger log = LoggerHelper.getLogger(HomePage.class);
	private AppiumDriver<WebElement> driver;
	
//	public ProductDetails(AppiumDriver<WebElement> driver) {
//		this.driver = driver;
//		PageFactory.initElements(new AppiumFieldDecorator( driver), this);
//		
//	}
	public ProductDetails(AppiumDriver<WebElement> driver) {
		 super(driver);
		this.driver=driver;
	}
	@AndroidFindBy(id="com.ebay.mobile:id/textview_item_name")
	WebElement itemDesc;
	
	@AndroidFindBy(id="com.ebay.mobile:id/textview_item_price")
	public
	WebElement itemPrice;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text = 'About this item']")
	WebElement aboutItem;
	
	public  boolean  isProductDEtailsPageDisplayed() {
		
		return new VerificationHelper(driver).isDisplayed(aboutItem);
	}
	
	public long getItemPrice(){
		String priceWithSymbol = itemPrice.getText().trim();
		  String price = priceWithSymbol.substring(1);
		  int cost = parseStringToInt(price);
		return cost;
	}
	
	public String getItemText(){
		String text1 = itemDesc.getText();
		return text1;
	}
	public static int parseStringToInt(String s){
        s = s.replaceAll(",", ""); //remove commas
        return (int)Math.round(Double.parseDouble(s)); //return rounded double cast to int
    }
}
