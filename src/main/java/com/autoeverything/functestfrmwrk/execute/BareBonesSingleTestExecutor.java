/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeverything.functestfrmwrk.execute;

import static com.autoeverything.functestfrmwrk.utility.ConsolePrinter.printMessageToConsole;

import com.autoeverything.functestfrmwrk.IFunctionalTest;
import com.autoeverything.functestfrmwrk.testdata.TestCaseInfo;

/**
 *
 * @author v.venugopal
 */
public class BareBonesSingleTestExecutor {

    public static void executeTest(Class<IFunctionalTest> clazz) {
        TestCaseInfo caseInfo = new TestCaseInfo();
        caseInfo.setTestClass(clazz.getName());
        executeTest(caseInfo);
    }

    public static void executeTest(TestCaseInfo testCaseInfo) {
        try {
            Class<IFunctionalTest> klass = (Class<IFunctionalTest>) Class.forName(testCaseInfo.getTestClass());
            printMessageToConsole("Klass: " + klass);
            IFunctionalTest testCase = klass.newInstance();

            testCase.prepareTest(testCaseInfo);

            testCase.executeTest(testCaseInfo);

            testCase.verifyTest(testCaseInfo);

            testCaseInfo.setTestPassed();
        } catch (Throwable ex) {
            ex.printStackTrace();
            testCaseInfo.setException(ex);
            testCaseInfo.setTestFailed();
        }
    }
}
