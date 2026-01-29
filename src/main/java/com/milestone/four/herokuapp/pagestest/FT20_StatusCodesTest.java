package com.milestone.four.herokuapp.pagestest;


import com.milestone.four.herokuapp.basetest.BaseTest;
import com.milestone.four.herokuapp.dataproviderheroku.DataProviderHeroku;
import com.milestone.four.utility.DriverFactory;
import com.milestone.four.herokuapp.pages.PerformancePage;
import com.milestone.four.herokuapp.pages.StatusCodesPage;

// import herokuapp.pages.StatusCodesPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FT20_StatusCodesTest extends BaseTest {

    @Test(dataProvider = "StatusCode", dataProviderClass = DataProviderHeroku.class)
    public void verifyStatusCodeMessage(String code) {

        DriverFactory.getDriver().findElement(By.linkText("Status Codes")).click();

        StatusCodesPage page = new StatusCodesPage(DriverFactory.getDriver());

        // choose a simple code
        page.clickStatusCode(code);

        String message = page.getStatusMessage();

        Assert.assertTrue(message.contains(code),
                "Status message does not contain expected code: " + code);
        
       
    }

    @Test
    public void verifyPageLoadsUnder3Seconds() {

        PerformancePage page = new PerformancePage(DriverFactory.getDriver());

        long loadTime = page.measurePageLoadTime("https://the-internet.herokuapp.com/status_codes");

        Assert.assertTrue(loadTime < 3000,
                "Page took too long to load. Time: " + loadTime + " ms");
    }
}
