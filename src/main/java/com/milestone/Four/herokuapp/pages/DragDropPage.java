package herokuapp.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

import utility.WaitUtil;

public class DragDropPage {

    private static final Logger log = Logger.getLogger(DragDropPage.class);
    private WebDriver driver;

    public DragDropPage(WebDriver driver) {
        Reporter.log("Initializing DragDropPage", true);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "column-a")
    private WebElement columnA;

    @FindBy(id = "column-b")
    private WebElement columnB;

    /**
     * Perform drag and drop of Column A → Column B
     */
    public void dragAtoB() {
        Reporter.log("Performing Drag and Drop A to B", true);

        Actions actions = new Actions(driver);

        WebElement source = WaitUtil.waitForVisibility(driver, columnA);
        WebElement target = WaitUtil.waitForVisibility(driver, columnB);

        actions.dragAndDrop(source, target).perform();

        log.info("Drag and Drop action performed");
    }

    /**
     * Returns header text of Column A after drag-drop
     */
    public String getColumnATitle() {
        String title = driver.findElement(By.xpath("//div[@id='column-a']/header")).getText();
        log.info("Column A Header: " + title);
        return title;
    }

    /**
     * Returns header text of Column B after drag-drop
     */
    public String getColumnBTitle() {
        String title = driver.findElement(By.xpath("//div[@id='column-b']/header")).getText();
        log.info("Column B Header: " + title);
        return title;
    }
}
