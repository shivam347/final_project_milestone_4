package com.milestone.four.herokuapp.basetest;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.milestone.four.herokuapp.configreader.ConfigReader;
import com.milestone.four.utility.DriverFactory;




public class BaseTest {
	protected WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Parameters("browser")
	@BeforeMethod
	public void beforeMethod(String browser) {
		Reporter.log("Browser is opening", true);
		try {
			DriverFactory.initDriver(browser);
			DriverFactory.getDriver()
					.get(ConfigReader.get("herokuapp.url"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void afterMethod() {
		Reporter.log("Browser is closing", true);
		try {
			DriverFactory.quitDriver();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
	}
}
