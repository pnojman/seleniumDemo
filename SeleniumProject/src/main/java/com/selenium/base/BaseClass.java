package com.selenium.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.selenium.utility.BrowserManager;
import com.selenium.utility.ExtentReportManager;

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
		
	BrowserManager.launchBrowser(driver, browserName);
		
	getDriver().manage().window().maximize();
	getDriver().manage().deleteAllCookies();
	
	getDriver().manage().timeouts().implicitlyWait
	(Duration.ofSeconds(Integer.parseInt(properties.getProperty("implicitlyWait"))));
	
	getDriver().manage().timeouts().pageLoadTimeout
	(Duration.ofSeconds(Integer.parseInt(properties.getProperty("pageLoadTimeout"))));
		
	getDriver().get(properties.getProperty("url"));
		
	}
	
}
