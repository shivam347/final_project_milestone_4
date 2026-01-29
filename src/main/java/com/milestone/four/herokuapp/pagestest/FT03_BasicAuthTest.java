package com.milestone.four.herokuapp.pagestest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.milestone.four.herokuapp.basetest.BaseTest;
import com.milestone.four.herokuapp.dataproviderheroku.DataProviderHeroku;
import com.milestone.four.utility.DriverFactory;
import com.milestone.four.herokuapp.pages.BasicAuthPage;
import com.milestone.four.herokuapp.pages.PerformancePage;

public class FT03_BasicAuthTest extends BaseTest {

    @Test(dataProvider="BasicAuthData", dataProviderClass = DataProviderHeroku.class)
    public void verifyBasicAuth(String name, String pass, boolean isValid){

        // Navigate to the page
        DriverFactory.getDriver().findElement(By.linkText("Basic Auth")).click();

        BasicAuthPage authPage = new BasicAuthPage(DriverFactory.getDriver());

        // Now call the pom method with credentials
        authPage.loginWithCredentials(name, pass);

        if(isValid){
            Assert.assertTrue(authPage.isSuccessMessageDisplayed(), "Success expected but not found");
        }else{
             Assert.assertTrue(authPage.isLoginFailed(),
                "Congratulation expected but not found!");
        }




    }

     @Test
    public void verifyPageLoadsUnder3Seconds() {

        PerformancePage page = new PerformancePage(DriverFactory.getDriver());

        long loadTime = page.measurePageLoadTime("https://the-internet.herokuapp.com/basic_auth");

        Assert.assertTrue(loadTime < 3000,
                "Page took too long to load. Time: " + loadTime + " ms");
    }


    
}
