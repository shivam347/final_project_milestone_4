package com.milestone.four.utility;

import java.io.File;
import java.nio.file.Files;

import org.openqa.selenium.*;

// Now this will return the screenshot path 

public class ScreenShotUtil {

    public String capture(WebDriver driver, String name) {

        // Base directory where Extent.html lives
        String baseDir = System.getProperty("user.dir")
                + "/src/main/resources/static/reports/screenshots/";

        // Create folder if missing
        new File(baseDir).mkdirs();

        // Unique filename
        String fileName = name + "_" + System.currentTimeMillis() + ".png";

        // Full OS path (for saving file)
        String fullPath = baseDir + fileName;

        try {

            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            File dest = new File(fullPath);

            Files.copy(src.toPath(), dest.toPath());

            // ✅ Return RELATIVE path (for Extent)
            return "screenshots/" + fileName;

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    // public String capture(WebDriver driver, String name) {

    // String path = "./screenshots/" + name + ".png";

    // try {

    // File src =
    // ((TakesScreenshot) driver)
    // .getScreenshotAs(OutputType.FILE);

    // File dest = new File(path);

    // Files.copy(src.toPath(), dest.toPath());

    // return dest.getAbsolutePath(); // IMPORTANT

    // } catch (Exception e) {

    // e.printStackTrace();
    // return null;
    // }
    // }
}

// package com.milestone.four.utility;

// import org.openqa.selenium.*;

// import com.milestone.four.herokuapp.basetest.BaseTest;

// // import orangehrm.basetest.BaseTest;
// import org.apache.commons.io.FileUtils;

// import java.io.File;
// import java.io.IOException;
// import java.text.SimpleDateFormat;
// import java.util.Date;

// public class ScreenShotUtil extends BaseTest {

// public void capture(WebDriver driver, String testName) {

// /* Getting web driver from the driver factory */

// String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
// .format(new Date());

// String path = "./screenshots/" + testName + "_" + timestamp + ".png";

// try {
// File src = ((TakesScreenshot)driver)
// .getScreenshotAs(OutputType.FILE);

// FileUtils.copyFile(src, new File(path));

// } catch (IOException e) {
// e.printStackTrace();
// }

// // return path;
// }
// }
