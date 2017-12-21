package com.trujettestauto.pageobjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.trujettestauto.lib.AbstractPage;


public class LoginPage extends AbstractPage {

	@Autowired
	public LoginPage(WebDriver driver) throws InterruptedException {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Log-in")
	private WebElement loginMenu;

	@FindBy(xpath = "//h1[contains(text(),'Book your flight')]")
	private WebElement bookFlightTextLabel;
	
	@FindBy(id = "Header1_btnLogin")
	private WebElement loginButton;
	
	@FindBy(id = "txtUserName")
	private WebElement userNameTextbox;
	
	@FindBy(id = "txtLPassword")
	private WebElement passwordTextbox;
	
	@FindBy(xpath = "//span[contains(text(),'Hello')]")
	private WebElement welcomeTextLabel;
	
	@FindBy(css = "span#lblErrMsg")
	private WebElement logonErrorMsgSpan;
	
	
	public void checkTrujetSiteIsLaunched()throws Exception{
		Assert.assertTrue("Trujet website not loaded properly", bookFlightTextLabel.isDisplayed());
	}
	
	public void clickOnButton(String buttonName)throws Exception{
		clickButton(buttonName);
	}
	
	public void checkLoginWindowIsOpened()throws Exception{
		Assert.assertTrue("Login window is not displayed", loginButton.isDisplayed());
	}
	
	public void enterUserName(String userName)throws Exception{
		typeEditBox(userNameTextbox, userName);
	}
	
	public void enterPassword(String password)throws Exception{
		typeEditBox(passwordTextbox, password);
	}
	
	public void verifyUserLogin()throws Exception{
		Assert.assertTrue("User login was not successful", welcomeTextLabel.isDisplayed());
	}
	
	public void clickLoginButton()throws Exception{
		loginButton.click();
	}
	
	public void verifyUserLoginFailed()throws Exception{
		String expectedText = "The user login or Password you entered is incorrect.";
		String actualErrorText = logonErrorMsgSpan.getText();
		Assert.assertEquals("User login was successful inspite of invalid creds, please check!!!", expectedText, actualErrorText);
	}
	
	public void moveToMenu(String menuName)throws Exception{
		moveToElement(menuName);
	}

	
}