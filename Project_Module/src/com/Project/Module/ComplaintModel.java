package com.Project.Module;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComplaintModel {

	public int add(ComplaintBean bean) throws Exception {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Module", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into Complaint_Management values(?, ?, ?, ? ,?, ?)");

			pstmt.setInt(1, bean.getId());
			pstmt.setString(2, bean.getUser_name());
			pstmt.setString(3, bean.getComplaint_type());
			pstmt.setString(4, bean.getDescription());
			pstmt.setString(5, bean.getStatus());
			pstmt.setString(6, bean.getEmail());

			int i = pstmt.executeUpdate();
			System.out.println(i + " Record inserted in DB");

			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}

		return bean.getId();

	}

	public void update(ComplaintBean bean) throws Exception {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Module", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update Complaint_Management set user_name = ? , complaint_type = ? , description = ? , status = ? where id = ?");

			pstmt.setString(1, bean.getUser_name());
			pstmt.setString(2, bean.getComplaint_type());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getStatus());
			pstmt.setInt(5, bean.getId());

			int i = pstmt.executeUpdate();
			System.out.println(i + " Record inserted in DB");

			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public void delete(ComplaintBean bean) throws Exception {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Module", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("delete from Complaint_Management where id = ? and status = ?");

			pstmt.setInt(1, bean.getId());
			pstmt.setString(2, bean.getStatus());

			int i = pstmt.executeUpdate();
			System.out.println(i + " Record Deleted from DB");

			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}

	}
	
	public ComplaintBean FindByEmail(String email) throws Exception {
		Connection conn = null;
		ComplaintBean bean = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Module", "root", "root");
			conn.setAutoCommit(false);
			
			PreparedStatement pstmt = conn.prepareStatement("select * from complaint_management where email = ? ");
			
			pstmt.setString(1, email);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				bean = new ComplaintBean();
			
				bean.setId(rs.getInt(1));
				bean.setUser_name(rs.getString(2));
				bean.setComplaint_type(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setStatus(rs.getString(5));
				bean.setEmail(rs.getString(6));
			}
			
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
		
		return bean;
	}

	public List search(ComplaintBean bean, int PageNo, int PageSize) throws Exception {

		List<ComplaintBean> list = new ArrayList<ComplaintBean>();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_module", "root", "root");

		StringBuffer sql = new StringBuffer("select * from complaint_management where 1=1");

		if (bean != null) {

			if (bean.getUser_name() != null && bean.getUser_name().length() > 0) {
				sql.append(" and user_name like '" + bean.getUser_name() + "%'");
			}

			if (bean.getComplaint_type() != null && bean.getComplaint_type().length() > 0) {
				sql.append(" and complaint_type like '" + bean.getComplaint_type() + "%'");
			}
			if (bean.getId() != 0 && bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getStatus() != null && bean.getStatus().length() > 0) {
				sql.append(" and status = '" + bean.getStatus() + "'");
			}
		}
		
		if(PageSize != 0 ) {
			PageNo = (PageNo - 1) * PageSize ;
			
			sql.append(" limit " + PageNo + " , " + PageSize);
		}

		System.out.println("SQL Command running ----> " + sql.toString());

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			bean = new ComplaintBean();

			bean.setId(rs.getInt(1));
			bean.setUser_name(rs.getString(2));
			bean.setComplaint_type(rs.getString(3));
			bean.setDescription(rs.getString(4));
			bean.setStatus(rs.getString(5));
			bean.setEmail(rs.getString(6));

			list.add(bean);
		}

		return list;

	}
}
