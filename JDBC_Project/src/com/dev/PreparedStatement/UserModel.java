package com.dev.PreparedStatement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UserModel {

	public int add(UserBean bean) throws Exception { // Method for adding the DB Records
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root");

			PreparedStatement pstmt = conn.prepareStatement("insert into st_user values(?,?,?,?,?,?)");
			conn.setAutoCommit(false);

			pstmt.setInt(1, bean.getID());
			pstmt.setString(2, bean.getFirst_name());
			pstmt.setString(3, bean.getLast_name());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getPassword());
			pstmt.setDate(6, new Date(bean.getDob().getTime()));

			int i = pstmt.executeUpdate();
			System.out.println(i + "row affected(record inserted)");

			pstmt.close();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

		return bean.getID();
	}

	public void update(UserBean bean) throws Exception { // To update the DB Records
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_user set first_name = ?,last_name = ?,login = ?, password = ?,Dob = ? where id = ?");

			pstmt.setString(1, bean.getFirst_name());
			pstmt.setString(2, bean.getLast_name());
			pstmt.setString(3, bean.getLogin());
			pstmt.setString(4, bean.getPassword());
			pstmt.setDate(5, new Date(bean.getDob().getTime()));
			pstmt.setInt(6, bean.getID());

			int i = pstmt.executeUpdate();
			System.out.println(i + " row affected (record updated");

			pstmt.close();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();

		} finally {
			conn.close();
		}

	}

	public void delete(UserBean bean) throws Exception { // Method for Deleting the DB Records
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root");

			PreparedStatement pstmt = conn.prepareStatement("delete from st_user where id = ?");
			conn.setAutoCommit(false);

			pstmt.setInt(1, bean.getID());

			int i = pstmt.executeUpdate();
			System.out.println(i + "row affected(record Deleted)");

			pstmt.close();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

	}

}
