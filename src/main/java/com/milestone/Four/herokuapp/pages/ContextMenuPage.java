package herokuapp.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import utility.DriverFactory;
import utility.WaitUtil;


public class ContextMenuPage {

    private static final Logger log = LogManager.getLogger(ContextMenuPage.class);

    private WebDriver driver;

    public ContextMenuPage(WebDriver driver) {
        Reporter.log("Initialization of the ContextMenuPage", true);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    // find the webElement
    @FindBy(id = "hot-spot")
    private WebElement contextMenu;

    /* Method which will perform right click on the contextMenu */
    public void rightClickContextMenu() {
        Reporter.log("Performing right click on the contextMenu", true);
        Actions action = new Actions(DriverFactory.getDriver()); // Using actions from selenium
        action.contextClick(WaitUtil.waitForVisibility(driver, contextMenu)).perform();
        log.info("Right click Performed");

    }

    /* Method to handle the alert and return the text of the alert*/
    public String handleAlert(){
        Reporter.log("Started Handling the alert text", true);
        // USING ALERT
        Alert alert = driver.switchTo().alert();
        String messg = alert.getText();
        alert.accept();
        log.info("Message Received: " + messg);
        return messg;
    }


}
