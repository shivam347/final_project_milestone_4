package herokuapp.pagestest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import herokuapp.basetest.BaseTest;
import herokuapp.dataproviderheroku.DataProviderHeroku;
import utility.DriverFactory;
import herokuapp.pages.BasicAuthPage;
import herokuapp.pages.PerformancePage;

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
