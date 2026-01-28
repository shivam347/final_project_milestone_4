package herokuapp.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import utility.WaitUtil;


public class LoginFormPage {

    public static final Logger log = LogManager.getLogger(LoginFormPage.class);

    private WebDriver driver;

    public LoginFormPage(WebDriver driver){
        Reporter.log("Initialization of LoginForm Page Authentication", true);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Web Element 

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = "button.radius")
    private WebElement loginButton;

    @FindBy(css = "a.button.secondary.radius")
    private WebElement logoutButton;

    @FindBy(id = "flash")
    private WebElement flashMessage;

    public void enterUsername(String username){
        // first clear the input
        WaitUtil.waitForVisibility(driver, usernameInput).clear();
        usernameInput.sendKeys(username);
    }

    // Method to enter the password into the password field
    public void enterPassword(String password){
        WaitUtil.waitForVisibility(driver, passwordInput).clear();
        passwordInput.sendKeys(password);
    }
    

    // Method to click loginButton
    public void clickLoginButton(){
        WaitUtil.waitForClickable(driver, loginButton).click();
    }

    public void clickLogoutButton(){
        WaitUtil.waitForClickable(driver, logoutButton).click();
    }

    // Method to get the string from the flash message 
    public String getFlashMessage(){
       return WaitUtil.waitForVisibility(driver, flashMessage).getText();
    }

    // Method to check the url when you logout the page
    public boolean getBackTologin(){
        return driver.getCurrentUrl().contains("login");
    }

    

    
    
}
