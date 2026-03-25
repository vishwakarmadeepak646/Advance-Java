package com.dev.jdbc.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJDBCDataSource {

	public static void main(String[] args) {
		for(int i =0; i<50; i++) {
		testSearch();
		System.out.println("------------" + i); // It will provide only 30 connection as connectionMax limit is 30
		}

	}
	
	public static void testSearch() {
		Connection conn =  null;
		
		try {
			conn = JDBCDataSource.getConnection();
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from st_user where id = 1");
			
			while(rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getString(4));
				System.out.println(rs.getString(5));
				System.out.println(rs.getString(6));
				
	
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

}
