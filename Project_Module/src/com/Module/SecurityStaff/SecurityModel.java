package com.Module.SecurityStaff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SecurityModel {

	public long FindbyPk() throws Exception {
		long pk = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_module", "root", "root");

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from security");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getLong(1);
		}

		System.out.println((pk + 1) + " row added into table");

		return pk + 1;

	}

	public void add(SecurityBean bean) throws Exception {
		
		
		SecurityBean exist = FindbyEmail(bean.getEmail());
		
		if(exist != null) {
			throw new Exception("Email id already exist");
		}else {

		Connection conn = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into Security values(?,?,?,?,?)");

			pstmt.setLong(1, FindbyPk());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getShift());
			pstmt.setDouble(4, bean.getSalary());
			pstmt.setString(5, bean.getEmail());

			pstmt.executeUpdate();

			conn.commit();
			pstmt.close();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();

		}
		}
		// return bean.getId();
	}

	public void update(SecurityBean bean) throws Exception {
		Connection conn = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("update security set name = ? , shift = ? , salary = ?, email = ? where id = ?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getShift());
			pstmt.setDouble(3, bean.getSalary());
			pstmt.setString(4, bean.getEmail());
			pstmt.setLong(5, bean.getId());

			int i = pstmt.executeUpdate();
			System.out.println(i + " row updated in table");
			conn.commit();
			pstmt.close();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

	}

	public void delete(SecurityBean bean) throws Exception {
		Connection conn = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from security where id = ?");

			pstmt.setLong(1, bean.getId());

			int i = pstmt.executeUpdate();
			System.out.println(i + " row deleted from table");
			conn.commit();
			pstmt.close();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

	}

	public SecurityBean FindbyEmail(String email) throws Exception {
		Connection conn = null;
		SecurityBean bean = new SecurityBean();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_module", "root", "root");
			PreparedStatement pstmt = conn.prepareStatement("select * from security where email = ?");

			pstmt.setString(1, email);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new SecurityBean();

				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setShift(rs.getString(3));
				bean.setSalary(rs.getDouble(4));
				bean.setEmail(rs.getString(5));
			}

			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return bean;
	}

	public List search(SecurityBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = null;
		List<SecurityBean> list = new ArrayList<SecurityBean>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_module", "root", "root");

			StringBuffer sql = new StringBuffer("select * from security where 1=1");

			if (bean != null) {

				if (bean.getName() != null && bean.getName().length() > 0) {
					sql.append(" and name like '" + bean.getName() + "%'");
				}
				if (bean.getEmail() != null && bean.getEmail().length() > 0) {
					sql.append(" and email like '" + bean.getEmail() + "%'");
				}
				if (bean.getId() != 0 && bean.getId() > 0) {
					sql.append(" and id = " + bean.getId());
				}
			}

			if (pageNo > 0) {
				pageNo = (pageNo - 1) * pageSize;

				sql.append(" limit " + pageNo + ", " + pageSize);
			}
			System.out.println("sql query ---->" + sql.toString());
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new SecurityBean();

				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setShift(rs.getString(3));
				bean.setSalary(rs.getDouble(4));
				bean.setEmail(rs.getString(5));

				list.add(bean);

			}
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return list;
	}

}
