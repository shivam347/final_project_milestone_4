package listener;


import utility.DriverFactory;
import utility.ScreenShotUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ScreenshotListener implements ITestListener {

    private static final Logger log = LogManager.getLogger(ScreenshotListener.class);

    ScreenShotUtil shot = new ScreenShotUtil();

    @Override
    public void onTestSuccess(ITestResult result) {
        shot.capture(DriverFactory.getDriver(), result.getName() + "_PASSED");
        Reporter.log("Test PASSED", true);
        

    }

    @Override
    public void onTestFailure(ITestResult result) {
        shot.capture(DriverFactory.getDriver(), result.getName() + "_FAILED");
        Reporter.log("Test FAILED", true);
    }
}
