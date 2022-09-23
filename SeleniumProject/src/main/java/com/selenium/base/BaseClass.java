package com.selenium.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.selenium.utility.ExtentReportManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static Properties properties;
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	@BeforeSuite(groups = {"Smoke", "Test"})
	public void beforeSuite() {
		ExtentReportManager.setExtentReport();
	
	}
	
	@AfterSuite(groups = {"Smoke", "Test"})
	public void afterSuite() {
		ExtentReportManager.endReport();
	}
	
	
	@BeforeTest(groups = {"Smoke", "Test"})	
	public void loadConfig() {
		
		try {
			properties = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "\\Configuration\\Config.properties" );
	
			properties.load(ip);
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void launchApp(String browserName) {
		
		if(browserName.equalsIgnoreCase("Chrome")) {
			
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
			
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
			
		} 
		
	getDriver().manage().window().maximize();
	getDriver().manage().deleteAllCookies();
	
	getDriver().manage().timeouts().implicitlyWait
	(Duration.ofSeconds(Integer.parseInt(properties.getProperty("implicitlyWait"))));
	
	getDriver().manage().timeouts().pageLoadTimeout
	(Duration.ofSeconds(Integer.parseInt(properties.getProperty("pageLoadTimeout"))));
		
	getDriver().get(properties.getProperty("url"));
		
	}
	
	public void setStatusOfTest(ITestResult result, ExtentTest test) {
		
		if(result.getStatus() == ITestResult.FAILURE) {
			
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " Something", ExtentColor.RED));
			
		} else if (result.getStatus() == ITestResult.SKIP) {
			
			test.log(Status.SKIP, "Skipped Test case is: " + result.getName());
			
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			
			test.log(Status.PASS, "Passed Test case is: " + result.getName());
		}
		
	}

}
