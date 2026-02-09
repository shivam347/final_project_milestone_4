package com.milestone.four.utility;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

//  This will create the UI dashboard

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter reporter =
                new ExtentSparkReporter("src/main/resources/static/reports/Extent.html");

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            extent.setSystemInfo("Framework", "Milestone");
            extent.setSystemInfo("Author", "Shivam");
        }

        return extent;
    }
}