/**
 * 
 */
package com.selenium.utility;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author przem
 *
 */
public class BrowserManager {

	public static void launchBrowser(ThreadLocal<RemoteWebDriver> driver, String browserName) {

		if (browserName.equalsIgnoreCase("Chrome")) {

			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());

		} else if (browserName.equalsIgnoreCase("Firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());

		}
	}

}
