package herokuapp.pagestest;


import herokuapp.basetest.BaseTest;
import utility.DriverFactory;
import herokuapp.pages.FloatingMenuPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FT15_FloatingMenuTest extends BaseTest {

    @Test
    public void verifyFloatingMenuStaysVisible() {

        // Navigate
       DriverFactory.getDriver().findElement(By.linkText("Floating Menu")).click();

        FloatingMenuPage page = new FloatingMenuPage(DriverFactory.getDriver());

        // Scroll Down
        page.scrollPage(800);
        Assert.assertTrue(page.isMenuDisplayed(), 
                "Floating menu should remain visible after scrolling down!");

        // Scroll Up
        page.scrollPage(-800);
        Assert.assertTrue(page.isMenuDisplayed(), 
                "Floating menu should remain visible after scrolling up!");
    }
}
