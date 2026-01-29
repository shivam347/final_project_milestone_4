package com.milestone.four.herokuapp.pagestest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.milestone.four.herokuapp.basetest.BaseTest;
import com.milestone.four.utility.DriverFactory;
import com.milestone.four.herokuapp.pages.DropDownPage;
// import herokuapp.pages.PerformancePage;
import com.milestone.four.herokuapp.pages.PerformancePage;

public class FT08_DropDownTest extends BaseTest {

    @Test
    public void verifyDropDown() {

        // First navigate to the page
        DriverFactory.getDriver().findElement(By.linkText("Dropdown")).click();

        // Now create the object for the page class
        DropDownPage dp = new DropDownPage(DriverFactory.getDriver());

        // Now call the method to select the text by providing the text
        dp.selectOption("Option 1");

        Assert.assertEquals(dp.getSelectedText(), "Option 1", "Expected Option 1 not found");

        dp.selectOption("Option 2");

        Assert.assertEquals(dp.getSelectedText(), "Option 2", "Expected option 2 but not found");

    }

    @Test
    public void verifyPageLoadsUnder3Seconds() {

        PerformancePage page = new PerformancePage(DriverFactory.getDriver());

        long loadTime = page.measurePageLoadTime("https://the-internet.herokuapp.com/dropdown");

        Assert.assertTrue(loadTime < 3000,
                "Page took too long to load. Time: " + loadTime + " ms");
    }

}
