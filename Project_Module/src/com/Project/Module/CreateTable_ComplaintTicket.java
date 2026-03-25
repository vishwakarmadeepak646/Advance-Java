package com.Project.Module;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTable_ComplaintTicket {
	
	public static void main(String[] args) throws Exception {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Module", "root", "root");
		
		Statement stmt = conn.createStatement();
		
		stmt.execute(
				"create table ComplaintTicket(Id int primary key, issueType varchar(20), createdDate Date, status varchar(20))");
		
		System.out.println("Table created");
		stmt.close();
		conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		

	}

}
