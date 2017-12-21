package com.trujettestauto.tests;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.trujettestauto.lib.AbstractCoreTest;
import com.trujettestauto.pageobjects.LoginPage;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


@ContextConfiguration(classes = { AbstractCoreTest.class })
public class StepDefinition {
	public LoginPage objLogin;

	AbstractCoreTest objCore;

	@Autowired
	protected WebDriver driver;

	@Autowired
	public Properties testProps;

	@Before
	public void setup()throws Exception{
		objLogin = new LoginPage(driver);	
	}

	@Given("^user navigates to URL : \"([^\"]*)\" application$")
	public void navigateAdminUI(String appURL)throws Exception{

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(appURL);

	}

	@Given("^that I am on Trujet landing screen$")
	public void that_I_am_on_Trujet_landing_screen() throws Throwable {
		objLogin.checkTrujetSiteIsLaunched();
	}

	@When("^I click on \"([^\"]*)\" button$")
	public void i_click_on_button(String buttonName) throws Throwable {
		objLogin.clickOnButton(buttonName);
	}

	@Then("^I should see \"([^\"]*)\" screen opens$")
	public void i_should_see_screen_opens(String arg1) throws Throwable {
	    objLogin.checkLoginWindowIsOpened();
	}

	@When("^I enter username \"([^\"]*)\"$")
	public void i_enter_username(String userName) throws Throwable {
	    objLogin.enterUserName(userName);
	}

	@When("^I enter password \"([^\"]*)\"$")
	public void i_enter_password(String password) throws Throwable {
	    objLogin.enterPassword(password);
	}

	@Then("^the user login should be successful$")
	public void the_user_login_should_be_successful() throws Throwable {
	    objLogin.verifyUserLogin();
	}
	
	@When("^I click on Login button$")
	public void i_click_on_Login_button() throws Throwable {
	    objLogin.clickLoginButton();
	}

	@Then("^the user login should fail$")
	public void the_user_login_should_fail() throws Throwable {
	    objLogin.verifyUserLoginFailed();
	}
	
	@After
	public void closeBrowser(Scenario scenario) throws InterruptedException, IOException, AWTException {
		try {
			if (scenario.isFailed()) {
				TestRunner.embedScreenshot(scenario, driver);
				driver.close();
				driver.quit();
			}
		} finally {
			Thread.sleep(5000);
			driver.quit();
		}
	}
}
