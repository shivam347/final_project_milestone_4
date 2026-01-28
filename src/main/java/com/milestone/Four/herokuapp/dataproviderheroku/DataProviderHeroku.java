package herokuapp.dataproviderheroku;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.log4testng.Logger;

public class DataProviderHeroku {

    private static final Logger log = Logger.getLogger(DataProviderHeroku.class);

    @DataProvider(name="BasicAuthData")
    public static Object[][] getBasicAuthData() {
        Reporter.log("Start Inserting Values for BasicAuth", true);

        return new Object[][] {
                { "admin", "admin", true }, // valid
                { "wrong", "admin", false }, // invalid username
                { "admin", "wrong", false }, // invalid password
                { "user", "pass", false } // fully invalid
        };

    }


    // Data provider for Login Form Authentication
    @DataProvider(name="LoginForm")
    public static Object[][] getLoginFormData(){
        Reporter.log("Starting Inserting values for Login Form", true);
        return new Object[][]{
            {"tomsmith","SuperSecretPassword!",true},
            {"tomsmith", "wrongPass", false},
            {"wrongUser", "SuperSecretPassword!", false},
            {"" , "", false}

        };
    }


    @DataProvider(name="StatusCode")
    public static Object[][] getStatusCode(){
        Reporter.log("Starting providing different code to the page", true);
        return new Object[][]{
            {"200"},
            {"301"},
            {"404"},
            {"500"}
        };
    }

}
