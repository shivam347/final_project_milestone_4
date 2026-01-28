package orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

import utility.WaitUtil;

import org.openqa.selenium.support.PageFactory;


public class LoginPage {
        private static final Logger log = Logger.getLogger(LoginPage.class);

        private WebDriver driver;

        public LoginPage(WebDriver driver) {
                Reporter.log("LoginPage contructor start to initialise Driver", true);
                this.driver = driver;
                PageFactory.initElements(driver, this);
        }

        @FindBy(name = "username")
        private WebElement username;

        @FindBy(name = "password")
        private WebElement password;

        @FindBy(xpath = "//button")
        private WebElement loginBtn;

        public void login(String user, String pass) {
                try {
                        Reporter.log("Login started", true);

                        WaitUtil.waitForVisibility(driver, username)
                                        .sendKeys(user);

                        WaitUtil.waitForVisibility(driver, password)
                                        .sendKeys(pass);

                        WaitUtil.waitForClickable(driver, loginBtn)
                                        .click();
                        Thread.sleep(5000);
                        log.info("Login completed");
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}
