/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vijay.functestfrmwrk.execute;

import vijay.functestfrmwrk.IFunctionalTest;
import vijay.functestfrmwrk.testdata.TestCaseInfo;
import static vijay.functestfrmwrk.utility.ConsolePrinter.printMessageToConsole;

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
