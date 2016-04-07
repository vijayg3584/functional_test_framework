/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.automatewebtesting.framework.execute;

import static com.automatewebtesting.framework.utility.ConsolePrinter.printMessageToConsole;

import com.automatewebtesting.framework.IFunctionalTest;
import com.automatewebtesting.framework.testdata.TestCaseInfo;

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
