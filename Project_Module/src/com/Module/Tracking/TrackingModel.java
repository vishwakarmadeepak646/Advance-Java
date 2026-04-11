package com.Module.Tracking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackingModel {

	public long nextPk() throws Exception {
		long pk = 0;

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root");

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from tracking");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			pk = rs.getLong(1);
		}

		return pk + 1;
	}

	public long add(TrackingBean bean) throws Exception {

		Connection conn = null;

		TrackingBean exist = FindByPK(bean.getId());

		if (exist != null) {
			throw new Exception("This id is already exists");
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into tracking values(?,?,?,?)");

			pstmt.setLong(1, nextPk());
			pstmt.setString(2, bean.getNumber());
			pstmt.setString(3, bean.getLocation());
			pstmt.setString(4, bean.getStatus());

			int i = pstmt.executeUpdate();

			System.out.println(i + " Row Affected");

			pstmt.close();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return bean.getId();

	}

	public void update(TrackingBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("update Tracking set number = ? , location = ?, status = ? where id = ?");

			pstmt.setString(1, bean.getNumber());
			pstmt.setString(2, bean.getLocation());
			pstmt.setString(3, bean.getStatus());
			pstmt.setLong(4, bean.getId());

			int i = pstmt.executeUpdate();

			System.out.println(i + " Row Affected(Updated)");

			pstmt.close();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

	}

	public void delete(TrackingBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from Tracking where id = ?");

			pstmt.setLong(1, bean.getId());

			int i = pstmt.executeUpdate();

			System.out.println(i + " Row Affected(Deleted)");

			pstmt.close();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

	}

	public TrackingBean FindByPK(long id) throws Exception {
		TrackingBean bean = null;
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("select * from Tracking where id = ?");

			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new TrackingBean();

				bean.setId(rs.getLong(1));
				bean.setNumber(rs.getString(2));
				bean.setLocation(rs.getString(3));
				bean.setStatus(rs.getString(4));

			}

			pstmt.close();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

		return bean;
	}

	public List search(TrackingBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = null;

		List<TrackingBean> list = new ArrayList<TrackingBean>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root");

			StringBuffer sql = new StringBuffer("select * from tracking where 1=1 ");

			if (bean != null) {

				if (bean.getId() != 0 && bean.getId() > 0) {
					sql.append(" and id = " + bean.getId());
				}

				if (bean.getLocation() != null && bean.getLocation().length() > 0) {
					sql.append(" and location like '" + bean.getLocation() + "%'");
				}

				if (bean.getStatus() != null && bean.getStatus().length() > 0) {
					sql.append(" and Status like '" + bean.getStatus() + "%'");
				}

			}

			if (pageNo > 0) {

				pageNo = (pageNo - 1) * pageSize;
				sql.append(" limit " + pageNo + " , " + pageSize);

			}

			System.out.println("Sql Query is -> " + sql.toString());

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new TrackingBean();

				bean.setId(rs.getLong(1));
				bean.setNumber(rs.getString(2));
				bean.setLocation(rs.getString(3));
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
