package com.dev.jdbc.util;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class JDBCDataSource {

	// JDBC Datasource static object

	private static JDBCDataSource jdbc = null;

	// C3P0 database connection pool

	private ComboPooledDataSource cpds = null;

	// Make default constructor private

	private JDBCDataSource() {

		try {

			// Create data source

			cpds = new ComboPooledDataSource();

			// Set DS Properties

			cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
			cpds.setJdbcUrl("jdbc:mysql://localhost:3306/school");
			cpds.setUser("root");
			cpds.setPassword("root");
			cpds.setMaxPoolSize(30);
			cpds.setMinPoolSize(10);
			cpds.setAcquireIncrement(10);
			cpds.setInitialPoolSize(10);

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
