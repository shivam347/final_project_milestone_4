package utility;

import org.openqa.selenium.*;

import orangehrm.basetest.BaseTest;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShotUtil extends BaseTest {

    public void capture(WebDriver driver, String testName) {

        /* Getting web driver from the driver factory  */

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());

        String path = "./screenshots/" + testName + "_" + timestamp + ".png";

        try {
            File src = ((TakesScreenshot)driver)
                    .getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(src, new File(path));

        } catch (IOException e) {
            e.printStackTrace();
        }

        // return path;
    }
}

