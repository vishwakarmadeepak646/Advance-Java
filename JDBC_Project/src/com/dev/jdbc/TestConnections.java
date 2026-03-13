package com.dev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class TestConnections {

	public static void main(String[] args) throws Exception {
		
		//---> step 1: Load Driver
		Class.forName("com.mysql.cj.jdbc.Driver"); // Loading the driver using the path of classDriver.so Java can communicate with the MySQL database.
		
		//---> Step 2 : Make Connection to the database
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/School", "root", "root"); 
		//DriverManager is a JDBC class. It's responsible for managing database drivers and establishing connections | This method Connects Java application to database
		//Create a connection object to communicate with the database
		System.out.println("Connection established successfully : " + conn.getCatalog()); // It getCatalog() will write host name
		
		//---> step 3: create statement and get ResultSet or get number of rows
		
		//Connection know as factory of statement.
		Statement stmt = conn.createStatement();  //Creates a Statement object from the database connection to execute SQL queries in JDBC.
		//Stmt gives two methods : 1.   .executeQuery(SQL) ;  --- It returns ResultSet AND 2. .executeUpdate(SQL); ---------It return int
		ResultSet rs = stmt.executeQuery("select * from marksheet");
		
		while(rs.next()) {
			
			System.out.print(rs.getInt(1));
			System.out.print("\t" + rs.getString(2));
			System.out.print("\t" + rs.getInt(3));
			System.out.print("\t" + rs.getInt(4));
			System.out.println("\t" + rs.getInt(5));
		}

	}

}
