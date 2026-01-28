package herokuapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import utility.WaitUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/* Abtesting means when user visit to the page it gets version A or version B , there is change
in heading or text, this can be checked when you visit on two different browsers or use incognito mode
refreshing the page or visiting again from same browser will not show any variation as it 
stores information using cookies*/
public class ABTestingPage {

    private static final Logger log = LogManager.getLogger(ABTestingPage.class);

    private WebDriver driver;

    /* Constructor */
    public ABTestingPage(WebDriver driver) {
        Reporter.log("Initializing ABtesting", true);
        this.driver = driver;
        // initElements -> find all the elements annotated with @FindBy
        PageFactory.initElements(driver, this);

    }

    @FindBy(tagName = "h3")
    private WebElement heading;

    /* Method to getText of the heading */
    public String getText() {
        Reporter.log("fetching heading text for abtesting", true);
        WaitUtil.waitForVisibility(driver, heading);
        String text = heading.getText();
        log.info("Heading Text: " + text);
        return text;
    }

}