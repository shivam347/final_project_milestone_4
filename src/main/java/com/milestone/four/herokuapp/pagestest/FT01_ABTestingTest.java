package com.milestone.four.herokuapp.pagestest;

import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.milestone.four.herokuapp.basetest.BaseTest;
import com.milestone.four.utility.DriverFactory;
import com.milestone.four.herokuapp.pages.ABTestingPage;
import com.milestone.four.herokuapp.pages.PerformancePage;


public class FT01_ABTestingTest extends BaseTest {

    @Test
    public void verifyHeadingText() {

        // Navigate to the page
        DriverFactory.getDriver().findElement(By.linkText("A/B Testing")).click();

        // Initialize AbTesting page object
        ABTestingPage page = new ABTestingPage(DriverFactory.getDriver());

        // Fetching heading
        String heading = page.getText();

        // verify heading matches one of the two variations
        Assert.assertTrue(heading.equals("A/B Test Control") ||
                heading.equals("A/B Test Variation 1"),
                "Unexpected Heading:  " + heading);

    }

    // Non functional Testing Method
    @Test
    public void verifyPageLoadsUnder3Seconds() {

        PerformancePage page = new PerformancePage(DriverFactory.getDriver());

        long loadTime = page.measurePageLoadTime("https://the-internet.herokuapp.com/abtest");

        Assert.assertTrue(loadTime < 3000,
                "Page took too long to load. Time: " + loadTime + " ms");
    }

   
}
