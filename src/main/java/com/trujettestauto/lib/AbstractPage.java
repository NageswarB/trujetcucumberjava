package com.trujettestauto.lib;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;


public abstract class AbstractPage implements ScreenDriver<WebDriver> {

	protected WebDriver driver;
	public static String permRuleOrder = null;

	public AbstractPage(final WebDriver driver) throws InterruptedException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aurea.test.scumber.selenium.pages.ScreenDriver#getDriver()
	 */
	@Override
	public WebDriver getDriver() {
		return driver;
	}

	public void moveToElement(String linkText)throws Exception{

		WebDriverWait wait = new WebDriverWait(driver, 30);

		WebElement we = null;
		try{
			we = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText)));
		}catch(Exception e){
			we = null;
		}
		moveToElement(we);
	}

	public void moveToElement(WebElement we)throws Exception{

		Actions actions = new Actions(driver);
		actions.moveToElement(we).build().perform();
	}

	public WebElement fluentWait(final By by){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		WebElement we = wait.until(new Function<WebDriver, WebElement>(){
			@Override
			public WebElement apply(WebDriver driver){
				return driver.findElement(by);
			}
		});
		return we;
	}

	public void typeEditBox(WebElement editBox,String valueToType)throws Exception{

		WebDriverWait wait = new WebDriverWait(driver, 30);

		if(wait.until(ExpectedConditions.visibilityOf(editBox)) != null){
			try{
				editBox.clear();
				editBox.sendKeys(valueToType);
			}catch(Exception e){
				//To do
			}
		}
	}

	public void selectByValueOrVisbleText(WebElement we,String valueToSelect)throws Exception{
		if(we != null){
			Select sel = new Select(we);
			try{
				sel.selectByVisibleText(valueToSelect);
			}catch(Exception e){
				sel.selectByValue(valueToSelect);
			}
		}
	}

	/**
	 * This method is to select a radio button in a webpage
	 * @param we - WebElement reference of radio button
	 * @param selectOrDeSelect - true or false , true for select and false for deselect
	 * @throws Exception
	 */
	public void selectRadioButton(WebElement we)throws Exception{
		if( we != null){
			if(!we.isSelected())
				we.click();
		}
	}
	
	/**
	 * 
	 */
	
	public void clickButton(String buttonNameOrValue)throws Exception{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String btnXpath = "//input[@id='" + buttonNameOrValue + "'] | //span[contains(text(),'" + buttonNameOrValue + "')]";
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(btnXpath)));
		if( button != null)
			button.click();
	}
}
