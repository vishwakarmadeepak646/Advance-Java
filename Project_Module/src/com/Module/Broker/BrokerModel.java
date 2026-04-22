package com.Module.Broker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrokerModel {

	public long nextPk() {
		BrokerBean bean = null;
		Connection conn = null;
		long pk = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_module", "root", "root");

			PreparedStatement pstmt = conn.prepareStatement("select max(id) from Broker");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new BrokerBean();
				pk = rs.getLong(1);

				System.out.println("Next pk id is : " + (pk + 1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pk + 1;
	}

	public void add(BrokerBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into Broker values(?,?,?,?)");
			pstmt.setLong(1, nextPk());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getContactNumber());
			pstmt.setString(4, bean.getCompany());

			int i = pstmt.executeUpdate();

			System.out.println(i + "Row affected");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}

	}

	public void update(BrokerBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("update Broker set name = ? , contactNumber = ? , company = ? where id = ?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getContactNumber());
			pstmt.setString(3, bean.getCompany());
			pstmt.setLong(4, bean.getId());

			int i = pstmt.executeUpdate();

			System.out.println(i + "Row affected and updated in table");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();

		} finally {
			conn.close();
		}

	}

	public void delete(BrokerBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from Broker where id = ?");

			pstmt.setLong(1, bean.getId());

			int i = pstmt.executeUpdate();

			System.out.println(i + "Row affected and Deleted from table");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();

		} finally {
			conn.close();
		}

	}
	
	
	public BrokerBean findByPk(long id) throws Exception {
		BrokerBean bean = null;
	
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_module", "root", "root");

			PreparedStatement pstmt = conn.prepareStatement("select * from broker where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bean = new BrokerBean();

				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setContactNumber(rs.getString(3));
				bean.setCompany(rs.getString(4));

			}
			pstmt.close();
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		
		return bean;
	
	}

	public List search(BrokerBean bean, int pageNo, int pageSize) throws SQLException {
		Connection conn = null;
		List<BrokerBean> list = new ArrayList<BrokerBean>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_module", "root", "root");

			StringBuffer sql = new StringBuffer("select * from broker where 1=1");

			if (bean != null) {
				if (bean.getName() != null && bean.getName().length() > 0) {
					sql.append(" and name like '" + bean.getName() + "%'");
				}

				if (bean.getCompany() != null && bean.getCompany().length() > 0) {
					sql.append(" and company like '" + bean.getCompany() + "%'");
				}

				if (bean.getId() != 0 && bean.getId() > 0) {
					sql.append(" and id = " + bean.getId());
				}
			}

			if (pageNo > 0) {
				pageNo = (pageNo - 1) * pageSize;
				sql.append(" limit " + pageNo + " , " + pageSize);
			}

			System.out.println("Sql query running --> " + sql.toString());

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new BrokerBean();

				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setContactNumber(rs.getString(3));
				bean.setCompany(rs.getString(4));
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
