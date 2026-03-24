package com.dev.PreparedStatement;

import java.text.SimpleDateFormat;

import java.util.Iterator;
import java.util.List;

public class TestUserModel {

	public static void main(String[] args) throws Exception {

		// getAdd();
		// getUpdate();
		// getDelete();
		// getFindByPK();
		// getFindByLogin();
		// getAuthentication();
		getSearch();

	}

	public static void getAdd() throws Exception {

		UserBean bean = new UserBean();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

		bean.setId(14);
		bean.setFirst_name("Deepak");
		bean.setLast_name("Vishwakarma");
		bean.setLogin("dkv@gmail.com");
		bean.setPassword("deepak@123");
		bean.setDob(s.parse("1999-10-10"));

		UserModel um = new UserModel();
		int i = um.add(bean);
		System.out.println("Record inserted at id : " + i);
	}

	public static void getUpdate() throws Exception {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		UserBean bean = new UserBean();
		bean.setFirst_name("Kapil");
		bean.setLast_name("Vishwakarma");
		bean.setLogin("kapil@gmail.com");
		bean.setPassword("kapil123");
		bean.setDob(s.parse("2000-04-28"));
		bean.setId(10);

		UserModel um = new UserModel();
		um.update(bean);
	}

	public static void getDelete() throws Exception {

		UserBean bean = new UserBean();

		bean.setId(11);

		UserModel um = new UserModel();
		um.delete(bean);
	}

	public static void getFindByPK() throws Exception {

		UserBean bean = new UserBean();
		UserModel um = new UserModel();

		bean = um.FindByPK(2);

		if (bean != null) {
			System.out.print(bean.getID());
			System.out.print("\t" + bean.getFirst_name());
			System.out.print("\t" + bean.getLast_name());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.println("\t" + bean.getDob());
		} else {
			System.out.println("Record Not Found");
		}

	}

	public static void getFindByLogin() throws Exception {
		UserBean bean = new UserBean();
		UserModel u = new UserModel();
		bean = u.FindByLogin("amit05");

		if (bean != null) {
			System.out.print(bean.getID());
			System.out.print("\t" + bean.getFirst_name());
			System.out.print("\t" + bean.getLast_name());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.println("\t" + bean.getDob());
		} else {
			System.out.println("Record Not Found");
		}
	}

	public static void getAuthentication() throws Exception {

		UserBean bean = new UserBean();
		UserModel u = new UserModel();

		bean = u.authentication("vikas03", "vikas789");

		if (bean != null) {
			System.out.print(bean.getID());
			System.out.print("\t" + bean.getFirst_name());
			System.out.print("\t" + bean.getLast_name());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.println("\t" + bean.getDob());
		} else {
			System.out.println("Record Not Found");
		}

	}

	public static void getSearch() throws Exception {
		UserBean bean = new UserBean();
		UserModel u = new UserModel();

		// bean.setFirst_name("deepak"); //--> Here we can search with mentioned options
		// like first/last Name and id
		// bean.setLast_name("vish");
		// bean.setId(3); // if we don't use any of them then it will fetch all the
		// coloumns

		List<UserBean> list = u.search(bean, 2, 4);

		Iterator<UserBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();

			System.out.print(bean.getID());
			System.out.print("\t" + bean.getFirst_name());
			System.out.print("\t\t" + bean.getLast_name());
			System.out.print("\t\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.println("\t" + bean.getDob());

		}
	}

}
