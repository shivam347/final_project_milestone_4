package com.milestone.four.herokuapp.pagestest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.milestone.four.herokuapp.basetest.BaseTest;
import com.milestone.four.herokuapp.dataproviderheroku.DataProviderHeroku;
import com.milestone.four.utility.DriverFactory;
import com.milestone.four.herokuapp.pages.LoginFormPage;

public class FT16_LoginFormTest extends BaseTest {

    @Test(dataProvider = "LoginForm", dataProviderClass = DataProviderHeroku.class)
    public void verifyLoginCredentials(String user, String password, boolean expectMessage) {

        // First Navigate to the page
        DriverFactory.getDriver().findElement(By.linkText("Form Authentication")).click();

        // Now create the object of the page
        LoginFormPage page = new LoginFormPage(DriverFactory.getDriver());

        // Now pass the credentials to the username field
        page.enterUsername(user);

        // Now pass the credentials to the password field
        page.enterPassword(password);

        // Now click on the login button
        page.clickLoginButton();

        // Now check the get the message from the flash message
        String message = page.getFlashMessage();

        if (expectMessage) {

            // Now check the login successful message
            Assert.assertTrue(message.contains("You logged into a secure area!"),
                    "Expected Successfull Login but not found");

            // Now click on LogoutButton
            page.clickLogoutButton();

            // Now CALL getbacktologin
            Assert.assertTrue(page.getBackTologin(), "Logout failed");

        } else {
            Assert.assertTrue(
                    message.contains("Your username is invalid!") || message.contains("Your password is invalid!"),
                    "Expected Login but failed");
        }

    }

}
