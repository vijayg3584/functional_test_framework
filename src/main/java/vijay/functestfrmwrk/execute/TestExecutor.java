/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vijay.functestfrmwrk.execute;

import vijay.functestfrmwrk.IFunctionalTest;
import vijay.functestfrmwrk.exception.VerifyTestException;
import vijay.functestfrmwrk.logger.DBResultLogger;
import vijay.functestfrmwrk.notify.NotificationException;
import vijay.functestfrmwrk.testdata.TestCaseInfo;
import static vijay.functestfrmwrk.utility.ConsolePrinter.printMessageToConsole;

/**
 *
 *
 */
public class TestExecutor {

    private final DBResultLogger resultLogger;

    public TestExecutor() throws NotificationException {
        resultLogger = new DBResultLogger();
    }

    @SuppressWarnings({"BroadCatchBlock", "TooBroadCatch", "UseSpecificCatch"})
    public void executeTest(TestCaseInfo testCaseInfo) {
        try {
            Class klass = Class.forName(testCaseInfo.getTestClass());
            printMessageToConsole("Klass: " + klass);
            IFunctionalTest testCase = (IFunctionalTest) klass.newInstance();

            testCase.prepareTest(testCaseInfo);

            testCaseInfo.executeStartTime();
            testCase.executeTest(testCaseInfo);
            testCaseInfo.executeFinishTime();

            verifyPerformance(testCaseInfo);

            testCase.verifyTest(testCaseInfo);

            testCaseInfo.setTestPassed();
        } catch (Throwable ex) {
            ex.printStackTrace();
            testCaseInfo.setException(ex);
            testCaseInfo.setTestFailed();
        }
        resultLogger.logTestResult(testCaseInfo);
    }

    private void verifyPerformance(TestCaseInfo caseInfo) throws VerifyTestException {
        final long expectedPerformance = caseInfo.getExpectedPerformance();
        final long actualPerformance = caseInfo.getActualPerformance();
        if (expectedPerformance > 0 && actualPerformance > expectedPerformance) {
            throw new VerifyTestException("\n Actual time taken more than expected, \n Expected time: " + expectedPerformance + "\n Actual time: " + actualPerformance);
        }
    }

}
