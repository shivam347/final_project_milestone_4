package herokuapp.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

import java.util.List;

public class InfiniteScrollPage {

    private static final Logger log = Logger.getLogger(InfiniteScrollPage.class);
    private WebDriver driver;

    public InfiniteScrollPage(WebDriver driver) {
        Reporter.log("Initializing InfiniteScrollPage", true);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".jscroll-added")
    private List<WebElement> contentBlocks;

    /**
     * Scroll down the page by a fixed amount
     */
    public void scrollDown() {
        Reporter.log("Scrolling down the page", true);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 600);");
        log.info("Page scrolled by 600px");
    }

    /**
     * Count how many blocks are loaded
     */
    public int getContentBlockCount() {
        return driver.findElements(By.cssSelector(".jscroll-added")).size();
    }
}
