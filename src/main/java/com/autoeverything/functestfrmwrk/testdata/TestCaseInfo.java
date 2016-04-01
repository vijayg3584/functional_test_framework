/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeverything.functestfrmwrk.testdata;

import java.lang.reflect.Field;

import com.autoeverything.functestfrmwrk.exception.VerifyTestException;
import com.autoeverything.functestfrmwrk.testdata.utility.TestSuiteContext;

/**
 *
 * @author vijay
 */
public class TestCaseInfo {

    private enum TestResults {

        PASSED("Passed"),
        FAILED("Failed"),
        NOT_STARTED("Not Started");

        private TestResults(String status) {
            this.status = status;
        }

        private final String status;

        @Override
        public String toString() {
            return status;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public long getExpectedPerformance() {
        return expectedPerformance;
    }

    public void setExpectedPerformance(long expectedPerformance) {
        this.expectedPerformance = expectedPerformance;
    }

    public String getTestClass() {
        return testClass;
    }

    public void setTestClass(String testClass) {
        this.testClass = testClass;
    }

    public long getActualPerformance() {
        return (executeFinishTime - executeStartTime);
    }

    public boolean didTestFail() {
        return testResult == TestResults.FAILED;
    }

    public void setTestPassed() {
        this.testResult = TestResults.PASSED;
    }

    public void setTestFailed() {
        this.testResult = TestResults.FAILED;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    public void executeStartTime() {
        this.executeStartTime = System.currentTimeMillis();
    }

    public void executeFinishTime() throws VerifyTestException {
        this.executeFinishTime = System.currentTimeMillis();
    }

    public String getResult() {
        return testResult.toString();
    }

    public TestSuiteContext getTestSuite() {
        return testSuite;
    }

    public void setTestSuite(TestSuiteContext testSuite) {
        this.testSuite = testSuite;
    }

    public TestCaseInfo() {
        clearValues();
    }

    private void clearValues() {
        id = -1;
        inputData = null;
        expectedOutput = null;
        application = null;
        scenario = null;
        expectedPerformance = -1;
        testClass = null;
        testResult = TestResults.NOT_STARTED;
        exception = null;
        executeStartTime = -1;
        executeFinishTime = -1;
    }

    private int id;
    private String inputData;
    private String expectedOutput;
    private String application;
    private String scenario;
    private long expectedPerformance;
    private String testClass;
    private TestResults testResult;
    private Throwable exception;
    private long executeStartTime;
    private long executeFinishTime;
    private TestSuiteContext testSuite;

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("");
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                string.append(field.getName()).append(" : ")
                        .append(field.get(this)).append("\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return string.toString();
    }

}
