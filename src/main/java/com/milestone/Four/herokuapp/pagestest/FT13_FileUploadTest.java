package herokuapp.pagestest;


import herokuapp.basetest.BaseTest;
import utility.DriverFactory;
import herokuapp.pages.FileUploadPage;
import herokuapp.pages.PerformancePage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FT13_FileUploadTest extends BaseTest {

    @Test
    public void verifyFileUpload() {

        // Navigate
        DriverFactory.getDriver().findElement(By.linkText("File Upload")).click();

        FileUploadPage page = new FileUploadPage(DriverFactory.getDriver());

        // Path to upload file (CHANGE THIS PATH)
        // String filePath = "C:\\Users\\VIDHI YADAV\\Downloads\\Sample.txt.txt";
        String filePath = "C:\\Projects\\Sample.txt.txt";

        // Perform upload
        page.uploadFile(filePath);
        page.clickUploadButton();

        // Verify filename
        Assert.assertEquals(page.getUploadedFileName(), "Sample.txt.txt",
                "Uploaded filename is incorrect!");
    }

    @Test
    public void verifyPageLoadsUnder3Seconds() {

        PerformancePage page = new PerformancePage(DriverFactory.getDriver());

        long loadTime = page.measurePageLoadTime("https://the-internet.herokuapp.com/upload");

        Assert.assertTrue(loadTime < 3000,
                "Page took too long to load. Time: " + loadTime + " ms");
    }
}
