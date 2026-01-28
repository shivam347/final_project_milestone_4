package herokuapp.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import utility.DriverFactory;
import utility.WaitUtil;


public class DynamicControlPage {

    private static final Logger log = LogManager.getLogger(DynamicControlPage.class);

    private WebDriver driver;

    // The constructor
    public DynamicControlPage(WebDriver driver) {
        Reporter.log("Initialization of Dynamic Control Page", true);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Web Elements for Add/ remove checkbox

    // 1. web element is add button
    @FindBy(xpath = "//button[text() = 'Add']")
    private WebElement addButton;

    // 2. Web Element for remove button
    @FindBy(xpath = "//button[text() = 'Remove']")
    private WebElement removeButton;

    // 3. Web Element for checkbox button
    @FindBy(id = "checkbox")
    private WebElement checkBox;

    // Web Element for Enable/disable button AND INPUT FIELD
    @FindBy(xpath = "//input[@type = 'text']")
    private WebElement inputField;

    // 1. Web Element for enable Button

    @FindBy(xpath = "//button[text() = 'Enable']")
    private WebElement enableButton;

    // 2. Web Element for disable button
    @FindBy(xpath = "//button[text() = 'Disable']")
    private WebElement disableButton;

    // 3. Common web Element to check for the message
    @FindBy(id = "message")
    private WebElement message;

    // Methods for the ADD AND REMOVE CHECKBOXES

    /* Method to click add button */
    public void clickAddButton() {
        Reporter.log("Started click on add button", true);
        WaitUtil.waitForClickable(driver, addButton).click();
        log.info("Add button is clicked");
        WaitUtil.waitForVisibility(driver, checkBox);

    }

    /* Method to click on remove button */
    public void clickRemoveButton() {
        Reporter.log("Started click on remove button", true);
        WaitUtil.waitForClickable(driver, removeButton).click();
        log.info("Remove button is clicked");
        WaitUtil.waitForInvisibility(driver, By.id("checkbox"));

    }

    /* Method to check for checkbox is present or not */
    public boolean isCheckBox() {

        return DriverFactory.getDriver().findElements(By.id("checkbox")).size() > 0;

    }

    /* Method to click the Enable button */
    public void clickEnableButton() {
        Reporter.log("Start clicking on Enabled Button", true);
        WaitUtil.waitForClickable(driver, enableButton).click();
        log.info("Clicked on Enabled button");
        WaitUtil.waitForClickable(driver, inputField);
    }

    /* Method to check input field is Enabled */
    public boolean isInputEnabled() {

        return inputField.isEnabled();
    }

    /* Method to click on Disable button */
    public void clickDisabledButton() {
        Reporter.log("Start clicking on Diabled button", true);
        WaitUtil.waitForClickable(driver, disableButton).click();
        log.info("Clicked on Disabled Button");
        WaitUtil.waitForVisibility(driver, message);
    }

    // Method to return the message
    public String getMessage() {
        return message.getText();
    }

}
