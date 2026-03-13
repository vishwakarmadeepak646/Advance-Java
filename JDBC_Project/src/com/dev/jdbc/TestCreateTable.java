package com.dev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TestCreateTable {

	public static void main(String[] args) throws Exception {
	
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/School","root","root");
		System.out.println("Connect Successfully" + conn.getCatalog());
		
		Statement stmt = conn.createStatement();
		
		int i = stmt.executeUpdate("create table DB_Connection(id int primary key, First_Name varchar(40), Last_Name varchar(40), Email varchar(20), Date date)");
		
		System.out.println(i);
		
		stmt.close();
		conn.close();

	}

}
