/**
 * 
 */
package com.selenium.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium.actiondriver.Action;
import com.selenium.base.BaseClass;

/**
 * @author przem
 *
 */
public class MainPage extends BaseClass {
	
	public MainPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	@FindBy(css="a[data-title='Facebook']")
	WebElement facebookLink;
	
	Action action = new Action();
	
	public boolean checkFacebookLink() {
		return action.isDisplayed(getDriver(), facebookLink);
	}
}
