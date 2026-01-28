package herokuapp.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

import utility.WaitUtil;

public class FileUploadPage {

    private static final Logger log = Logger.getLogger(FileUploadPage.class);
    private WebDriver driver;

    public FileUploadPage(WebDriver driver) {
        Reporter.log("Initializing FileUploadPage", true);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "file-upload")
    private WebElement fileUploadInput;

    @FindBy(id = "file-submit")
    private WebElement uploadButton;

    @FindBy(id = "uploaded-files")
    private WebElement uploadedFileText;

    /**
     * Upload file using sendKeys
     */
    public void uploadFile(String filePath) {
        Reporter.log("Uploading file: " + filePath, true);
        fileUploadInput.sendKeys(filePath);
        log.info("File selected");
    }

    /**
     * Click Upload button
     */
    public void clickUploadButton() {
        Reporter.log("Clicking Upload button", true);
        WaitUtil.waitForClickable(driver, uploadButton).click();
        WaitUtil.waitForVisibility(driver, uploadedFileText);
        log.info("Upload button clicked");
    }

    /**
     * Get uploaded filename text
     */
    public String getUploadedFileName() {
        WaitUtil.waitForVisibility(driver, uploadedFileText);
        String name = uploadedFileText.getText();
        Reporter.log("Uploaded file name: " + name, true);
        return name;
    }
}
