package herokuapp.pages;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import utility.WaitUtil;

public class EntryAdPage {

    private static final Logger log = LogManager.getLogger(EntryAdPage.class);
    private WebDriver driver;

    public EntryAdPage(WebDriver driver) {
        Reporter.log("Initializing EntryAdPage", true);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".modal")
    private WebElement modalWindow;

    @FindBy(css = ".modal-footer p")
    private WebElement closeButton;

    /**
     * Wait for modal popup to appear
     */
    public void waitForModal() {
        Reporter.log("Waiting for modal popup", true);
        WaitUtil.waitForVisibility(driver, By.cssSelector(".modal"));
        log.info("Modal popup is visible");
    }

    /**
     * Close modal popup
     */
    public void closeModal() {
        Reporter.log("Closing modal popup", true);
        WaitUtil.waitForClickable(driver, By.cssSelector(".modal-footer p")).click();
        log.info("Modal popup closed");
    }

    /**
     * Check if modal is gone
     */
    public boolean isModalClosed() {
        Reporter.log("Checking if modal popup is closed", true);
        return WaitUtil.waitForInvisibility(driver, By.cssSelector(".modal"));
    }
}
