package com.Project.Module;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;

public class CreateLoginTable {

	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_module", "root", "root");
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("create table Login (id BIGINT primary key, loginCode varchar(20), userName varchar(20), loginTime Date, status varchar(20))");
				
			System.out.println("Table Created successfully ");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		

	}

}
