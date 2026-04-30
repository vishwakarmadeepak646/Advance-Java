package com.Module.FeatureAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.JDBCDataSource;

public class FeatureAccessModel {

	public Integer nextPk() {
		Connection conn = null;
		int pk = 0;

		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(AccessId) from featureAccess");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk + 1;
	}

	public long add(FeatureAccessBean bean) throws Exception {
		Connection conn = null;
		int pk = 0;
		
		FeatureAccessBean exist = findByUserName(bean.getUserName());
		
		if(exist != null) {
			throw new Exception("UserName already exist");
		}

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into featureAccess values(?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getAccessCode());
			pstmt.setString(3, bean.getFeatureName());
			pstmt.setString(4, bean.getUserName());
			pstmt.setString(5, bean.getStatus());

			long i = pstmt.executeUpdate();
			conn.commit();
			System.out.println(i + " row affected");

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	public void update(FeatureAccessBean bean) throws Exception {
		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update featureAccess set  accessCode=?, featureName=?, UserName = ?, status = ? where accessId = ? ");

			pstmt.setString(1, bean.getAccessCode());
			pstmt.setString(2, bean.getFeatureName());
			pstmt.setString(3, bean.getUserName());
			pstmt.setString(4, bean.getStatus());
			pstmt.setLong(5, bean.getAccessId());

			pstmt.executeUpdate();
			conn.commit();
			System.out.println(" row updated successsfully");

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(FeatureAccessBean bean) throws Exception {
		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from featureAccess  where accessId = ? ");

			pstmt.setLong(1, bean.getAccessId());

			pstmt.executeUpdate();
			conn.commit();
			System.out.println("Row Deleted successsfully");

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public FeatureAccessBean findByPK(long pk) {

		FeatureAccessBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from featureaccess where Accessid = ?");
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FeatureAccessBean();
				bean.setAccessId(rs.getLong(1));
				bean.setAccessCode(rs.getString(2));
				bean.setFeatureName(rs.getString(3));
				bean.setUserName(rs.getString(4));
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

	public FeatureAccessBean findByUserName(String name) {

		FeatureAccessBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from featureaccess where UserName = ?");
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FeatureAccessBean();
				bean.setAccessId(rs.getLong(1));
				bean.setAccessCode(rs.getString(2));
				bean.setFeatureName(rs.getString(3));
				bean.setUserName(rs.getString(4));
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

	public List<FeatureAccessBean> search(FeatureAccessBean bean, int pageNo, int pageSize) {

		StringBuffer sql = new StringBuffer("select * from featureaccess where 1=1");
		List<FeatureAccessBean> list = new ArrayList<FeatureAccessBean>();
		Connection conn = null;

		if (bean != null) {
			if (bean.getAccessId() > 0) {
				sql.append(" and accessId = " + bean.getAccessId());
			}
			if (bean.getUserName() != null && bean.getUserName().length() > 0) {
				sql.append(" and userName like'" + bean.getUserName() + "%'");
			}
			
			if(bean.getStatus() != null && bean.getStatus().length()>0) {
				sql.append(" and status like'" + bean.getStatus() + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + " , " + pageSize);
		}

		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new FeatureAccessBean();
				bean.setAccessId(rs.getLong(1));
				bean.setAccessCode(rs.getString(2));
				bean.setFeatureName(rs.getString(3));
				bean.setUserName(rs.getString(4));
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
