package herokuapp.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

import utility.WaitUtil;

public class FileDownloadPage {

    private static final Logger log = Logger.getLogger(FileDownloadPage.class);
    private WebDriver driver;

    public FileDownloadPage(WebDriver driver) {
        Reporter.log("Initializing FileDownloadPage", true);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "some-file.txt")
    private WebElement sampleFileLink;

    /**
     * Click the file download link
     */
    public void clickDownloadFile() {
        Reporter.log("Clicking file download link", true);
        WaitUtil.waitForClickable(driver, sampleFileLink).click();
        log.info("File download initiated");
    }

    /**
     * Get the download link href attribute
     */
    public String getDownloadLink() {
        String href = sampleFileLink.getAttribute("href");
        Reporter.log("Download link: " + href, true);
        return href;
    }
}
