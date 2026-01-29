package com.milestone.four.herokuapp.pagestest;


import com.milestone.four.herokuapp.basetest.BaseTest;
import com.milestone.four.utility.DriverFactory;
import com.milestone.four.herokuapp.pages.EntryAdPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FT11_EntryAdTest extends BaseTest {

    @Test
    public void verifyEntryAdPopup() {

        // Navigate to Entry Ad page
        DriverFactory.getDriver().findElement(By.linkText("Entry Ad")).click();

        EntryAdPage page = new EntryAdPage(DriverFactory.getDriver());

        // Wait for popup
        page.waitForModal();

        // Close popup
        page.closeModal();

        // Verify popup disappears
        Assert.assertTrue(page.isModalClosed(),
                "Modal popup should be closed but is still visible!");
    }
}
