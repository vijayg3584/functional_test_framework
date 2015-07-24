/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vijay.functestfrmwrk;

import vijay.functestfrmwrk.exception.ExecuteTestException;
import vijay.functestfrmwrk.exception.PrepareTestException;
import vijay.functestfrmwrk.exception.VerifyTestException;
import vijay.functestfrmwrk.testdata.TestCaseInfo;

/**
 *
 * @author vijay
 */
public interface IFunctionalTest {

	public void prepareTest(TestCaseInfo testCaseInfo) throws PrepareTestException;

	public void executeTest(TestCaseInfo testCaseInfo) throws ExecuteTestException;

	public void verifyTest(TestCaseInfo testCaseInfo) throws VerifyTestException;
}
