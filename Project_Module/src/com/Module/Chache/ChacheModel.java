package com.Module.Chache;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.JDBCDataSource;

public class ChacheModel {

	public Integer nextPk() {
		int pk = 0;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from chache");
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

	public long add(ChacheBean bean) throws Exception {
		Connection conn = null;
		int pk = 0;

		ChacheBean exist = findByCode(bean.getCode());

		if (exist != null) {
			throw new Exception("Code id already exist");
		}
		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into Chache values(?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getCode());
			pstmt.setString(3, bean.getKeyName());
			pstmt.setString(4, bean.getValue());
			pstmt.setString(5, bean.getStatus());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println(i + " Row affected");
			pstmt.close();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}

		return pk;
	}

	public void update(ChacheBean bean) throws Exception {
		Connection conn = null;
		int pk = 0;
		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("update Chache set code= ?, keyName = ?, value= ?, status =? where id=?");

			pstmt.setString(1, bean.getCode());
			pstmt.setString(2, bean.getKeyName());
			pstmt.setString(3, bean.getValue());
			pstmt.setString(4, bean.getStatus());
			pstmt.setLong(5, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println(i + " Row updated and affected");
			pstmt.close();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}

	}

	public void delete(ChacheBean bean) throws Exception {
		Connection conn = null;
		int pk = 0;
		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from Chache where id=?");

			pstmt.setLong(1, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println(i + " Row deleted and affected");
			pstmt.close();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}

	}

	public ChacheBean findByPk(long id) {
		Connection conn = null;
		ChacheBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from Chache where id = ?");
			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new ChacheBean();
				bean.setId(rs.getLong(1));
				bean.setCode(rs.getString(2));
				bean.setKeyName(rs.getString(3));
				bean.setValue(rs.getString(4));
				bean.setStatus(rs.getString(5));
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

	public ChacheBean findByCode(String code) {
		Connection conn = null;
		ChacheBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from Chache where code = ?");
			pstmt.setString(1, code);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new ChacheBean();
				bean.setId(rs.getLong(1));
				bean.setCode(rs.getString(2));
				bean.setKeyName(rs.getString(3));
				bean.setValue(rs.getString(4));
				bean.setStatus(rs.getString(5));
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

	public List<ChacheBean> search(ChacheBean bean, int PageNo, int pageSize) {

		StringBuffer sql = new StringBuffer("select * from Chache where 1=1");

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append("and id = " + bean.getId());
			}

			if (bean.getStatus() != null && bean.getStatus().length() > 0) {
				sql.append(" and status like'" + bean.getStatus() + "%'");
			}
			if (bean.getCode() != null && bean.getCode().length() > 0) {
				sql.append(" and code like'" + bean.getCode() + "%'");
			}
		}

		if (pageSize > 0) {
			PageNo = (PageNo - 1) * pageSize;
			sql.append(" limit " + PageNo + " , " + pageSize);
		}

		Connection conn = null;
		List<ChacheBean> list = new ArrayList<ChacheBean>();
		System.out.println("Sql Query ---> " + sql.toString());

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new ChacheBean();
				bean.setId(rs.getLong(1));
				bean.setCode(rs.getString(2));
				bean.setKeyName(rs.getString(3));
				bean.setValue(rs.getString(4));
				bean.setStatus(rs.getString(5));
				list.add(bean);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}
}
