/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vijay.functestfrmwrk.logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import vijay.functestfrmwrk.config.TomcatConnectionPool;
import vijay.functestfrmwrk.testdata.TestCaseInfo;
import org.apache.commons.lang.exception.ExceptionUtils;

/**
 *
 * @author vijay
 */
public class DBResultLogger {

    public void logTestResult(TestCaseInfo testCaseInfo) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = TomcatConnectionPool.getConnection();
            String query = "insert into AUTO_FUNC_TEST_LOG (AFTL_ID, AF_TEST_ID, RESULT, DESCRIPTION, EXECUTION_TIME_MS) values (SEQ_AUTO_FUNC_TEST_LOG.nextval, ?, ?, ?, ?)";
            statement = con.prepareStatement(query);
            statement.setInt(1, testCaseInfo.getId());
            statement.setString(2, testCaseInfo.getResult());
            statement.setLong(4, testCaseInfo.getActualPerformance());
            final Throwable exception = testCaseInfo.getException();

            String description = (exception == null) ? "" : getExceptionStackAsString(exception);
            if (description.length() > 4000) {
                description = description.substring(0, 3999);
            }
            statement.setString(3, description);
            statement.executeUpdate();
        } catch (SQLException ex) {
        } finally {
            TomcatConnectionPool.closeStatement(statement);
            TomcatConnectionPool.closeConnection(con);
        }

    }

    private String getExceptionStackAsString(Throwable exception) {
        return ExceptionUtils.getStackTrace(exception);
    }
}
