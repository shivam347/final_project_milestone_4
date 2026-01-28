package herokuapp.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

import utility.WaitUtil;

import java.util.List;

public class HoversPage {

    private static final Logger log = Logger.getLogger(HoversPage.class);
    private WebDriver driver;

    public HoversPage(WebDriver driver) {
        Reporter.log("Initializing HoversPage", true);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div.figure")
    private List<WebElement> imageFigures;

    @FindBy(css = "div.figcaption h5")
    private List<WebElement> userNames;

    @FindBy(css = "div.figcaption a")
    private List<WebElement> profileLinks;

    public void hoverUser(int index) {
        Reporter.log("Hovering over user index: " + index, true);
        Actions actions = new Actions(driver);
        WebElement target = WaitUtil.waitForVisibility(driver, imageFigures.get(index));
        actions.moveToElement(target).perform();
        log.info("Hovered user #" + (index + 1));
    }

    public String getUserName(int index) {
        return WaitUtil.waitForVisibility(driver, userNames.get(index)).getText();
    }

    public boolean isProfileLinkVisible(int index) {
        return WaitUtil.waitForVisibility(driver, profileLinks.get(index)).isDisplayed();
    }
}
