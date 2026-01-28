package herokuapp.pagestest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import herokuapp.basetest.BaseTest;
import utility.DriverFactory;
import herokuapp.pages.CheckBoxPage;
import herokuapp.pages.PerformancePage;

public class FT05_CheckBoxTest extends BaseTest{

    @Test
    public void verifyCheckBoxes(){

        // First we navigate to the linktext
        DriverFactory.getDriver().findElement(By.linkText("Checkboxes")).click();

        // Now we create the object for CheckBoxPage 
        CheckBoxPage chkBox = new CheckBoxPage(DriverFactory.getDriver());

        // Now i will call the method to select the checkbox
        chkBox.selectCheckBox(0);

        // Now i will verify the seleted checkbox is selected or not
        Assert.assertTrue(chkBox.selectedCheckBox(0), "Checkbox should be selected");


    }


    @Test
    public void verifyPageLoadsUnder3Seconds() {

        PerformancePage page = new PerformancePage(DriverFactory.getDriver());

        long loadTime = page.measurePageLoadTime("https://the-internet.herokuapp.com/checkboxes");

        Assert.assertTrue(loadTime < 3000,
                "Page took too long to load. Time: " + loadTime + " ms");
    }
    
}
