package com.milestone.four.listener;


// import utility.DriverFactory;
// import utility.ScreenShotUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.milestone.four.utility.DriverFactory;
import com.milestone.four.utility.ScreenShotUtil;

public class ScreenshotListener implements ITestListener {

    private static final Logger log =
            LogManager.getLogger(ScreenshotListener.class);

    ScreenShotUtil shot = new ScreenShotUtil();

    @Override
    public void onStart(ITestContext context) {
        log.info("Test Suite Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Test Suite Finished: " + context.getName());
        log.info("Passed: " + context.getPassedTests().size());
        log.info("Failed: " + context.getFailedTests().size());
        log.info("Skipped: " + context.getSkippedTests().size());
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        shot.capture(DriverFactory.getDriver(),
                result.getName() + "_PASSED");

        log.info("PASSED: " + result.getName());
        Reporter.log("Test PASSED: " + result.getName(), true);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        shot.capture(DriverFactory.getDriver(),
                result.getName() + "_FAILED");

        log.error("FAILED: " + result.getName(), result.getThrowable());
        Reporter.log("Test FAILED: " + result.getName(), true);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("SKIPPED: " + result.getName());
        Reporter.log("Test SKIPPED: " + result.getName(), true);
    }
}
