package com.dev.PreparedStatement;

import java.text.SimpleDateFormat;

public class TestUserModel {

	public static void main(String[] args) throws Exception {

		getAdd();

	}

	public static void getAdd() throws Exception {

		UserBean bean = new UserBean();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

		bean.setId(12);
		bean.setFirst_name("Deepak");
		bean.setLast_name("Vishwakarma");
		bean.setLogin("dkv@gmail.com");
		bean.setPassword("deepak@123");
		bean.setDob(s.parse("1999-10-10"));

		UserModel um = new UserModel();
		int i = um.add(bean);
		System.out.println("Record inserted at id : " + i);
	}

}
