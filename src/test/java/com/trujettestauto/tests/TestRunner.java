package com.trujettestauto.tests;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.cucumber.listener.ExtentCucumberFormatter;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	monochrome = true, 
	features = { "classpath:feature_files_trujet/" }, 
	glue = {"com.trujettestauto.tests" }, 
	tags = "@Login", 
	format = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},
	plugin = {"com.cucumber.listener.ExtentCucumberFormatter"})
public class TestRunner {

	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void setup() {
		// Initiates the extent report and generates the output in the
        // output/Run_<unique timestamp>/report.html file by default.
        ExtentCucumberFormatter.initiateExtentCucumberFormatter();

        // User can add the system information as follows
        ExtentCucumberFormatter.addSystemInfo("Selenium version", "v2.53.0");

        // Also you can add system information using a hash map
        final Map systemInfo = new HashMap();
        systemInfo.put("Cucumber version", "v1.2.4");
        systemInfo.put("Extent Cucumber Reporter version", "v1.1.1");
        ExtentCucumberFormatter.addSystemInfo(systemInfo);
	}

	public static void embedScreenshot(Scenario scenario, WebDriver driver) {
		try {
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
