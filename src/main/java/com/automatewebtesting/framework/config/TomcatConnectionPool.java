/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.automatewebtesting.framework.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

/**
 *
 * @author vijay
 */
public class TomcatConnectionPool {

    private static DataSource datasource;

    static {
        PoolProperties props;
        try {
            Properties dbProperties = new Properties();
            dbProperties.load(TomcatConnectionPool.class.getResourceAsStream("Functional_Framework.properties"));
            final String dbURL = dbProperties.getProperty("DB_URL");
            final String dbUsername = dbProperties.getProperty("DB_USERNAME");
            final String dbPassword = dbProperties.getProperty("DB_PASSWORD");

            int dbMaxActive = 25;
            int dbMinIdle = 3;
            int dbInitialSize = 10;

            props = new PoolProperties();
            props.setUrl(dbURL);
            props.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            props.setUsername(dbUsername);
            props.setPassword(dbPassword);
            props.setJmxEnabled(true);
            props.setTestWhileIdle(false);
            props.setTestOnBorrow(false);
//            props.setValidationQuery("select 1 from dual");
            props.setTestOnReturn(false);
//            props.setValidationInterval(30000);
            props.setTimeBetweenEvictionRunsMillis(30000);
            props.setMaxActive(dbMaxActive);
            props.setInitialSize(dbInitialSize);
            props.setMaxWait(120000);
            props.setMaxIdle(dbInitialSize);
            props.setRemoveAbandonedTimeout(60);
            props.setMinEvictableIdleTimeMillis(50000);
            props.setMinIdle(dbMinIdle);
            props.setRemoveAbandoned(true);
            props.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
            datasource = new DataSource();
            datasource.setPoolProperties(props);

            Runtime.getRuntime().addShutdownHook(new Thread(new TomcatConnectionPool.ShutdownPool()));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return datasource.getConnection();
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void closeResultset(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    static class ShutdownPool implements Runnable {

        public void run() {
            datasource.close(true);
        }
    }
}
