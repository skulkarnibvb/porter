package com.acc.xlp.writer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.acc.xlp.util.PortUtils;

public class DBWriter {

	private static Properties dbProperties;
	private static Logger logger = Logger.getRootLogger();

	/**
	 * @param entities
	 * @return
	 */
	public static boolean writeToDB(List<JSONObject> entities) {

		Connection connection = null;
		Statement statement = null;
		try {
			if (dbProperties == null) {
				dbProperties = new Properties();
				dbProperties.load(new InputStreamReader(PortUtils.class.getResourceAsStream("/port.properties")));
			}

			Class.forName(dbProperties.getProperty("db.driver"));
			connection = DriverManager.getConnection(dbProperties.getProperty("db.url"),
					dbProperties.getProperty("db.username"), dbProperties.getProperty("db.password"));
			connection.setAutoCommit(false);
			logger.info("Opened database successfully");

			statement = connection.createStatement();

			return true;

		} catch (SQLException e) {
			logger.error(e);
		} catch (ClassNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
		return false;
	}
}
