package com.Module.Tracking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTrackingTable {

	public static void main(String[] args) throws Exception {

		try {
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root");

		Statement stmt = conn.createStatement();

		stmt.executeUpdate(
				"create table Tracking(id bigInt primary key, number varchar(20), location varchar(20), status varchar(20))");

		System.out.println(" Table creat successfully");

		conn.close();
		stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
