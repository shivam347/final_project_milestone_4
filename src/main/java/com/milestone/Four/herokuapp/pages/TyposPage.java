package herokuapp.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

import utility.WaitUtil;

public class TyposPage {

    private static final Logger log = Logger.getLogger(TyposPage.class);
    private WebDriver driver;

    public TyposPage(WebDriver driver) {
        Reporter.log("Initializing TyposPage", true);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div.example p")
    private WebElement paragraph;

    public String getParagraphText() {
        return WaitUtil.waitForVisibility(driver, paragraph).getText();
    }
}
