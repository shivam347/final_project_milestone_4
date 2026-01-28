package herokuapp.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.WaitUtil;

public class RenderingPage {

    private WebDriver driver;

    public RenderingPage(WebDriver driver) {
        this.driver = driver;
    }

    public long measureElementVisibilityTime(By locator) {
        long start = System.currentTimeMillis();
        WaitUtil.waitForVisibility(driver, locator);
        long end = System.currentTimeMillis();
        return end - start;
    }
}
