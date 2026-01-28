package herokuapp.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import utility.WaitUtil;



public class DropDownPage {

    private static final Logger log = LogManager.getLogger(DropDownPage.class);

    // create the webDriver
    private WebDriver driver;

    // Creating the constructor
    public DropDownPage(WebDriver driver) {
        Reporter.log("Initialization of Drop-Down Page", true);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "dropdown")
    private WebElement dropDown;

    // Method to select the option
    public void selectOption(String optionText) {
        Reporter.log("Selecting the option from drop down", true);
        WebElement dp = WaitUtil.waitForVisibility(driver, dropDown);
        // create object of select
        Select select = new Select(dp);
        // pass the option the text into the method
        select.selectByVisibleText(optionText);
        log.info("Selected: " + optionText);

    }

    // Method to return the selected text
    public String getSelectedText() {
        Select select = new Select(dropDown);
        String selectedOption = select.getFirstSelectedOption().getText();

        log.info("current selected option: " + selectedOption, true);

        return selectedOption;
    }

}
