package com.dev.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dev.bean.SchedulerJobBean;
import com.dev.exception.DuplicateRecodExcepiton;
import com.dev.util.JDBCDataSource;

public class SchedulerJobModel {

	public int nextPk() {
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select max(id) from SchedulerJob");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.getConnection();
		}

		return pk + 1;
	}

	public long add(SchedulerJobBean bean) throws DuplicateRecodExcepiton {
		int pk = 0;
		Connection conn = null;

		SchedulerJobBean exists = findbyCode(bean.getCode());

		if (exists != null) {
			throw new DuplicateRecodExcepiton("Job Code already exist....");
		}

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("insert into SchedulerJob values(?,?,?,?,?) ");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getCode());
			pstmt.setString(3, bean.getName());
			pstmt.setString(4, bean.getCronExpression());
			pstmt.setString(5, bean.getStatus());
			long i = pstmt.executeUpdate();
			System.out.println(i + " Row added successfully");
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	public void update(SchedulerJobBean bean) {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"update SchedulerJob set code=?, name=?, cronExpression= ? , status =? where id = ? ");

			pstmt.setString(1, bean.getCode());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getCronExpression());
			pstmt.setString(4, bean.getStatus());
			pstmt.setLong(5, bean.getId());
			pstmt.executeUpdate();
			System.out.println(" Row updated successfully");
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(SchedulerJobBean bean) {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("delete from SchedulerJob where id= ? ");
			pstmt.setLong(1, bean.getId());

			pstmt.executeUpdate();
			System.out.println(" Row deleted successfully");
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public SchedulerJobBean findByPk(long id) {
		Connection conn = null;
		SchedulerJobBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from SchedulerJob where id= ? ");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new SchedulerJobBean();
				bean.setId(rs.getLong(1));
				bean.setCode(rs.getString(2));
				bean.setName(rs.getString(3));
				bean.setCronExpression(rs.getString(4));
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

	public SchedulerJobBean findbyCode(String code) {
		Connection conn = null;
		SchedulerJobBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from SchedulerJob where code = ? ");
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new SchedulerJobBean();
				bean.setId(rs.getLong(1));
				bean.setCode(rs.getString(2));
				bean.setName(rs.getString(3));
				bean.setCronExpression(rs.getString(4));
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

	public List<SchedulerJobBean> search(SchedulerJobBean bean, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer("select * from SchedulerJob where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id= " + bean.getId());
			}

			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like'" + bean.getName() + "%'");
			}
			if (bean.getStatus() != null && bean.getStatus().length() > 0) {
				sql.append(" and status like'" + bean.getStatus() + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + " , " + pageSize);
		}

		System.out.println(sql.toString());
		Connection conn = null;
		List<SchedulerJobBean> list = new ArrayList<SchedulerJobBean>();
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new SchedulerJobBean();
				bean.setId(rs.getLong(1));
				bean.setCode(rs.getString(2));
				bean.setName(rs.getString(3));
				bean.setCronExpression(rs.getString(4));
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
