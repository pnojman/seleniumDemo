/**
 * 
 */
package com.selenium.testcases;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.selenium.base.BaseClass;
import com.selenium.pageobjects.MainPage;
import com.selenium.utility.Log;

/**
 * @author przem
 *
 */
public class How2AutomateTest extends BaseClass {

	//ExtentTest test;
	private MainPage mainPage;

	@Parameters("browser")
	@BeforeMethod(groups = { "Smoke", "Test" })
	public void setup(String browser) {
		launchApp(browser);
	}

	@AfterMethod(groups = { "Smoke", "Test" })
	public void tearDown(ITestResult result) {

		//setStatusOfTest(result, test);
		getDriver().quit();
		
	}

	@Test(groups = "Smoke")
	public void checkFacebookLink() {

		Log.startTest("checkFacebookLink");

		mainPage = new MainPage();
		Log.info("Check facebook link");
		Assert.assertTrue(mainPage.checkFacebookLink());

		Log.endTest("checkFacebookLink");

	}

	@Test(groups = "Smoke")
	public void checkFacebookLink2() {

		Log.startTest("checkFacebookLink");

		mainPage = new MainPage();
		Log.info("Check facebook link");
		//Assert.assertTrue(mainPage.checkFacebookLink());
		Assert.assertTrue(false);
		Log.endTest("checkFacebookLink");

	}

}
