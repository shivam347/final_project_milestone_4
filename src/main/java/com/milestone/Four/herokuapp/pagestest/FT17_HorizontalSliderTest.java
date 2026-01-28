package herokuapp.pagestest;


import herokuapp.basetest.BaseTest;
import utility.DriverFactory;
import herokuapp.pages.HorizontalSliderPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FT17_HorizontalSliderTest extends BaseTest {

    @Test
    public void verifySliderMovesUsingActions() {

        // Navigate
        DriverFactory.getDriver().findElement(By.linkText("Horizontal Slider")).click();

        HorizontalSliderPage page = new HorizontalSliderPage(DriverFactory.getDriver());

        String targetValue = "5";

        page.moveSliderTo(targetValue);

        Assert.assertEquals(page.getSliderValue(), targetValue,
                "Slider value is incorrect after Actions movement!");
    }
}
