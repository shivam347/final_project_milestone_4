package herokuapp.pagestest;


import herokuapp.basetest.BaseTest;
import utility.DriverFactory;
import herokuapp.pages.HoversPage;
import herokuapp.pages.PerformancePage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FT18_HoversTest extends BaseTest {

    @Test
    public void verifyHoverUserProfile() {

        DriverFactory.getDriver().findElement(By.linkText("Hovers")).click();

        HoversPage page = new HoversPage(DriverFactory.getDriver());

        // There are 3 users (index 0,1,2)
        for (int i = 0; i < 3; i++) {
            page.hoverUser(i);

            String username = page.getUserName(i);
            Assert.assertTrue(username.contains("user"), 
                    "Username not shown for user index: " + i);

            Assert.assertTrue(page.isProfileLinkVisible(i),
                    "Profile link not visible for user index: " + i);
        }
    }

    @Test
    public void verifyPageLoadsUnder3Seconds() {

        PerformancePage page = new PerformancePage(DriverFactory.getDriver());

        long loadTime = page.measurePageLoadTime("https://the-internet.herokuapp.com/hovers");

        Assert.assertTrue(loadTime < 3000,
                "Page took too long to load. Time: " + loadTime + " ms");
    }
}
