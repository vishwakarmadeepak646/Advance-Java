package com.Module.BlockList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableBlockList {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Module", "root", "root");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(
					"create table BlockList (id bigint primary key, code varchar(20), name varchar(20), reason varchar(20), status varchar(20))");
			System.out.println("Table created successfully");
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
	}
}
