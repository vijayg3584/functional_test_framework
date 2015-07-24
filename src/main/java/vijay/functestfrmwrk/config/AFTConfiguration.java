/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vijay.functestfrmwrk.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vijay
 */
public class AFTConfiguration {

	private static AFTConfiguration configuration;
	private static Map<String, String> configMap = new HashMap<String, String>();

	private AFTConfiguration() throws ConfigException {
		loadConfiguration();
	}

	public static AFTConfiguration getInstance() throws ConfigException {
		if (configuration == null) {
			configuration = new AFTConfiguration();
		}
		return configuration;
	}

	private void loadConfiguration() throws ConfigException {
		Connection con = null;
		Statement stmt = null;
		try {
			con = TomcatConnectionPool.getConnection();
			String query = "select property, value from AUTO_FUNC_TEST_SETTING where APPLICABLE = 'T'";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				configMap.put(rs.getString("property"), rs.getString("value"));
			}
		} catch (SQLException ex) {
			throw new ConfigException("Unable to load configuration: " + ex.getMessage(), ex);
		} finally {
			TomcatConnectionPool.closeStatement(stmt);
			TomcatConnectionPool.closeConnection(con);
		}
	}

	public String getConfigValue(AFTConfigPropertyInfo propertyInfo) {
		return configMap.get(propertyInfo.toString());
	}
}
