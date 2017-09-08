package com.trujettestauto.lib;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("com.tv")
public class AbstractCoreTest {

	public static WebDriverWait wait;
	public static String path;


	protected Properties testProps;


	public static Properties testProps() {
		return PropertiesLoader.getInstance().load("browser.properties");
	}

	@Bean
	@Scope("prototype")
	public WebDriver driver() {

		if (testProps().getProperty("browser").equals("chrome")) {

			return WebDriverFactory.create(WebDriverType.chrome.CHROME);
			
		} else if (testProps().getProperty("browser").equals("ie")) {

			return  WebDriverFactory.create(WebDriverType.ie.IE);

		} else {

			return WebDriverFactory.create(WebDriverType.fireFox.FIREFOX);
		}

		 }
	}
