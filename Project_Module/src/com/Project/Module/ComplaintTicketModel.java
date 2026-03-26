package com.Project.Module;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ComplaintTicketModel {

	public int add(ComplaintTicketBean bean) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_module", "root", "root");

			PreparedStatement pstmt = conn.prepareStatement("insert into complaintticket values(?,?,?,?)");

			conn.setAutoCommit(false);

			pstmt.setInt(1, bean.getId());
			pstmt.setString(2, bean.getIssueType());
			pstmt.setDate(3, new Date(bean.getCreatedDate().getTime()));
			pstmt.setString(4, bean.getStatus());

			int i = pstmt.executeUpdate();

			System.out.println(i + " Row affected in add method ");

			conn.commit();
			pstmt.close();
		} catch (Exception e) {

			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

		return bean.getId();

	}

	public void update(ComplaintTicketBean bean) throws Exception {
		Connection conn = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update complaintticket set issueType = ? , createdDate = ? ,status = ? where id = ? ");

			pstmt.setString(1, bean.getIssueType());
			pstmt.setDate(2, new Date(bean.getCreatedDate().getTime()));
			pstmt.setString(3, bean.getStatus());
			pstmt.setInt(4, bean.getId());

			int i = pstmt.executeUpdate();
			System.out.println(i + " Row afftected from update statement");
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public void delete(ComplaintTicketBean bean) throws Exception {
		Connection conn = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from complaintticket where id = ? ");

			pstmt.setInt(1, bean.getId());

			int i = pstmt.executeUpdate();
			System.out.println(i + " Row afftected from delete statement");
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public ComplaintTicketBean FindByPK(int id) throws Exception {

		ComplaintTicketBean bean = null;
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_module", "root", "root");

			PreparedStatement pstmt = conn.prepareStatement("select * from complaintticket where id = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new ComplaintTicketBean();

				bean.setId(rs.getInt(1));
				bean.setIssueType(rs.getString(2));
				bean.setCreatedDate(rs.getDate(3));
				bean.setStatus(rs.getString(4));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return bean;
	}

	public List search(ComplaintTicketBean bean, int pageNo, int pageSize) throws Exception {

		List<ComplaintTicketBean> list = new ArrayList<ComplaintTicketBean>();
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_module", "root", "root");

			StringBuffer sql = new StringBuffer("select * from complaintticket where 1=1");

			if (bean != null) {
				if (bean.getStatus() != null && bean.getStatus().length() > 0) {
					sql.append(" and getStatus like '" + bean.getStatus() + "%'");
				}

				if (bean.getIssueType() != null && bean.getIssueType().length() > 0) {
					sql.append(" and  issueType like '" + bean.getIssueType() + "%'");
				}

				if (bean.getId() > 0) {
					sql.append(" and id = " + bean.getId());
				}
			}

			if (pageNo > 0) {
				pageNo = (pageNo - 1) * pageSize;

				sql.append(" limit " + pageNo + " , " + pageSize);
			}
			System.out.println("sql query running ----> " + sql.toString());
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new ComplaintTicketBean();

				bean.setId(rs.getInt(1));
				bean.setIssueType(rs.getString(2));
				bean.setCreatedDate(rs.getDate(3));
				bean.setStatus(rs.getString(4));
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
