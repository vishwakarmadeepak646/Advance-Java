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

		System.out.print("Id");
		System.out.print("\t" + "Roll No");
		System.out.print("\t" + "Name");
		System.out.print("\t\t" + "Physics");
		System.out.print("\t" + "Chemistry");
		System.out.print("\t" + "Maths");
		System.out.print("\t" + "Total");
		System.out.println("\t" + "Percentage");

		while (s.next()) {
			System.out.print(s.getInt(1));
			System.out.print("\t" + s.getInt(2));
			System.out.print("\t" + s.getString(3));
			System.out.print("\t" + s.getInt(4));
			System.out.print("\t" + s.getInt(5));
			System.out.print("\t\t" + s.getInt(6));

			int total = (s.getInt(4) + s.getInt(5) + s.getInt(6));
			int per = total / 3;
			System.out.print("\t" + total);
			System.out.println("\t" + per);

		}

	}

}
