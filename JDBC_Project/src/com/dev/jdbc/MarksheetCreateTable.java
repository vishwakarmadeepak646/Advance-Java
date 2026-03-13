package com.dev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MarksheetCreateTable {

	public static void main(String[] args) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/School1","root","root");
		
		System.out.println(conn.getCatalog());
		
		Statement stmt = conn.createStatement();
		
		int i = stmt.executeUpdate("CREATE TABLE marksheet ( id INT PRIMARY KEY, rollno INT, name VARCHAR(50), phy INT, chm INT, maths INT )");
		
System.out.println(i + " Table created");
	}

}
