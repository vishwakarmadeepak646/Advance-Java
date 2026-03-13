package com.dev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TestDelete {

	public static void main(String[] args) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/School","root","root");
		
		System.out.println(conn.getCatalog());
		
		Statement stmt = conn.createStatement();
			
		int i = stmt.executeUpdate("delete from DB_connection where id = 5");

		System.out.println(i + " row affected");
		

	}

}
