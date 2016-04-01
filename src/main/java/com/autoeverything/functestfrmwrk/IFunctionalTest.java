/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeverything.functestfrmwrk;

import com.autoeverything.functestfrmwrk.exception.ExecuteTestException;
import com.autoeverything.functestfrmwrk.exception.PrepareTestException;
import com.autoeverything.functestfrmwrk.exception.VerifyTestException;
import com.autoeverything.functestfrmwrk.testdata.TestCaseInfo;

/**
 *
 * @author vijay
 */
public interface IFunctionalTest {

	public void prepareTest(TestCaseInfo testCaseInfo) throws PrepareTestException;

	public void executeTest(TestCaseInfo testCaseInfo) throws ExecuteTestException;

	public void verifyTest(TestCaseInfo testCaseInfo) throws VerifyTestException;
}
