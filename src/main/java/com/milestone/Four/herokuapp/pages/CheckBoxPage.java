package herokuapp.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import utility.WaitUtil;



public class CheckBoxPage {

    private static final Logger log = LogManager.getLogger(CheckBoxPage.class);

    private WebDriver driver;

    // Constructor
    public CheckBoxPage(WebDriver driver) {
        Reporter.log("Initialization of CheckBox Method", true);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Now we have list of checkBoxes
    @FindBy(css = "input[type = 'checkbox']")
    private List<WebElement> checkBoxes;

    /* Method to select the checkbox based on the index given */
    public void selectCheckBox(int index) {
        Reporter.log("Selecting checkBox at index : " + index, true);
        WebElement ch = checkBoxes.get(index);
        WaitUtil.waitForClickable(driver, ch).click();
        log.info("checkbox selected");
    }

    /* Method to check the checkbox is selected or not */
    public boolean selectedCheckBox(int index) {
        boolean selected = checkBoxes.get(index).isSelected();
        Reporter.log("CheckBox is selected at Index: " + index, true);

        return selected;
    }

}
