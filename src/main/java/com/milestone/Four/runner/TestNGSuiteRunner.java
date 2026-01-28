package com.milestone.four.runner;

import org.springframework.stereotype.Component;
import org.testng.TestNG;

import java.util.List;

@Component
public class TestNGSuiteRunner {

    public void runSuite(String suitePath) {

        System.out.println("Running TestNG suite from: " + suitePath);

        TestNG testNG = new TestNG();
        testNG.setTestSuites(List.of(suitePath));

        // Helpful for debugging
        testNG.setVerbose(2);

        // Recommended defaults
        testNG.setUseDefaultListeners(true);
        testNG.setPreserveOrder(true);

        testNG.run();
    }
}
