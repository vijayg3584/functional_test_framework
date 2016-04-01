/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeverything.functestfrmwrk.execute;

import static com.autoeverything.functestfrmwrk.utility.ConsolePrinter.printMessageToConsole;

import java.sql.SQLException;
import java.util.List;

import com.autoeverything.functestfrmwrk.notify.NotificationException;
import com.autoeverything.functestfrmwrk.notify.TestResultlNotifier;
import com.autoeverything.functestfrmwrk.testdata.TestCaseInfo;
import com.autoeverything.functestfrmwrk.testdata.TestDataRetriever;
import com.autoeverything.functestfrmwrk.testdata.TestSuiteInfo;
import com.autoeverything.functestfrmwrk.testdata.utility.TestSuiteContext;

/**
 *
 *
 */
public class Executor {

    private List<TestSuiteInfo> testSuiteList;
    private final TestExecutor testExecutor;
    private final TestResultlNotifier notifier;
    private final String moduleName;
    private final TestDataRetriever testDataRetriever;
    private final String buildVersion;
    private final boolean sendMail;

    public Executor(String moduleName, String buildVersion, boolean sendMail) throws NotificationException {
        testExecutor = new TestExecutor();
        notifier = new TestResultlNotifier();
        this.moduleName = moduleName;
        this.buildVersion = buildVersion;
        this.sendMail = sendMail;
        testDataRetriever = new TestDataRetriever();
    }

    private List<TestSuiteInfo> fetchTestSuitesToExecute() throws SQLException {
        return testDataRetriever.getTestSuitesToExecute(moduleName);
    }

    public boolean executeTests() throws SQLException, NotificationException {
        testSuiteList = fetchTestSuitesToExecute();
        boolean testsPassed = false;
        for (TestSuiteInfo suiteInfo : testSuiteList) {
            suiteInfo.setBuildVersion(buildVersion);
            suiteInfo.setSendMail(sendMail);
            testsPassed = executeTestSuite(suiteInfo);
            if (!testsPassed) {
                break;
            }
        }
        return testsPassed;
    }

    private List<TestCaseInfo> fetchTestCasesToExecuteForSuite(TestSuiteInfo suiteInfo) throws SQLException {
        return testDataRetriever.getTestCasesForTestSuite(suiteInfo.getTestSuiteId());
    }

    private boolean executeTestSuite(TestSuiteInfo suiteInfo) throws NotificationException, SQLException {

        boolean testsPassed = true;
        suiteInfo.setTestCases(fetchTestCasesToExecuteForSuite(suiteInfo));

        TestSuiteContext suiteContext = null;
        try {
            suiteContext = new TestSuiteContext();
            for (TestCaseInfo testCaseInfo : suiteInfo.getTestCases()) {
                testCaseInfo.setTestSuite(suiteContext);
                testExecutor.executeTest(testCaseInfo);
                if (testCaseInfo.didTestFail()) {
                    testsPassed = false;
                    break;
                }
            }
        } finally {
            if (suiteContext != null) {
                suiteContext.dispose();
            }
        }
        if (testsPassed) {
            suiteInfo.setSuitePassed();
        }
        notifier.notifyResult(suiteInfo);
        return testsPassed;
    }

    private static <T> boolean isArrayEmpty(T[] array) {
        return (array.length == 0);
    }

    private static void displayUsageMessage() {
        printMessageToConsole("Input argument list is empty.");
        printMessageToConsole("Usage: java " + Executor.class.getName() + " <module name> from AUTO_FUNC_TEST_SUITE table. ");
    }

    private static void exitSystem(int statusCode) {
        System.exit(statusCode);
    }

    public static void main(String[] args) throws SQLException {
        int existStatus = 0;
        if (isArrayEmpty(args)) {
            displayUsageMessage();
            existStatus = 2;
        } else {
            String moduleName = args[0];
            String buildVersion = "NA";
            if (args.length == 2) {
                buildVersion = args[1];
            }
            boolean sendMailWithDeployLink = false;
            if (args.length == 3 && "Y".equalsIgnoreCase(args[2])) {
                sendMailWithDeployLink = true;
            }
            try {
                boolean passed = new Executor(moduleName, buildVersion, sendMailWithDeployLink).executeTests();
                if (!passed) {
                    existStatus = 1;
                }
            } catch (Throwable ex) {
                ex.printStackTrace();
                existStatus = 1;
            }
        }
        exitSystem(existStatus);
    }
}
