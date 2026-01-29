package com.milestone.four.herokuapp.pagestest;


import com.milestone.four.herokuapp.basetest.BaseTest;
import com.milestone.four.utility.DriverFactory;
import com.milestone.four.herokuapp.pages.DynamicLoadingPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FT10_DynamicLoadingTest extends BaseTest {

    @Test
    public void verifyDynamicLoadingFlow() {

        // Navigate to Dynamic Loading
        DriverFactory.getDriver().findElement(By.linkText("Dynamic Loading")).click();
        DriverFactory.getDriver().findElement(By.linkText("Example 2: Element rendered after the fact")).click();

        DynamicLoadingPage page = new DynamicLoadingPage(DriverFactory.getDriver());

        // Start loading and wait for Hello World
        page.clickStartAndWaitForFinish();

        // Validate output text
        Assert.assertEquals(page.getFinishText(), "Hello World!",
                "Expected 'Hello World!' after loading is complete!");
    }
}
