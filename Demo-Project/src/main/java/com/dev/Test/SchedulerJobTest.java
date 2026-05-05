package com.dev.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.dev.bean.SchedulerJobBean;
import com.dev.exception.DuplicateRecodExcepiton;
import com.dev.model.SchedulerJobModel;

public class SchedulerJobTest {

	public static void main(String[] args) throws DuplicateRecodExcepiton {
		 getAdd();
		// getUpdate();
		// getDelete();
		//findByPK();
		//  search();
		// findByCode() ;
	}

	public static void getAdd() throws DuplicateRecodExcepiton {
		SchedulerJobBean bean = new SchedulerJobBean();
		SchedulerJobModel m = new SchedulerJobModel();
		bean.setCode("JOB011");
		bean.setName("NotificationCleanup");
		bean.setCronExpression("0 0 4 * * ?");
		bean.setStatus("Pending");
		long i = m.add(bean);
	}

	public static void getUpdate() {
		SchedulerJobBean bean = new SchedulerJobBean();
		SchedulerJobModel m = new SchedulerJobModel();
		bean.setId(11);
		bean.setCode("JOB011");
		bean.setName("NotificationCleanup");
		bean.setCronExpression("0 0 4 * * ?");
		bean.setStatus("Approved");
		m.update(bean);
	}

	public static void getDelete() {
		SchedulerJobBean bean = new SchedulerJobBean();
		SchedulerJobModel m = new SchedulerJobModel();
		bean.setId(11);

		m.delete(bean);
	}

	public static void findByPK() {
		SchedulerJobBean bean = new SchedulerJobBean();
		SchedulerJobModel m = new SchedulerJobModel();
		bean = m.findByPk(1);

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getCode());
			System.out.println(bean.getName());
			System.out.println(bean.getCronExpression());
			System.out.println(bean.getStatus());
		} else {
			System.out.println("No record");
		}
	}
	
	public static void findByCode() {
		SchedulerJobBean bean = new SchedulerJobBean();
		SchedulerJobModel m = new SchedulerJobModel();
		bean = m.findbyCode("JOB006");

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getCode());
			System.out.println(bean.getName());
			System.out.println(bean.getCronExpression());
			System.out.println(bean.getStatus());
		} else {
			System.out.println("No record");
		}
	}

	public static void search() {
		SchedulerJobBean bean = new SchedulerJobBean();
		SchedulerJobModel m = new SchedulerJobModel();
		List<SchedulerJobBean> list = new ArrayList<SchedulerJobBean>();

		list = m.search(bean, 1, 5);
		Iterator<SchedulerJobBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getCode());
			System.out.println(bean.getName());
			System.out.println(bean.getCronExpression());
			System.out.println(bean.getStatus());
			System.out.println("-----------------");
		}
	}

}
