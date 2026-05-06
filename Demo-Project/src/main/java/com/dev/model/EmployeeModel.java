package com.dev.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dev.bean.EmployeeBean;
import com.dev.util.JDBCDataSource;

public class EmployeeModel {

	public int nextPk() {
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from Employee");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk + 1;
	}

	public long add(EmployeeBean bean) throws Exception {
		Connection conn = null;
		int pk = 0;
		
		EmployeeBean exist = findByEmail(bean.getEmail());
		
		if(exist != null){
			throw new Exception("Email already exists");
		}

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into Employee values(?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDepartment());
			pstmt.setLong(4, bean.getSalary());
			pstmt.setString(5, bean.getEmail());

			int i = pstmt.executeUpdate();
			System.out.println(i + " Row added");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	public void update(EmployeeBean bean) throws Exception {
		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("update Employee  set name= ? , department = ?, salary=? , email=? where id = ?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDepartment());
			pstmt.setLong(3, bean.getSalary());
			pstmt.setString(4, bean.getEmail());
			pstmt.setLong(5, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			System.out.println(" Row updated");

			pstmt.close();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public void delete(EmployeeBean bean) throws Exception {
		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from Employee where id = ?");

			pstmt.setLong(1, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			System.out.println(" Row Deleted");

			pstmt.close();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public EmployeeBean findByPk(long id) {
		Connection conn = null;
		EmployeeBean bean = null;
		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("select * from Employee where id = ?");

			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new EmployeeBean();

				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDepartment(rs.getString(3));
				bean.setSalary(rs.getLong(4));
				bean.setEmail(rs.getString(5));
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	public EmployeeBean findByEmail(String email) {
		Connection conn = null;
		EmployeeBean bean = null;
		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("select * from Employee where email = ?");

			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new EmployeeBean();

				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDepartment(rs.getString(3));
				bean.setSalary(rs.getLong(4));
				bean.setEmail(rs.getString(5));
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	public List<EmployeeBean> search(EmployeeBean bean, int pageNo, int pageSize) {

		Connection conn = null;
		List<EmployeeBean> list = new ArrayList<EmployeeBean>();

		StringBuffer sql = new StringBuffer("select * from Employee where 1=1");

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}

			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like'" + bean.getName() + "%'");
			}
			if (bean.getDepartment() != null && bean.getDepartment().length() > 0) {
				sql.append(" and department like'" + bean.getDepartment() + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + " , " + pageSize);
		}
		System.out.println("SQL Query -->" + sql.toString());

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new EmployeeBean();

				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDepartment(rs.getString(3));
				bean.setSalary(rs.getLong(4));
				bean.setEmail(rs.getString(5));
				list.add(bean);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.getConnection();
		}

		return list;

	}
}
