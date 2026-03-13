package com.dev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MarksheetInsert {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/School1", "root", "root");

		System.out.println(conn.getCatalog());

		Statement stmt = conn.createStatement();

		int i = stmt.executeUpdate("INSERT INTO marksheet (id, rollno, name, phy, chm, maths) VALUES (1, 101, 'Rahul Sharma', 78, 82, 90), (2, 102, 'Anita Verma', 88, 79, 85), (3, 103, 'Vikas Patel', 67, 72, 70), (4, 104, 'Sneha Gupta', 91, 89, 94), (5, 105, 'Amit Kumar', 74, 68, 80), (6, 106, 'Pooja Singh', 85, 90, 88), (7, 107, 'Rohit Mishra', 60, 65, 70), (8, 108, 'Neha Tiwari', 92, 95, 93), (9, 109, 'Karan Yadav', 55, 61, 58), (10, 110, 'Priya Saxena', 80, 84, 86)");
		
		System.out.println(i + " Row affected");
	}

}
