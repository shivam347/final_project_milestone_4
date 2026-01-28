package herokuapp.pages;

import utility.DriverFactory;
import utility.WaitUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;




public class FloatingMenuPage {

    private static final Logger log = LogManager.getLogger(FloatingMenuPage.class);
    private WebDriver driver;

    public FloatingMenuPage(WebDriver driver) {
        Reporter.log("Initializing FloatingMenuPage", true);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "menu")
    private WebElement floatingMenu;

    /**
     * Scroll the page vertically
     */
    public void scrollPage(int pixels) {
        Reporter.log("Scrolling page by: " + pixels, true);
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        js.executeScript("window.scrollBy(0, arguments[0]);", pixels);
        log.info("Page scrolled");
    }

    /**
     * Check if floating menu is visible
     */
    public boolean isMenuDisplayed() {
        Reporter.log("Checking if floating menu is displayed", true);
        WaitUtil.waitForVisibility(driver, By.id("menu"));
        boolean displayed = floatingMenu.isDisplayed();
        log.info("Floating menu displayed: " + displayed);
        return displayed;
    }
}
