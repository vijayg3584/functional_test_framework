/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeverything.functestfrmwrk.testdata;

import java.lang.reflect.Field;
import java.util.List;

/**
 *
 * @author vijay
 */
public class TestSuiteInfo {

    public int getTestSuiteId() {
        return testSuiteId;
    }

    public void setTestSuiteId(int testSuiteId) {
        this.testSuiteId = testSuiteId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public List<TestCaseInfo> getTestCases() {
        return testCaseList;
    }

    public void setTestCases(List<TestCaseInfo> testCaseList) {
        this.testCaseList = testCaseList;
    }

    public boolean isSuitePassed() {
        return suitePassed;
    }

    public void setSuitePassed() {
        this.suitePassed = true;
    }

    public String getResult() {
        return suitePassed ? "Passed" : "Failed";
    }

    public String getBuildVersion() {
        return buildVersion;
    }

    public void setBuildVersion(String buildVersion) {
        this.buildVersion = buildVersion;
    }

    public boolean isSendMail() {
        return sendMail;
    }

    public void setSendMail(boolean sendMail) {
        this.sendMail = sendMail;
    }

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
    private int testSuiteId;
    private String module;
    private String scenario;
    private List<TestCaseInfo> testCaseList;
    private boolean suitePassed = false;
    private String buildVersion = "NA";
    private boolean sendMail;
}
