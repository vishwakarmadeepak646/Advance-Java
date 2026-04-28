package com.Module.EventBooking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.util.JDBCDataSource;

public class EventBookingModel {
	public int nextPk() throws Exception {
		Connection conn = null;
		int pk = 0;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_module", "root", "root");
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from eventbooking");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

		return pk + 1;
	}

	public long add(EventBookingBean bean) throws Exception {
		Connection conn = null;
		int pk = nextPk();
		
		EventBookingBean exist = findByDate(bean.getDate());
		
		if(exist != null) {
			throw new Exception("Event is already booked for this date");
		}
		
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into eventBooking values(?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setDate(3, new java.sql.Date(bean.getDate().getTime()));
			pstmt.setInt(4, bean.getSeat());

			int i = pstmt.executeUpdate();

			System.out.println(i + " Row affected");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return pk;
	}

	public void update(EventBookingBean bean) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("update EventBooking set name=?, date=?, seat = ? where id =?");

			pstmt.setString(1, bean.getName());
			pstmt.setDate(2, new java.sql.Date(bean.getDate().getTime()));
			pstmt.setInt(3, bean.getSeat());
			pstmt.setLong(4, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public void delete(EventBookingBean bean) throws Exception {
		Connection conn = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from EventBooking where id =?");
			pstmt.setLong(1, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public EventBookingBean findByPk(long id) throws SQLException {
		EventBookingBean bean = null;
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_module", "root", "root");
			PreparedStatement pstmt = conn.prepareStatement("select * from EventBooking where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new EventBookingBean();

				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDate(rs.getDate(3));
				bean.setSeat(rs.getInt(4));
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return bean;
	}

	public EventBookingBean findByDate(Date d) throws Exception {
		EventBookingBean bean = null;
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_module", "root", "root");
			PreparedStatement pstmt = conn.prepareStatement("select * from EventBooking where Date = ?");
			pstmt.setDate(1, new java.sql.Date(d.getTime()));

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new EventBookingBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDate(rs.getDate(3));
				bean.setSeat(rs.getInt(4));
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

		return bean;
	}

	public List<EventBookingBean> search(EventBookingBean bean, int pageNo, int pageSize) throws Exception {
		Connection conn = null;
		List<EventBookingBean> list = new ArrayList<EventBookingBean>();

		StringBuffer sql = new StringBuffer("select * from EventBooking where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append("and id = ?" + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like'" + bean.getName() + "%'");
			}
			if (bean.getDate() != null && bean.getDate().getTime() > 0) {
				sql.append(" and date like'" + bean.getDate() + "%'");
			}
			if (bean.getSeat() > 0) {
				sql.append(" and seat = " + bean.getSeat());
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + " , " + pageSize);
		}
		System.out.println("SQL query -- > " + sql.toString());

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_module", "root", "root");
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new EventBookingBean();

				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDate(rs.getDate(3));
				bean.setSeat(rs.getInt(4));
				list.add(bean);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

		return list;
	}

}
