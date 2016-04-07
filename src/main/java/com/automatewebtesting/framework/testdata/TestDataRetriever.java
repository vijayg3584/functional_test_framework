/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.automatewebtesting.framework.testdata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.automatewebtesting.framework.config.TomcatConnectionPool;

/**
 *
 *
 */
public class TestDataRetriever {

    public List<TestSuiteInfo> getTestSuitesToExecute(String moduleName) throws SQLException {
        List<TestSuiteInfo> suiteInfoList = new ArrayList<TestSuiteInfo>();
        Connection con = null;
        Statement stmt = null;
        ResultSet testSuites = null;
        final String QUERY_RETRIEVE_TEST_SUITES_FOR_APPLICATION = "select AFTS.AF_TEST_SUITE_ID SUITE_ID, AFTS.MODULE MODULE, AFTS.SCENARIO SCENARIO , AFTS.SERIAL from AUTO_FUNC_TEST_SUITE AFTS, AUTO_FUNC_TESTSUITE_TESTS AFTT "
                + "where AFTS.AF_TEST_SUITE_ID = AFTT.AF_TEST_SUITE_ID and AFTS.RUN_SUITE = 'T' and AFTS.MODULE='" + moduleName + "' "
                + "group by (AFTS.AF_TEST_SUITE_ID , AFTS.MODULE , AFTS.SCENARIO , AFTS.SERIAL) order by AFTS.SERIAL";

        try {
            con = TomcatConnectionPool.getConnection();
            stmt = con.createStatement();
            testSuites = stmt.executeQuery(QUERY_RETRIEVE_TEST_SUITES_FOR_APPLICATION);
            TestSuiteInfo suiteInfo;
            while (testSuites.next()) {
                suiteInfo = new TestSuiteInfo();
                suiteInfo.setTestSuiteId(testSuites.getInt("SUITE_ID"));
                suiteInfo.setModule(testSuites.getString("MODULE"));
                suiteInfo.setScenario(testSuites.getString("SCENARIO"));
                suiteInfoList.add(suiteInfo);
            }

        } finally {
            TomcatConnectionPool.closeResultset(testSuites);
            TomcatConnectionPool.closeStatement(stmt);
            TomcatConnectionPool.closeConnection(con);
        }
        return Collections.unmodifiableList(suiteInfoList);
    }

    public List<TestCaseInfo> getTestCasesForTestSuite(int testSuiteId) throws SQLException {
        List<TestCaseInfo> testCases = new ArrayList<TestCaseInfo>();
        Connection con = null;
        Statement stmt = null;
        ResultSet testCasesRs = null;
        String QUERY_RETRIEVE_TESTS_FOR_TEST_SUITE = "select AFT.AF_TEST_ID ID, AFT.SCENARIO SCENARIO, AFT.TEST_CLASS CLASS, AFT.INPUT_DATA INPUT_DATA, "
                + "AFT.EXPECTED_OUTPUT EXPECTED_OUTPUT, AFT.EXPECTED_PERFORMANCE_MS EXPECTED_PERFORMANCE "
                + "from AUTO_FUNC_TEST AFT, AUTO_FUNC_TESTSUITE_TESTS AFTST where AFT.AF_TEST_ID = AFTST.AF_TEST_ID and AFT.RUN_TEST = 'T' and AFTST.AF_TEST_SUITE_ID="
                + testSuiteId + " order by AFTST.EXECUTION_ORDER";

        try {
            con = TomcatConnectionPool.getConnection();
            stmt = con.createStatement();
            testCasesRs = stmt.executeQuery(QUERY_RETRIEVE_TESTS_FOR_TEST_SUITE);
            TestCaseInfo testCase;
            while (testCasesRs.next()) {
                testCase = new TestCaseInfo();
                testCase.setId(testCasesRs.getInt("ID"));
                testCase.setScenario(testCasesRs.getString("SCENARIO"));
                testCase.setTestClass(testCasesRs.getString("CLASS"));
                testCase.setInputData(testCasesRs.getString("INPUT_DATA"));
                testCase.setExpectedOutput(testCasesRs.getString("EXPECTED_OUTPUT"));
                testCase.setExpectedPerformance(testCasesRs.getLong("EXPECTED_PERFORMANCE"));
                testCases.add(testCase);
            }

        } finally {
            TomcatConnectionPool.closeResultset(testCasesRs);
            TomcatConnectionPool.closeStatement(stmt);
            TomcatConnectionPool.closeConnection(con);
        }
        return testCases;
    }
}
