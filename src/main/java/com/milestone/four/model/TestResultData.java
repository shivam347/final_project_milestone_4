package com.milestone.four.model;

public class TestResultData {  // Excel file setup

    public String testName;
    public String status;
    public String time;

    public TestResultData(String testName, String status, String time) {
        this.testName = testName;
        this.status = status;
        this.time = time;
    }
}