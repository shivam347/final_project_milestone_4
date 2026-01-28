package herokuapp.pagestest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import herokuapp.basetest.BaseTest;
import utility.DriverFactory;
import herokuapp.pages.ABTestingPage;
import herokuapp.pages.PerformancePage;
import herokuapp.pages.RenderingPage;

public class FT01_ABTestingTest extends BaseTest {

    @Test
    public void verifyHeadingText() {

        // Navigate to the page
        DriverFactory.getDriver().findElement(By.linkText("A/B Testing")).click();

        // Initialize AbTesting page object
        ABTestingPage page = new ABTestingPage(DriverFactory.getDriver());

        // Fetching heading
        String heading = page.getText();

        // verify heading matches one of the two variations
        Assert.assertTrue(heading.equals("A/B Test Control") ||
                heading.equals("A/B Test Variation 1"),
                "Unexpected Heading:  " + heading);

    }

    // Non functional Testing Method
    @Test
    public void verifyPageLoadsUnder3Seconds() {

        PerformancePage page = new PerformancePage(DriverFactory.getDriver());

        long loadTime = page.measurePageLoadTime("https://the-internet.herokuapp.com/abtest");

        Assert.assertTrue(loadTime < 3000,
                "Page took too long to load. Time: " + loadTime + " ms");
    }

   
}
