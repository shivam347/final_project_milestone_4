package herokuapp.pagestest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import herokuapp.basetest.BaseTest;
import utility.DriverFactory;
import herokuapp.pages.BrokenImagePage;
import herokuapp.pages.PerformancePage;

public class FT04_BrokenImageTest extends BaseTest {

    @Test
    public void verifyBrokenImages(){

        // first navigate to the page 
        DriverFactory.getDriver().findElement(By.linkText("Broken Images")).click();

        // Now create object of BrokenImageTest class
        BrokenImagePage brokenImage = new BrokenImagePage(DriverFactory.getDriver());

        brokenImage.allImageLoaded();

        // As i want result get passed for images are broken 
        Assert.assertFalse(brokenImage.allImageLoaded(), "All images loaded Successfully");


    }

    @Test
    public void verifyPageLoadsUnder3Seconds() {

        PerformancePage page = new PerformancePage(DriverFactory.getDriver());

        long loadTime = page.measurePageLoadTime("https://the-internet.herokuapp.com/broken_images");

        Assert.assertTrue(loadTime < 3000,
                "Page took too long to load. Time: " + loadTime + " ms");
    }
    
}
