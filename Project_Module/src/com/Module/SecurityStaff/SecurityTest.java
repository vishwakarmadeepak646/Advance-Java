package com.Module.SecurityStaff;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SecurityTest {

	public static void main(String[] args) throws Exception {
		 getAdd();

		// getUpdate();
		// getDelete();
		// getSearch();
		//FindbyEmail();

	}

	public static void getAdd() throws Exception {
		SecurityBean bean = new SecurityBean();

		SecurityModel m = new SecurityModel();

		// bean.setId(13);
		bean.setName("rahul");
		bean.setShift("Night");
		bean.setSalary(12000.50);
		bean.setEmail("rahul@gmail.com");

		m.add(bean);

		// System.out.println(i + " id row inserted");
	}

	public static void getUpdate() throws Exception {
		SecurityBean bean = new SecurityBean();

		SecurityModel m = new SecurityModel();

		bean.setId(1);
		bean.setName("ramesh sen");
		bean.setShift("Day");
		bean.setSalary(13500);
		bean.setEmail("rameshsen@gmail.com");

		m.update(bean);

		System.out.println("--------------------");
	}

	public static void getDelete() throws Exception {

		SecurityBean bean = new SecurityBean();

		SecurityModel m = new SecurityModel();

		bean.setId(0);
		m.delete(bean);

		System.out.println("-------------------");
	}

	public static void FindbyEmail() throws Exception {
		SecurityBean bean = new SecurityBean();
		SecurityModel m = new SecurityModel();
		
		bean = m.FindbyEmail("naresh@gmail.com");
		
		if(bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getShift());
			System.out.println(bean.getSalary());
			System.out.println(bean.getEmail());
			System.out.println("------------------------");
		}
		
		
	}

	public static void getSearch() throws Exception {
		SecurityBean bean = new SecurityBean();
		SecurityModel m = new SecurityModel();
		List<SecurityBean> list = new ArrayList<SecurityBean>();
		// bean.setEmail("r");
		// bean.setId(5);
		bean.setName("r");
		list = m.search(bean, 1, 5);
		Iterator<SecurityBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getShift());
			System.out.println(bean.getSalary());
			System.out.println(bean.getEmail());
			System.out.println("------------------------");

		}
	}

}
