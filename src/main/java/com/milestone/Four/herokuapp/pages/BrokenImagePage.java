package herokuapp.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import utility.WaitUtil;

import java.util.List;

import org.apache.logging.log4j.LogManager;

public class BrokenImagePage {

    private static final Logger log = LogManager.getLogger(BrokenImagePage.class);
    private WebDriver driver;

    public BrokenImagePage(WebDriver driver) {
        Reporter.log("Initialization of Broken Image Test", true);
        this.driver = driver;
        PageFactory.initElements(driver, this); // helps us to use findby annotation
    }

    @FindBy(tagName = "img")
    private List<WebElement> images;

    public boolean allImageLoaded() {
        Reporter.log("Checking the Broken state of the images", true);

        JavascriptExecutor js = (JavascriptExecutor) driver; /*
                                                              * Through normal way selenium can not find the image is
                                                              * broken or not
                                                              * // so we use JavaScriptExecutor , it will check inside
                                                              * the browser
                                                              */

        for (WebElement img : images) { // Now we have to iterate for every image
            try {

                WaitUtil.waitForVisibility(driver, img); // first we will wait for image to load
                boolean loaded = (boolean) js /*
                                               * Here arguments0.complete means image get loaded completely and then
                                               * checking it width
                                               */
                        .executeScript("return arguments[0].complete && arguments[0].naturalWidth > 0", img);
                if(!loaded){
                    log.error("Broken image found" + img.getAttribute("src"));
                    return false;
                }

            } catch (Exception e) {
                log.error("Exception while checking broken images", e);
                return false;
            }
        }

        log.info("All images loaded successfully");
        return true;

    }

}
