package com.Module.Release;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class CreateReleaseTable {

	public static void main(String[] args) throws Exception {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Module", "root", "root");
		
		Statement stmt = conn.createStatement();
		
		stmt.executeUpdate("create table Release_Info(id bigint primary key, code varchar(20), version varchar(20), date Date, status varchar(20))");

		System.out.println("Table create successfully");
		stmt.close();
		
		conn.close();
		
		}catch(Exception e) {
			e.printStackTrace();
			

		}
	}

}
