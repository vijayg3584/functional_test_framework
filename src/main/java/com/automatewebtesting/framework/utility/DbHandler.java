/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.automatewebtesting.framework.utility;

import java.sql.Connection;
import java.sql.SQLException;

import com.automatewebtesting.framework.config.TomcatConnectionPool;

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
