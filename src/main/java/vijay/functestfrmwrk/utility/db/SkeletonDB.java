/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vijay.functestfrmwrk.utility.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import vijay.functestfrmwrk.utility.Configuration;

/**
 *
 * @author vinodyadav
 */
public class SkeletonDB {

    public static Connection getConnection() throws Exception {
        Configuration configuration = Configuration.getInstance();
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.
                getConnection(configuration.getProperty("url"), configuration.getProperty("username"), configuration.getProperty("password"));
        return con;
    }

    public static void closeConnection(Connection con) throws SQLException {
        if (con != null) {
            con.close();
        }
    }

    public static void closeStatement(Statement stmt) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }

    public static void closeResultSet(ResultSet rSet) throws SQLException {
        if (rSet != null) {
            rSet.close();
        }
    }
}
