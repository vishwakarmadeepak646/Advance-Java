package com.Module.Release;

import java.beans.beancontext.BeanContextChildComponentProxy;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReleaseModel {

	public long nextPk() {
		Connection conn = null;
		long pk = 0;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_module", "root", "root");
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from release_info");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				pk = rs.getLong(1);

				System.out.println("Row added on id number : " + (pk + 1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pk + 1;
	}

	public void add(ReleaseBean bean) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into release_info values(?,?,?,?,?)");

			pstmt.setLong(1, nextPk());
			pstmt.setString(2, bean.getCode());
			pstmt.setString(3, bean.getVersion());
			pstmt.setDate(4, new Date(bean.getDate().getTime()));
			pstmt.setString(5, bean.getStatus());

			int i = pstmt.executeUpdate();

			System.out.println(i + " Row added in table");
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();

		} finally {
			conn.close();
		}

	}

	private long FindByPk() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void update(ReleaseBean bean) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("update Release_Info set code = ?, version =?, date = ?, status= ? where id = ?");

			pstmt.setString(1, bean.getCode());
			pstmt.setString(2, bean.getVersion());
			pstmt.setDate(3, new Date(bean.getDate().getTime()));
			pstmt.setString(4, bean.getStatus());
			pstmt.setLong(5, bean.getId());

			int i = pstmt.executeUpdate();

			System.out.println(i + " Row updated in table");
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();

		} finally {
			conn.close();
		}

	}

	public void delete(ReleaseBean bean) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(" delete from release_info where id = ?");

			pstmt.setLong(1, bean.getId());

			int i = pstmt.executeUpdate();

			System.out.println(i + " Row deleted in table");
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();

		} finally {
			conn.close();
		}

	}

	public ReleaseBean findByPk( long id) throws Exception {
		Connection conn = null;
		ReleaseBean bean = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_module", "root", "root");

			PreparedStatement pstmt = conn.prepareStatement("select * from release_info where id = ?");

			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new ReleaseBean();
				
				bean.setId(rs.getLong(1));
				bean.setCode(rs.getString(2));
				bean.setVersion(rs.getString(3));
				bean.setDate(rs.getDate(4));
				bean.setStatus(rs.getString(5));
			}

			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bean;

	}

	public List search(ReleaseBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = null;
		List<ReleaseBean> list = new ArrayList<ReleaseBean>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_module", "root", "root");

			StringBuffer sql = new StringBuffer("select * from Release_info where 1=1 ");

			if (bean != null) {
				if (bean.getStatus() != null && bean.getStatus().length() > 0) {
					sql.append(" and status like '" + bean.getStatus() + "%'");
				}

				if (bean.getCode() != null && bean.getCode().length() > 0) {
					sql.append(" and code like '" + bean.getCode() + "%'");
				}
				if (bean.getId() != 0 && bean.getId() > 0) {
					sql.append(" and id = " + bean.getId());
				}
			}

			if (pageNo > 0) {
				pageNo = (pageNo - 1) * pageSize;
				sql.append(" limit " + pageNo + " , " + pageSize);
			}

			System.out.println("sql query-->" + sql.toString());
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new ReleaseBean();

				bean.setId(rs.getLong(1));
				bean.setCode(rs.getString(2));
				bean.setVersion(rs.getString(3));
				bean.setDate(rs.getDate(4));
				bean.setStatus(rs.getString(5));
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
