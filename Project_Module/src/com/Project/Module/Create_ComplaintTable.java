package com.Project.Module;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Create_ComplaintTable {

	public static void main(String[] args) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Module", "root", "root");

			Statement stmt = conn.createStatement();
			stmt.execute(
					"create table Complaint_Management(id int primary key , user_name varchar(20) , complaint_type varchar(20), description varchar(60), status varchar(10) not null)");
			System.out.println("Table Created");
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			
		}

	}

}
