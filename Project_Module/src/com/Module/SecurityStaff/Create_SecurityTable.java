package com.Module.SecurityStaff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Create_SecurityTable {

	public static void main(String[] args) throws Exception {
		
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_module", "root", "root");
		
		Statement stmt = conn.createStatement();
		
		stmt.execute("create table Security(id bigInt, name varchar(20), shift varchar(20), salary double, email varchar(40))");
		
		System.out.println("Table created");
		
		conn.close();
		stmt.close();
		
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
	}
}
