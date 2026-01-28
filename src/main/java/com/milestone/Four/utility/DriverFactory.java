package utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import orangehrm.configreader.ConfigReader;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

public class DriverFactory {
    private static final Logger log= Logger.getLogger(DriverFactory.class);
    // Thread safe driver (parallel execution ke liye)
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(String browser) {
        // create logs in testng reports and sets true for printing on console.
    	Reporter.log("login test started",true);
    	Reporter.log(browser,true);
        if (browser == null) {
            browser = "chrome";
        }
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
                break;

            default:
                throw new RuntimeException("Invalid browser: " + browser);
        }

        getDriver().manage().window().maximize();
        getDriver().manage().timeouts()
        .implicitlyWait(
            Duration.ofSeconds(
                Integer.parseInt(
                    ConfigReader.get("implicit.wait")
                )
            )
        );

    }

    public static WebDriver getDriver() {
    	log.info("Taking driver object");
        return driver.get();
    }

    public static void quitDriver() {
    	log.info("Quit driver");
        if (driver.get() != null) {
            driver.get().quit();
            // removing it to get safe from memory leak
            driver.remove();
        }
    }
}