package com.dev.PreparedStatement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserModel {

	public int add(UserBean bean) throws Exception { // Method for adding the DB Records
		Connection conn = null;

		UserBean exist = FindByLogin(bean.getLogin());

		if (exist != null) {
			throw new Exception("Login already exists");
		}

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

	public UserBean FindByPK(int id) throws Exception {

		UserBean bean = null;
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root");

			PreparedStatement pstmt = conn.prepareStatement("select * from st_user where id = ?");

			pstmt.setInt(1, id);

			ResultSet r = pstmt.executeQuery();

			while (r.next()) {
				bean = new UserBean();

				bean.setId(r.getInt(1));
				bean.setFirst_name(r.getString(2));
				bean.setLast_name(r.getString(3));
				bean.setLogin(r.getString(4));
				bean.setPassword(r.getString(5));
				bean.setDob(r.getDate(6));
			}

			pstmt.close();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} finally {
			conn.close();
		}
		return bean;
	}

	public UserBean FindByLogin(String login) throws Exception {
		UserBean bean = null;
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root");

			PreparedStatement pstmt = conn.prepareStatement("select * from st_user where login = ?");

			pstmt.setString(1, login);

			ResultSet r = pstmt.executeQuery();

			while (r.next()) {
				bean = new UserBean();

				bean.setId(r.getInt(1));
				bean.setFirst_name(r.getString(2));
				bean.setLast_name(r.getString(3));
				bean.setLogin(r.getString(4));
				bean.setPassword(r.getString(5));
				bean.setDob(r.getDate(6));
			}

			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return bean;
	}

	public UserBean authentication(String login, String password) throws Exception {
		UserBean bean = null;
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root");

			PreparedStatement pstmt = conn.prepareStatement("select * from st_user where login = ? and password = ?");
			pstmt.setString(1, login);
			pstmt.setString(2, password);

			ResultSet r = pstmt.executeQuery();

			while (r.next()) {
				bean = new UserBean();

				bean.setId(r.getInt(1));
				bean.setFirst_name(r.getString(2));
				bean.setLast_name(r.getString(3));
				bean.setLogin(r.getString(4));
				bean.setPassword(r.getString(5));
				bean.setDob(r.getDate(6));

			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.close();
		}

		return bean;
	}

	public List search(UserBean bean, int pageNo, int pageSize) throws Exception {

		List<UserBean> list = new ArrayList<UserBean>();

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root");

		StringBuffer sql = new StringBuffer("select * from st_user where 1=1"); // SQL Injection 1=1 mean true

		if (bean != null) {
			
			if (bean.getFirst_name() != null && bean.getFirst_name().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirst_name() + "%'");
			}

			if (bean.getLast_name() != null && bean.getLast_name().length() > 0) {
				sql.append(" and last_name like '" + bean.getLast_name() + "%'");
			}

			if (bean.getID() != 0 && bean.getID() > 0) {
				sql.append(" and id = " + bean.getID());
			}
		}

		if (pageNo > 0) { // Pagination
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + " , " + pageSize);
		}

		System.out.println("SQL query running now ------> " + sql.toString());

		PreparedStatement pstmt = conn.prepareStatement(sql.toString()); // Convert object to string

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			bean = new UserBean();

			bean.setId(rs.getInt(1));
			bean.setFirst_name(rs.getString(2));
			bean.setLast_name(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getDate(6));

			list.add(bean);
		}

		return list;
	}
}
