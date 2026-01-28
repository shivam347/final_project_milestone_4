package herokuapp.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import utility.WaitUtil;


public class BasicAuthPage {

    public static final Logger log = LogManager.getLogger(BasicAuthPage.class);

    private WebDriver driver;

    public BasicAuthPage(WebDriver driver) {
        Reporter.log("Initializing Basic auth", true);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // finding the webElement after successfull login
    // Successful login message
    @FindBy(xpath = "//p[contains(text(), 'Congratulations')]")
    private WebElement successMsg;

    public void loginWithCredentials(String user, String pass) {
        Reporter.log("Navigating with Basic Auth credentials", true);
        String url = "https://" + user + ":" + pass + "@the-internet.herokuapp.com/basic_auth";
        driver.get(url);
        log.info("Navigated with: " + user + "/" + pass);
    }

    // Method when you login with correct credentials it shows you congratulation
    // text that we need to check
    public boolean isSuccessMessageDisplayed() {
        Reporter.log("Validating success message", true);
        try {
            WaitUtil.waitForVisibility(driver, successMsg);
            return successMsg.isDisplayed();
        } catch (Exception e) {
            log.error("Success message not found", e);
            return false;
        }
    }


    public boolean isLoginFailed(){
        //case 1 -> congratulation text not found 
        return (!driver.getPageSource().contains("Congratulations"));   
        
        // we can check the url but url remains same so avoid it 

    }

}
