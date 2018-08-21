package com.uiFramework.myComp.myApp.testScripts;

import org.testng.annotations.Test;

import com.uiFramework.myComp.myApp.pageObject.HomePage;
import com.uiFramework.myComp.myApp.pageObject.SignInPage;
import com.uiFramework.myComp.myApp.testBase.TestBaseRunner;

public class LoginTest extends TestBaseRunner{

	@Test
	public void Login() throws InterruptedException
	{
		HomePage homepage = new HomePage(driver);
		
		SignInPage signin = homepage.clickOnSignInLink();
		signin.clickSubmit();
		
	}
	 
}
