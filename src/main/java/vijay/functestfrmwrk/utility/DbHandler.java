/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vijay.functestfrmwrk.utility;

import java.sql.Connection;
import java.sql.SQLException;
import vijay.functestfrmwrk.config.TomcatConnectionPool;

/**
 *
 * @author vijay
 */
public class DbHandler {

    public void tearDownDBConnection(Connection connection) throws SQLException {
        if (connection != null) {
//            connection.rollback();
//            connection.setAutoCommit(true);
            TomcatConnectionPool.closeConnection(connection);
        }
    }

    public Connection setUpDbConnection() throws SQLException {
        Connection connection = TomcatConnectionPool.getConnection();
//        connection.setAutoCommit(false);

        return connection;
    }
}
