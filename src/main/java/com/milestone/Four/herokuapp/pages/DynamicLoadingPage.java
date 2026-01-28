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

public class DynamicLoadingPage {

    private static final Logger log = LogManager.getLogger(DynamicLoadingPage.class);
    private WebDriver driver;

    public DynamicLoadingPage(WebDriver driver) {
        Reporter.log("Initializing DynamicLoadingPage", true);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(tagName = "button")
    private WebElement startButton;

    @FindBy(id = "loading")
    private WebElement loadingIcon;

    @FindBy(id = "finish")
    private WebElement finishText;

    /**
     * Click Start and wait for loading to complete
     */
    public void clickStartAndWaitForFinish() {
        Reporter.log("Clicking Start button", true);

        // Click Start
        WaitUtil.waitForClickable(driver, startButton).click();
        log.info("Start button clicked");

        // Wait for loading spinner to disappear
        Reporter.log("Waiting for loading to finish", true);
        WaitUtil.waitForInvisibility(driver, By.id("loading"));

        // Wait for finish text to appear
        Reporter.log("Waiting for finish text", true);
        WaitUtil.waitForVisibility(driver, finishText);

        log.info("'Hello World!' text is visible");
    }

    public String getFinishText() {
        return finishText.getText();
    }
}
