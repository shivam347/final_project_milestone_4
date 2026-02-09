package com.milestone.four.listener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.testng.*;

import com.aventstack.extentreports.*;
import com.milestone.four.model.TestResultData;
import com.milestone.four.utility.*;

public class TestListener implements ITestListener {

    // ===== Extent =====
    private static ExtentReports extent =
        ExtentManager.getInstance();

    private static ThreadLocal<ExtentTest> extentTest =
        new ThreadLocal<>();

    // ===== Screenshot =====
    ScreenShotUtil shot = new ScreenShotUtil();

    // ===== Excel =====
    private static List<TestResultData> results =
        new ArrayList<>();

    // =============================

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test =
            extent.createTest(result.getName());

        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        String path =
            shot.capture(
              DriverFactory.getDriver(),
              result.getName()
            );

        extentTest.get().pass("PASSED ✅");

        if (path != null) {
            extentTest.get()
                .addScreenCaptureFromPath(path);
        }

        results.add(new TestResultData(
                result.getName(),
                "PASS",
                LocalDateTime.now().toString()
        ));
    }

    @Override
    public void onTestFailure(ITestResult result) {

        String path =
            shot.capture(
              DriverFactory.getDriver(),
              result.getName()
            );

        extentTest.get().fail(result.getThrowable());

        if (path != null) {
            extentTest.get()
                .addScreenCaptureFromPath(path);
        }

        results.add(new TestResultData(
                result.getName(),
                "FAIL",
                LocalDateTime.now().toString()
        ));
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        extentTest.get().skip("SKIPPED ⚠️");

        results.add(new TestResultData(
                result.getName(),
                "SKIP",
                LocalDateTime.now().toString()
        ));
    }

    @Override
    public void onFinish(ITestContext context) {

        // ✅ Generate Excel
        ExcelReportUtil.writeFinalReport(results);

        // ✅ Generate Extent
        extent.flush();
    }
}






