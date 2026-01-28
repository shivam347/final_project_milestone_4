package herokuapp.pagestest;


import herokuapp.basetest.BaseTest;
import utility.DriverFactory;
import herokuapp.pages.DynamicLoadingPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FT10_DynamicLoadingTest extends BaseTest {

    @Test
    public void verifyDynamicLoadingFlow() {

        // Navigate to Dynamic Loading
        DriverFactory.getDriver().findElement(By.linkText("Dynamic Loading")).click();
        DriverFactory.getDriver().findElement(By.linkText("Example 2: Element rendered after the fact")).click();

        DynamicLoadingPage page = new DynamicLoadingPage(DriverFactory.getDriver());

        // Start loading and wait for Hello World
        page.clickStartAndWaitForFinish();

        // Validate output text
        Assert.assertEquals(page.getFinishText(), "Hello World!",
                "Expected 'Hello World!' after loading is complete!");
    }
}
