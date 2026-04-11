package com.Module.Export;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExportModel {

	public long FindbyPk() throws Exception {
		long pk =0;

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_module", "root", "root");

		PreparedStatement pstmt = conn.prepareStatement("Select max(id) from export");

		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()){
			pk = rs.getLong(1);
		}
		
		System.out.println((pk + 1) + " Id is inserted" );
		return pk + 1;
	}

	public void add(ExportBean bean) throws Exception {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into export values(?,?,?,?,?)");

			pstmt.setLong(1, FindbyPk());
			pstmt.setString(2, bean.getCode());
			pstmt.setString(3, bean.getFileName());
			pstmt.setString(4, bean.getFormat());
			pstmt.setString(5, bean.getStatus());

			int i = pstmt.executeUpdate();

			System.out.println(i + "row inserted in table");

			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

	}

	public void update(ExportBean bean) throws Exception {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("update export set code = ?, fileName = ?, format = ?, status = ? where id = ?");

			pstmt.setString(1, bean.getCode());
			pstmt.setString(2, bean.getFileName());
			pstmt.setString(3, bean.getFormat());
			pstmt.setString(4, bean.getStatus());
			pstmt.setLong(5, bean.getId());

			int i = pstmt.executeUpdate();

			System.out.println(i + " row is updated in table");

			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

	}

	public void delete(ExportBean bean) throws Exception {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from export where id = ?");

			pstmt.setLong(1, bean.getId());

			int i = pstmt.executeUpdate();

			System.out.println(i + " row is deleted from table");

			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

	}

	public List search(ExportBean bean, int pageNo, int pageSize) throws Exception {
		Connection conn = null;
		List<ExportBean> list = new ArrayList<ExportBean>();

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_module", "root", "root");

			StringBuffer sql = new StringBuffer("select * from Export where 1=1");

			if (bean != null) {

				if (bean.getStatus() != null && bean.getStatus().length() > 0) {
					sql.append(" and status like '" + bean.getStatus() + "%'");
				}

				if (bean.getCode() != null && bean.getCode().length() > 0) {
					sql.append(" and code = " + bean.getCode());
				}

				if (bean.getFormat() != null && bean.getFormat().length() > 0) {
					sql.append(" and format like '" + bean.getFormat() + "%'");
				}

				if (bean.getId() != 0 && bean.getId() > 0) {
					sql.append(" and id =" + bean.getId());
				}
			}

			if (pageNo > 0) {

				pageNo = (pageNo - 1) * pageSize;
				sql.append(" limit " + pageNo + "," + pageSize);
			}

			System.out.println("Sql query-->" + sql.toString());

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new ExportBean();

				bean.setId(rs.getLong(1));
				bean.setCode(rs.getString(2));
				bean.setFileName(rs.getString(3));
				bean.setFormat(rs.getString(4));
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
