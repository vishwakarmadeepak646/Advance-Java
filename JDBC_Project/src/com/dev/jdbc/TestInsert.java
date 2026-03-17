package com.dev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TestInsert {

	public static void main(String[] args) throws Exception {
		Connection conn = null;

		ResourceBundle rb = ResourceBundle.getBundle("com.dev.jdbc.Bundle.app");

		String url = rb.getString("url");
		String driver = rb.getString("driver");
		String pwd = rb.getString("pwd");
		String username = rb.getString("username");

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, pwd);
			Statement stmt = conn.createStatement();
			int i = stmt.executeUpdate(
					"insert into db_connection values(6,'Radhe', 'Shyam', 'radhsyam@gmail.com','1997-09-05')");

			System.out.println(i + " Rows affected");
			stmt.close();

		} catch (Exception e) {
			conn.rollback();
			System.out.println(e.getMessage());
		} finally {
			conn.close();
		}

	}
}
