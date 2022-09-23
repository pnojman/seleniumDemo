package com.selenium.actiondriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.base.BaseClass;

public class Action extends BaseClass {

	public void scrollByVisibilityOfElement(WebDriver driver, WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);

	}

	public void click(WebDriver driver, WebElement element) {

		Actions action = new Actions(driver);
		action.click(element).build().perform();

	}

	public boolean findElement(WebDriver driver, WebElement element) {
		boolean flag = false;

		try {
			element.isDisplayed();
			flag = true;
		} catch (Exception e) {
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Element successfully found");
			} else {
				System.out.println("Element not displayed");
			}
		}

		return flag;

	}

	public boolean isDisplayed(WebDriver driver, WebElement element) {

		boolean flag = false;

		flag = findElement(driver, element);

		if (flag) {
			flag = element.isDisplayed();
			if (flag) {
				System.out.println("The element is displayed");
			} else {
				System.out.println("The element is not displayed");
			}
		} else {
			System.out.println("Not displayed");
		}

		return flag;
	}

	public boolean isSelected(WebDriver driver, WebElement element) {

		boolean flag = false;

		flag = findElement(driver, element);
		if (flag) {
			flag = element.isSelected();
			if (flag) {
				System.out.println("The element is selected");
			} else {
				System.out.println("The element is not selected");
			}
		} else {
			System.out.print("Not selected");
		}

		return flag;
	}

	public boolean isEnabled(WebDriver driver, WebElement element) {

		boolean flag = false;

		flag = findElement(driver, element);
		if (flag) {
			flag = element.isEnabled();
			if (flag) {
				System.out.println("The element is enabled");
			} else {
				System.out.println("The element is not enabled");
			}
		} else {
			System.out.println("Not enabled");
		}

		return flag;
	}

	public boolean type(WebElement element, String text) {

		boolean flag = false;

		try {

			flag = element.isDisplayed();
			element.clear();
			element.sendKeys(text);
			flag = true;
		} catch (Exception e) {
			System.out.println("Location Not found");
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully entered value");
			} else {
				System.out.println("Unable to enter value");
			}
		}
		return flag;
	}

	public boolean selectByIndex(WebElement element, int index) {
		boolean flag = false;

		try {
			Select select = new Select(element);
			select.selectByIndex(index);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Option selected by index");
			} else {
				System.out.println("Option not selected by index");
			}
		}
	}

	public boolean selectByValue(WebElement element, String text) {
		boolean flag = false;

		try {
			Select select = new Select(element);
			select.selectByValue(text);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Option selected by value");
			} else {
				System.out.println("Option not selected by value");
			}
		}
	}

	public boolean selectByVisibleText(WebElement element, String visibleText) {
		boolean flag = false;

		try {
			Select select = new Select(element);
			select.selectByVisibleText(visibleText);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Option selected by visibletext");
			} else {
				System.out.println("Option not selected by visibletext");
			}
		}
	}

	public void explicitWait(WebDriver driver, Duration timeout, String css) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css)));
	}

	public void pageLoadTimeOut(WebDriver driver, int timeout) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeout));
	}

	public boolean JSClick(WebDriver driver, WebElement element) {
		boolean flag = false;

		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			flag = true;
		} catch (Exception e) {
			throw e;
		} finally {
			if (flag) {
				System.out.println("Click action is performed");
			} else {
				System.out.println("Click action is not performed");
			}
		}
		return flag;
	}

	public String screenShot(WebDriver driver, String filename) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + filename + "_" + dateName + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}

		// This new path for jenkins
		String newImageString = "http://localhost:8082/job/MyStoreProject/ws/MyStoreProject/ScreenShots/" + filename
				+ "_" + dateName + ".png";
		return newImageString;

	}

}
