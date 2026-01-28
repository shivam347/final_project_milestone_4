package herokuapp.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class PerformancePage {

    private WebDriver driver;

    public PerformancePage(WebDriver driver) {
        Reporter.log("Initializing PerformancePage", true);
        this.driver = driver;
    }

    public long measurePageLoadTime(String url) {
        long start = System.currentTimeMillis();
        driver.get(url);
        long end = System.currentTimeMillis();
        return end - start; // milliseconds
    }
}
