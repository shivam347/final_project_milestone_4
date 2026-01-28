package herokuapp.pagestest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import herokuapp.basetest.BaseTest;
import utility.DriverFactory;
import herokuapp.pages.DynamicControlPage;

public class FT09_DynamicControlTest  extends BaseTest{

    @Test
    public void verifyDynamicControls(){

        DriverFactory.getDriver().findElement(By.linkText("Dynamic Controls")).click();

        // create the object for Dynamic Controls PAGE
        DynamicControlPage dcp = new DynamicControlPage(DriverFactory.getDriver());

        /* This is used to check checkbox is not present */
        // click on remove button
        dcp.clickRemoveButton();

        // checkbox will be gone 
        Assert.assertFalse(dcp.isCheckBox(), "Expected false checkbox is not present");

        // Now check the message 
        Assert.assertEquals(dcp.getMessage(), "It's gone!", "Expected message to be displayed");


        /* This is used to add the checkbox is present */
        dcp.clickAddButton();

        Assert.assertTrue(dcp.isCheckBox(), "Expected checkbox to add but not added");

        // Now check the message
        Assert.assertEquals(dcp.getMessage(), "It's back!","Expected message to return after adding checkbox");



        /*This is used to check input field is Enabled */
        dcp.clickEnableButton();

        // now check the input field is enabled
        Assert.assertTrue(dcp.isInputEnabled());

        // Now check the message 
        Assert.assertEquals(dcp.getMessage(), "It's enabled!");

        /* This is used to check the input field is disabled  */
        dcp.clickDisabledButton();

        Assert.assertFalse(dcp.isInputEnabled());

        // Now check the message displayed
        Assert.assertEquals(dcp.getMessage(), "It's disabled!", "Expected disabled message but not found");
        







    }
 
    
}
