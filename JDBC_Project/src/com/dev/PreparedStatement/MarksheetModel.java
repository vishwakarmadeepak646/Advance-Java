package com.dev.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MarksheetModel {

	public int add(MarksheetBean bean) throws Exception {
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root");
			
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into Marksheet values(?,?,?,?,?)");

			pstmt.setInt(1, bean.getRollNo());
			pstmt.setString(2, bean.getName());
			pstmt.setInt(3, bean.getPhy());
			pstmt.setInt(4, bean.getChm());
			pstmt.setInt(5, bean.getMaths());
			
			int i = pstmt.executeUpdate();
			System.out.println(i + "Row Affected(record inserted)");
			
			pstmt.close();
			conn.commit();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

		return bean.getRollNo();
	}
}
