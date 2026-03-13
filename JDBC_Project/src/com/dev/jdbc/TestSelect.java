package com.dev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestSelect {

	public static void main(String[] args) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school","root","root");
		
		System.out.println(conn.getCatalog());
		
		Statement stmt = conn.createStatement();
		
		ResultSet b = stmt.executeQuery("Select * from DB_connection");
		
		while(b.next()) {
			System.out.print(b.getInt(1));
			System.out.print("\t" + b.getString(2));
			System.out.print("\t" + b.getString(3));
			System.out.print("\t" + b.getString(4));
			System.out.println("\t" + b.getDate(5));
		}
		
	}

}
