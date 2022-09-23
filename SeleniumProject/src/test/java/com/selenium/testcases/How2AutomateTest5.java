/**
 * 
 */
package com.selenium.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.selenium.base.BaseClass;
import com.selenium.pageobjects.MainPage;
import com.selenium.utility.Log;

/**
 * @author przem
 *
 */
public class How2AutomateTest5 extends BaseClass {
	
	ExtentTest test;
	MainPage mainPage;
	
	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke", "Test"})
	public void setup(String browser) {
		launchApp(browser);
	}
	
	
	@AfterMethod(groups = {"Smoke", "Test"})
	public void tearDown() {
		getDriver().close();
	}
	
	@Test(groups = {"Smoke", "Test"})
	public void checkFacebookLink() {
		
		Log.startTest("checkFacebookLink");
		
		mainPage = new MainPage();
		Log.info("Check facebook link");
		Assert.assertTrue(mainPage.checkFacebookLink());
		
		Log.endTest("checkFacebookLink");
		
	}

}
