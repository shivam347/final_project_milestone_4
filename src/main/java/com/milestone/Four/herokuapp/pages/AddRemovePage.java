package herokuapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import utility.DriverFactory;
import utility.WaitUtil;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class AddRemovePage {

    public static final Logger log = LogManager.getLogger(AddRemovePage.class);

    private WebDriver driver;

    public AddRemovePage(WebDriver driver) {
        Reporter.log("Initializing Add Remove Page", true);
        this.driver = driver;
        PageFactory.initElements(driver, this); // helps us to use the annotation @Findby
    }

    // so we have two webElement to get one is add button and second is delete
    // button
    @FindBy(xpath = "//button[text() =  'Add Element']")
    private WebElement addButton;

    @FindBy(css = "button.added-manually")
    private List<WebElement> deleteButton;

    /* Method to check add button is clickable or not */
    public void clickAddButton() {
        Reporter.log("Initialization of Add Button", true);
        WaitUtil.waitForClickable(driver, addButton).click();
        log.info("Add button clicked");

    }

    /* Method to get the count of delete button */
    public int getdeletebuttonCount() {
        deleteButton = DriverFactory.getDriver().findElements(By.xpath("//button[text()='Delete']"));
        return deleteButton.size();
    }

    /* Method to delete all delete button
    so using while loop to get count of delete button and 
    everytime get first instance of delete button using driver then waiting for click 
    like one-by one deleting all delete buttons */
    public void deleteAllButton() {
       Reporter.log("Deleting all delete Button", true);
       while(getdeletebuttonCount() > 0){
        WebElement btn = DriverFactory.getDriver().findElements(By.xpath("//button[text()='Delete']")).get(0);
        WaitUtil.waitForClickable(driver, btn).click();
        log.info("One delete button removed");
       }

    }

}
