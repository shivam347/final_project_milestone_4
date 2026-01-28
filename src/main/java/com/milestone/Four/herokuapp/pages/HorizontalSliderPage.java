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

public class HorizontalSliderPage {

    private static final Logger log = Logger.getLogger(HorizontalSliderPage.class);
    private WebDriver driver;

    public HorizontalSliderPage(WebDriver driver) {
        Reporter.log("Initializing HorizontalSliderPage", true);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[type='range']")
    private WebElement slider;

    @FindBy(id = "range")
    private WebElement sliderValue;

    /**
     * Move slider using Actions drag and drop.
     * This method dynamically calculates the offset per step.
     */
    public void moveSliderTo(String targetValue) {

        Reporter.log("Moving slider to value (via Actions): " + targetValue, true);

        WebElement sliderElement = WaitUtil.waitForVisibility(driver, By.cssSelector("input[type='range']"));
        Actions actions = new Actions(driver);

        // Click to focus the slider
        actions.click(sliderElement).perform();

        // Parse target number
        double target = Double.parseDouble(targetValue);

        // slider steps: 0 → 5 in increments of 0.5
        int steps = (int) (target / 0.5);

        // Slider width
        int sliderWidth = sliderElement.getSize().getWidth();

        // Each step roughly equal:
        // total steps = 5 / 0.5 = 10 steps
        int stepPixel = sliderWidth / 10;

        int totalMove = steps * stepPixel;

        log.info("Slider width: " + sliderWidth +
                 " | Steps: " + steps +
                 " | Pixels per step: " + stepPixel +
                 " | Total offset: " + totalMove);

        // Perform the drag
        actions.dragAndDropBy(sliderElement, totalMove, 0).perform();

        Reporter.log("Moved slider using Actions successfully", true);
    }

    /**
     * Get slider displayed value
     */
    public String getSliderValue() {
        WaitUtil.waitForVisibility(driver, By.id("range"));
        return sliderValue.getText();
    }
}
