package com.dev.jdbc.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TransactionHandling {
	public static void main(String[] args) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root");
			conn.setAutoCommit(false); // By default it's true

			Statement stmt = conn.createStatement();

			int i = stmt.executeUpdate(
					"insert into db_connection values(1,'Kartik','Jha','kartik@gmail.com','2000-05-09')");

			i = stmt.executeUpdate("insert into db_connection values(2,'Nidhi','Vish','nidhi@gmail.com','1999-03-07')");

			i = stmt.executeUpdate("insert into db_connection values(5,'Ronit','Jain','ronit@gmail.com','1998-01-01')");

			conn.commit();
			stmt.close();

			System.out.println("Data inserted");
		} catch (Exception e) {

			e.printStackTrace();
			conn.rollback();

		} finally {
			conn.close();
		}

	}
}
