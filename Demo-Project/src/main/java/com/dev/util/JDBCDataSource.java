package com.dev.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCDataSource {

	// JDBC Datasource static object

	private static JDBCDataSource jdbc = null;

	// C3P0 database connection pool

	private ComboPooledDataSource cpds = null;

	private static ResourceBundle rb = ResourceBundle.getBundle("com.dev.bundle.System");

	// Make default constructor private

	private JDBCDataSource() {

		try {

			// Create data source

			cpds = new ComboPooledDataSource();

			// Set DS Properties

			cpds.setDriverClass(rb.getString("driver"));
			cpds.setJdbcUrl(rb.getString("url"));
			cpds.setUser(rb.getString("username"));
			cpds.setPassword(rb.getString("pwd"));
			cpds.setMaxPoolSize(Integer.parseInt(rb.getString("maxpoolsize")));
			cpds.setMinPoolSize(Integer.parseInt(rb.getString("minpoolsize")));
			cpds.setAcquireIncrement(Integer.parseInt(rb.getString("acquireincrement")));
			cpds.setInitialPoolSize(Integer.parseInt(rb.getString("initialpoolsize")));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// get singleton class instance

	public static JDBCDataSource getInstance() {

		if (jdbc == null) {

			jdbc = new JDBCDataSource();

		}
		return jdbc;

	}

	// gets connection from DCP

	public static Connection getConnection() {

		try {
			return getInstance().cpds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Close connection

		public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// Close connection

		public static void closeConnection(Connection conn, Statement stmt) {
			closeConnection(conn, stmt, null);
		}

		// Close connection

		public static void closeConnection(Connection conn) {
			closeConnection(conn, null, null);
		}

}
