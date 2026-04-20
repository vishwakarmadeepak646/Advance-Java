package com.Module.Escalation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EscalationModel {

	public long nextPk() {
		Connection conn = null;
		EscalationBean bean = null;
		long pk = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Module", "root", "root");
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from Escalation");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getLong(1);

				System.out.println("id inserted in table : " + (pk + 1));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pk + 1;
	}

	public void add(EscalationBean bean) throws Exception {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into Escalation values(?,?,?,?,?)");

			pstmt.setLong(1, nextPk());
			pstmt.setString(2, bean.getCode());
			pstmt.setString(3, bean.getLevel());
			pstmt.setString(4, bean.getAssignedTo());
			pstmt.setString(5, bean.getStatus());

			int i = pstmt.executeUpdate();

			System.out.println(i + " Row inserted in table");

			conn.commit();
			pstmt.close();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} finally {
			conn.close();
		}

	}

	public long update(EscalationBean bean) throws Exception {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update Escalation set code = ?, level = ?, assignedTo= ?, status = ? where id = ?");

			pstmt.setString(1, bean.getCode());
			pstmt.setString(2, bean.getLevel());
			pstmt.setString(3, bean.getAssignedTo());
			pstmt.setString(4, bean.getStatus());
			pstmt.setLong(5, bean.getId());

			int i = pstmt.executeUpdate();

			System.out.println(i + " Row updated in table");

			conn.commit();
			pstmt.close();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} finally {
			conn.close();
		}

		return bean.id;
	}

	public void delete(EscalationBean bean) throws Exception {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from Escalation where id = ?");

			pstmt.setLong(1, bean.getId());

			int i = pstmt.executeUpdate();

			System.out.println(i + " Row deleted in table");

			conn.commit();
			pstmt.close();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} finally {
			conn.close();
		}

	}

	public EscalationBean findByPK(long id) {

		EscalationBean bean = null;

		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Module", "root", "root");

			PreparedStatement pstmt = conn.prepareStatement("select * from Escalation where id = ?");
			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new EscalationBean();

				bean.setId(rs.getLong(1));
				bean.setCode(rs.getString(2));
				bean.setLevel(rs.getString(3));
				bean.setAssignedTo(rs.getString(4));
				bean.setStatus(rs.getString(5));
			}

			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	public List search(EscalationBean bean, int pageNo, int pageSize) throws Exception {
		Connection conn = null;

		List<EscalationBean> list = new ArrayList<EscalationBean>();
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Module", "root", "root");

			StringBuffer sql = new StringBuffer("select * from Escalation where 1=1");

			if (sql != null) {
				if (bean.level != null && bean.level.length() > 0) {
					sql.append(" like '" + bean.getLevel() + "%'");
				}

				if (bean.getStatus() != null && bean.getStatus().length() > 0) {
					sql.append("like '" + bean.getStatus() + "%'");
				}

				if (bean.getId() != 0 && bean.getId() > 0) {
					sql.append(" and id = " + bean.getId());
				}
			}

			if (pageNo > 0) {
				pageNo = (pageNo - 1) * pageSize;
				sql.append(" limit " + pageNo + "," + pageSize);
			}
			System.out.println("Query running ---> " + sql.toString());

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new EscalationBean();

				bean.setId(rs.getLong(1));
				bean.setCode(rs.getString(2));
				bean.setLevel(rs.getString(3));
				bean.setAssignedTo(rs.getString(4));
				bean.setStatus(rs.getString(5));
				list.add(bean);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

		return list;
	}
}
