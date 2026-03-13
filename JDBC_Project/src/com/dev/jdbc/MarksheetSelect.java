package com.dev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MarksheetSelect {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/School1", "root", "root");

		System.out.println(conn.getCatalog());

		Statement stmt = conn.createStatement();
		
		ResultSet s = stmt.executeQuery("Select * from Marksheet");
		
		while(s.next()) {
			System.out.print("\t" + s.getInt(1));
			System.out.print("\t" + s.getInt(2));
			System.out.print("\t" + s.getString(3));
			System.out.print("\t" + s.getInt(4));
			System.out.print("\t" + s.getInt(5));
			System.out.println("\t" + s.getInt(6));
			
		}
		

	}

}
