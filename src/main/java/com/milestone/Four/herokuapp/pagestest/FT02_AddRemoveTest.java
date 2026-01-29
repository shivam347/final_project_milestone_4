package com.milestone.four.herokuapp.pagestest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.milestone.four.herokuapp.basetest.BaseTest;
import com.milestone.four.utility.DriverFactory;
import com.milestone.four.herokuapp.pages.AddRemovePage;
import com.milestone.four.herokuapp.pages.AddRemovePage;
import com.milestone.four.herokuapp.pages.PerformancePage;

public class FT02_AddRemoveTest extends BaseTest {

    @Test
    public void verifyAddRemoveTest() {

        // first we navigate to the link
        DriverFactory.getDriver().findElement(By.linkText("Add/Remove Elements")).click();

        // create object of addRemovePage class
        AddRemovePage page = new AddRemovePage(DriverFactory.getDriver());

        // click on addbutton
        page.clickAddButton();
        page.clickAddButton();
        page.clickAddButton();
        // now check the delete button
        Assert.assertEquals(page.getdeletebuttonCount(), 3, "Delete button appeared");

        // Now call method which will delete all the Delete button
        page.deleteAllButton();
        Assert.assertEquals(page.getdeletebuttonCount(), 0, "No delete button is present");
    }

     @Test
    public void verifyPageLoadsUnder3Seconds() {

        PerformancePage page = new PerformancePage(DriverFactory.getDriver());

        long loadTime = page.measurePageLoadTime("https://the-internet.herokuapp.com/add_remove_elements/");

        Assert.assertTrue(loadTime < 3000,
                "Page took too long to load. Time: " + loadTime + " ms");
    }

}
