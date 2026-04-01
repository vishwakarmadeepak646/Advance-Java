package com.Project.Module;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoginModel {

	public long add(LoginBean bean) throws Exception {

		Connection conn = null;
		
		LoginBean exist = FindbyLoginCode(bean.getLoginCode());
		
		if(exist != null) {
			throw new Exception("LoginCode already exists");
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into login values (?, ?,?,?,?)");

			pstmt.setLong(1, bean.getId());
			pstmt.setString(2, bean.getLoginCode());
			pstmt.setString(3, bean.getUserName());
			pstmt.setDate(4, new Date(bean.getLoginTime().getTime()));
			pstmt.setString(5, bean.getStatus());

			int i = pstmt.executeUpdate();

			System.out.println(i + " row inserted in table");
			pstmt.close();
			conn.commit();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			conn.close();
		}

		return bean.getId();
	}

	public void update(LoginBean bean) throws Exception {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update login set  loginCode= ?, userName = ?, loginTime= ? , status= ? where id = ?");

			pstmt.setString(1, bean.getLoginCode());
			pstmt.setString(2, bean.getUserName());
			pstmt.setDate(3, new Date(bean.getLoginTime().getTime()));
			pstmt.setString(4, bean.getStatus());
			pstmt.setLong(5, bean.getId());

			int i = pstmt.executeUpdate();

			System.out.println(i + " row updated in table");
			pstmt.close();
			conn.commit();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			conn.close();
		}

	}

	public void delete(LoginBean bean) throws Exception {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from Login where id = ?");

			pstmt.setLong(1, bean.getId());

			int i = pstmt.executeUpdate();

			System.out.println(i + " row deleted in table");
			pstmt.close();
			conn.commit();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			conn.close();
		}

	}

	public LoginBean FindByPk(long id) {
		LoginBean bean = null;
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_module", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("select * from login where id = ?");

			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new LoginBean();

				bean.setId(rs.getLong(1));
				bean.setLoginCode(rs.getString(2));
				bean.setUserName(rs.getString(3));
				bean.setLoginTime(rs.getDate(4));
				bean.setStatus(rs.getString(5));

			}

			conn.commit();
			conn.rollback();
		} catch (Exception e) {
			e.printStackTrace();

		}

		return bean;
	}

	public LoginBean FindbyLoginCode(String LoginCode) {

		LoginBean bean = null;
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_module", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("select * from login where LoginCode = ?");

			pstmt.setString(1, LoginCode);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new LoginBean();

				bean.setId(rs.getLong(1));
				bean.setLoginCode(rs.getString(2));
				bean.setUserName(rs.getString(3));
				bean.setLoginTime(rs.getDate(4));
				bean.setStatus(rs.getString(5));

			}

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();

		}

		return bean;

	}

	public List<LoginBean> search(LoginBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = null;
		List<LoginBean> list = new ArrayList<LoginBean>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_module", "root", "root");
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer("select * from login where 1=1");

			if (bean != null) {
				if (bean.getUserName() != null && bean.getUserName().length() > 0) {
					sql.append(" and userName like '" + bean.getUserName() + "%'");
				}

				if (bean.getLoginCode() != null && bean.getLoginCode().length() > 0) {
					sql.append(" and loginCode  like '" + bean.getLoginCode() + "%'");
				}
				if (bean.getId() != 0 && bean.getId() > 0) {
					sql.append(" and id = " + bean.getId());
				}
			}

			if (pageNo > 0) {
				pageNo = (pageNo - 1) * pageSize;

				sql.append(" limit " + pageNo + " , " + pageSize);
			}
			System.out.println("SQL Query ---->" + sql.toString());
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new LoginBean();

				bean.setId(rs.getLong(1));
				bean.setLoginCode(rs.getString(2));
				bean.setUserName(rs.getString(3));
				bean.setLoginTime(rs.getDate(4));
				bean.setStatus(rs.getString(5));

				list.add(bean);
			}

			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}

		return list;

	}
}
