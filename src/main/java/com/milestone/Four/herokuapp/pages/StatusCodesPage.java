package herokuapp.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

import utility.WaitUtil;

public class StatusCodesPage {

    private static final Logger log = Logger.getLogger(StatusCodesPage.class);
    private WebDriver driver;

    public StatusCodesPage(WebDriver driver) {
        Reporter.log("Initializing StatusCodesPage", true);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Click a status code link by passing "200", "404", etc.
     */
    public void clickStatusCode(String code) {
        Reporter.log("Clicking Status Code: " + code, true);
        WebElement link = driver.findElement(By.linkText(code));
        WaitUtil.waitForClickable(driver, link).click();
        log.info("Clicked status code: " + code);
    }

    /**
     * Get result text after clicking status code
     */
    public String getStatusMessage() {
        WebElement content = driver.findElement(By.cssSelector("div.example p"));
        WaitUtil.waitForVisibility(driver, content);
        return content.getText();
    }
}
