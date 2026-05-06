package com.dev.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.dev.bean.EmployeeBean;
import com.dev.model.EmployeeModel;

public class EmployeeTest {

	public static void main(String[] args) throws Exception {
		 // getAdd();
		// getUpdate();
		// getDelete();
		// findByPk();
		// findByEmail();
		// search();
	}

	public static void getAdd() throws Exception {
		EmployeeBean bean = new EmployeeBean();
		EmployeeModel model = new EmployeeModel();

		bean.setName("Raja");
		bean.setDepartment("CS");
		bean.setSalary(40000);
		bean.setEmail("raja@gmail.com");
		long i = model.add(bean);
		System.out.println(i + " id is added");
	}

	public static void getUpdate() throws Exception {
		EmployeeBean bean = new EmployeeBean();
		EmployeeModel model = new EmployeeModel();
		bean.setId(1);
		bean.setName("Raja");
		bean.setDepartment("HR");
		bean.setSalary(70000);
		bean.setEmail("raja@gmail.com");
		model.update(bean);
	}

	public static void getDelete() throws Exception {
		EmployeeBean bean = new EmployeeBean();
		EmployeeModel model = new EmployeeModel();
		bean.setId(1);

		model.delete(bean);
	}

	public static void findByPk() throws Exception {
		EmployeeBean bean = new EmployeeBean();
		EmployeeModel model = new EmployeeModel();

		bean = model.findByPk(1);

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDepartment());
			System.out.println(bean.getSalary());
			System.out.println(bean.getEmail());
		} else {
			System.out.println("No record found");
		}
	}

	public static void findByEmail() throws Exception {
		EmployeeBean bean = new EmployeeBean();
		EmployeeModel model = new EmployeeModel();

		bean = model.findByEmail("raja@gmail.com");
		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDepartment());
			System.out.println(bean.getSalary());
			System.out.println(bean.getEmail());
		} else {
			System.out.println("No record found");
		}
	}
	
	public static void search() {
		EmployeeBean bean = new EmployeeBean();
		EmployeeModel model = new EmployeeModel();
		
		List<EmployeeBean> list = new ArrayList<EmployeeBean>();
	//	bean.setId(4);
		bean.setDepartment("IT");
		
		list = model.search(bean, 1, 5);
		
		Iterator<EmployeeBean> it = list.iterator();
		
		while(it.hasNext()) {
			bean = it.next();
			
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDepartment());
			System.out.println(bean.getSalary());
			System.out.println(bean.getEmail());
			System.out.println("-------------------");
		}
	}
}
