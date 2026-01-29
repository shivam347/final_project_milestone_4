package com.milestone.four.herokuapp.pagestest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.milestone.four.herokuapp.basetest.BaseTest;
import com.milestone.four.utility.DriverFactory;
import com.milestone.four.herokuapp.pages.ContextMenuPage;

public class FT06_ContextMenuTest extends BaseTest {

    @Test
    public void verifyContextMenuAlert(){

        // first navigate to the link named as contextMenu
        DriverFactory.getDriver().findElement(By.linkText("Context Menu")).click();

        // create the object of the ContextMenu PAGE
        ContextMenuPage cmp = new ContextMenuPage(DriverFactory.getDriver());


        // call the method to do the right click
        cmp.rightClickContextMenu();

        String alert = cmp.handleAlert();

        // Validate the alert
        Assert.assertEquals(alert, "You selected a context menu", "Unexpected Message Received");

    }
    
}
