package com.milestone.four.service;

import com.milestone.four.runner.TestNGSuiteRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.nio.file.Paths;

@Service
public class TestExecutionService {

    @Autowired
    private TestNGSuiteRunner runner;

    public String executeSuite(String suiteName) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource(suiteName);

            if (resource == null) {
                throw new RuntimeException("testng.xml not found: " + suiteName);
            }

            //  IMPORTANT FIX
            String realPath = Paths.get(resource.toURI()).toFile().getAbsolutePath();

            runner.runSuite(realPath);
            return "Execution started for suite: " + suiteName;

        } catch (Exception e) {
            throw new RuntimeException("Failed to run TestNG suite", e);
        }
    }
}
