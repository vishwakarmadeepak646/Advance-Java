package com.Module.BlockList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlockListModel {

	public long nextPk() throws Exception {
		Connection conn = null;
		long pk = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Module", "root", "root");
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from BlockList");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getLong(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

		return pk + 1;
	}

	public long add(BlockListBean bean) throws Exception {
		Connection conn = null;
		long pk = nextPk();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into BlockList values(?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getCode());
			pstmt.setString(3, bean.getName());
			pstmt.setString(4, bean.getReason());
			pstmt.setString(5, bean.getStatus());

			int i = pstmt.executeUpdate();
			System.out.println(i + " row affected");

			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pk;
	}

	public void update(BlockListBean bean) throws Exception {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("update BlockList set code= ?, name = ? , reason = ? , status = ? where id= ?");

			pstmt.setString(1, bean.getCode());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getReason());
			pstmt.setString(4, bean.getStatus());
			pstmt.setLong(5, bean.getId());

			int i = pstmt.executeUpdate();
			System.out.println(i + " row Updated");

			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delete(BlockListBean bean) throws Exception {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Module", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from BlockList where id= ?");

			pstmt.setLong(1, bean.getId());

			int i = pstmt.executeUpdate();
			System.out.println(i + " row Deleted");

			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public BlockListBean FindByPk(long id) throws Exception {
		BlockListBean bean = new BlockListBean();
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Module", "root", "root");
			PreparedStatement pstmt = conn.prepareStatement("select * from BlockList where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new BlockListBean();

				bean.setId(rs.getLong(1));
				bean.setCode(rs.getString(2));
				bean.setName(rs.getString(3));
				bean.setReason(rs.getString(4));
				bean.setStatus(rs.getString(5));

			}

			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

		return bean;
	}

	public List search(BlockListBean bean, int pageNo, int pageSize) throws Exception {

		List<BlockListBean> list = new ArrayList<BlockListBean>();
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Module", "root", "root");

			StringBuffer sql = new StringBuffer("select * from BlockList where 1=1");

			if (bean != null) {
				if (bean.getName() != null && bean.getName().length() > 0) {
					sql.append(" and name like '" + bean.getName() + "%'");
				}

				if (bean.getReason() != null && bean.getReason().length() > 0) {
					sql.append(" and reason like '" + bean.getReason() + "%'");
				}

				if (bean.status != null && bean.getStatus().length() > 0) {
					sql.append(" and status like '" + bean.getStatus() + "%'");
				}
				if (bean.code != null && bean.code.length() > 0) {
					sql.append(" and code = '" + bean.getCode() + "'");
				}
			}

			if (pageNo > 0) {

				pageNo = (pageNo - 1) * pageSize;
				sql.append(" limit " + pageNo + "," + pageSize);
			}

			System.out.println("Sql query--> " + sql.toString());

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new BlockListBean();

				bean.setId(rs.getLong(1));
				bean.setCode(rs.getString(2));
				bean.setName(rs.getString(3));
				bean.setReason(rs.getString(4));
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
