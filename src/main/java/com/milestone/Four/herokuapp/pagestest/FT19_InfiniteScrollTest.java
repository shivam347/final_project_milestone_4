package herokuapp.pagestest;

import herokuapp.basetest.BaseTest;
import utility.DriverFactory;
import herokuapp.pages.InfiniteScrollPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FT19_InfiniteScrollTest extends BaseTest {

    @Test
    public void verifyInfiniteScrollLoadsMoreContent() {

        DriverFactory.getDriver().findElement(By.linkText("Infinite Scroll")).click();

        InfiniteScrollPage page = new InfiniteScrollPage(DriverFactory.getDriver());

        int initialCount = page.getContentBlockCount();

        // Scroll multiple times
        for (int i = 0; i < 4; i++) {
            page.scrollDown();
            try {
                Thread.sleep(1000); // brief wait for content to load (safe for AJAX)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int finalCount = page.getContentBlockCount();

        Assert.assertTrue(finalCount > initialCount,
                "Content blocks did not increase after scrolling!");
    }
}
