package com.Project.Module;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestLogin {

	public static void main(String[] args) throws Exception {
		 Add();
		// Update();
		// Delete();
		// FindByPK();
		 
		//getSearch();

	}

	public static void Add() throws Exception {
		LoginBean bean = new LoginBean();
		LoginModel m = new LoginModel();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

		bean.setId(21);
		bean.setLoginCode("LC002");
		bean.setUserName("Amit");
		bean.setLoginTime(s.parse("2026-03-02"));
		bean.setStatus("Active");

		long i = m.add(bean);
		System.out.println(i + " st row inserted");

		System.out.println("--------------------------");
	}

	public static void Update() throws Exception {
		LoginBean bean = new LoginBean();
		LoginModel m = new LoginModel();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

		bean.setId(2);
		bean.setLoginCode("LC002");
		bean.setUserName("Ramal");
		bean.setLoginTime(s.parse("2026-03-02"));
		bean.setStatus("Active");

		m.update(bean);
		System.out.println("--------------------------");
	}

	public static void Delete() throws Exception {
		LoginBean bean = new LoginBean();
		LoginModel m = new LoginModel();

		bean.setId(2);

		m.delete(bean);
		System.out.println("--------------------------");
	}

	public static void FindByPK() {
		LoginBean bean = new LoginBean();
		LoginModel m = new LoginModel();
		bean = m.FindByPk(3);
		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getLoginCode());
			System.out.println(bean.getUserName());
			System.out.println(bean.getLoginTime());
			System.out.println(bean.getStatus());

			System.out.println("---------------");
		} else {
			System.out.println("Result Not found");
		}
	}

	public static void FindbyLoginCode() {
		LoginBean bean = new LoginBean();
		LoginModel m = new LoginModel();
		bean = m.FindByPk(3);
		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getLoginCode());
			System.out.println(bean.getUserName());
			System.out.println(bean.getLoginTime());
			System.out.println(bean.getStatus());

			System.out.println("---------------");
		} else {
			System.out.println("Result Not found");
		}
	}

	public static void FindByLoginCode() {

		LoginBean bean = new LoginBean();
		LoginModel m = new LoginModel();

		bean = m.FindbyLoginCode("LC007");

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getLoginCode());
			System.out.println(bean.getUserName());
			System.out.println(bean.getLoginTime());
			System.out.println(bean.getStatus());

			System.out.println("---------------");
		} else {
			System.out.println("Result Not found");
		}
	}

	public static void getSearch() throws Exception {
		LoginBean bean = new LoginBean();
		LoginModel m = new LoginModel();

		List<LoginBean> list = new ArrayList<LoginBean>();

		// bean.setUserName("r");
		// bean.setLoginCode("LC005");
		bean.setId(8);

		list = m.search(bean, 1, 5);

		Iterator<LoginBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getLoginCode());
			System.out.println(bean.getUserName());
			System.out.println(bean.getLoginTime());
			System.out.println(bean.getStatus());

			System.out.println("---------------");
		}
	}

}
