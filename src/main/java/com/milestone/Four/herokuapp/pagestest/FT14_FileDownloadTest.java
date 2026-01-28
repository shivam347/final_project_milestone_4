package herokuapp.pagestest;

import herokuapp.basetest.BaseTest;
import utility.DriverFactory;
import herokuapp.pages.FileDownloadPage;
import herokuapp.pages.PerformancePage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FT14_FileDownloadTest extends BaseTest {

    @Test
    public void verifyFileDownloadLink() {

        // Navigate
        DriverFactory.getDriver().findElement(By.linkText("File Download")).click();

        FileDownloadPage page = new FileDownloadPage(DriverFactory.getDriver());

        // Get link before clicking
        String downloadLink = page.getDownloadLink();

        // Validate link ends with file name
        Assert.assertTrue(downloadLink.endsWith("some-file.txt"),
                "Download link does not contain correct filename!");

        // Click file download
        page.clickDownloadFile();

        
    }

    @Test
    public void verifyPageLoadsUnder3Seconds() {

        PerformancePage page = new PerformancePage(DriverFactory.getDriver());

        long loadTime = page.measurePageLoadTime("https://the-internet.herokuapp.com/download");

        Assert.assertTrue(loadTime < 3000,
                "Page took too long to load. Time: " + loadTime + " ms");
    }
}
