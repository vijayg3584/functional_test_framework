/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.automatewebtesting.framework;

import com.automatewebtesting.framework.exception.ExecuteTestException;
import com.automatewebtesting.framework.exception.PrepareTestException;
import com.automatewebtesting.framework.exception.VerifyTestException;
import com.automatewebtesting.framework.testdata.TestCaseInfo;

/**
 *
 * @author vijay
 */
public interface IFunctionalTest {

	public void prepareTest(TestCaseInfo testCaseInfo) throws PrepareTestException;

	public void executeTest(TestCaseInfo testCaseInfo) throws ExecuteTestException;

	public void verifyTest(TestCaseInfo testCaseInfo) throws VerifyTestException;
}
