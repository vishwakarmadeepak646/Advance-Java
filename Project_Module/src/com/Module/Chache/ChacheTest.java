package com.Module.Chache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChacheTest {

	public static void main(String[] args) {
		 getAdd();
		// getUpdate();
		// getDelete();
		// findByPK();
		// findByCode();
		//search();
	}

	public static void getAdd() {
		try {

			ChacheBean bean = new ChacheBean();
			ChacheModel m = new ChacheModel();
			bean.setCode("CH012");
			bean.setKeyName("apiLimit");
			bean.setValue("18");
			bean.setStatus("Rejected");
			m.add(bean);
			System.out.println("User added successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getDelete() {
		try {

			ChacheBean bean = new ChacheBean();
			ChacheModel m = new ChacheModel();

			bean.setId(11);
			m.delete(bean);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void findByPK() {
		try {

			ChacheBean bean = new ChacheBean();
			ChacheModel m = new ChacheModel();

			bean = m.findByPk(1);

			if (bean != null) {
				System.out.println(bean.getId());
				System.out.println(bean.getCode());
				System.out.println(bean.getKeyName());
				System.out.println(bean.getValue());
				System.out.println(bean.getStatus());
				System.out.println("-------------");
			} else {
				System.out.println("No record found");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void findByCode() {
		try {

			ChacheBean bean = new ChacheBean();
			ChacheModel m = new ChacheModel();

			bean = m.findByCode("CH003");

			if (bean != null) {
				System.out.println(bean.getId());
				System.out.println(bean.getCode());
				System.out.println(bean.getKeyName());
				System.out.println(bean.getValue());
				System.out.println(bean.getStatus());
				System.out.println("-------------");
			} else {
				System.out.println("No record found");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void search() {
		try {

			ChacheBean bean = new ChacheBean();
			ChacheModel m = new ChacheModel();
			List<ChacheBean> list = new ArrayList<ChacheBean>();
			list = m.search(bean, 1, 5);
			Iterator<ChacheBean> it = list.iterator();

			while (it.hasNext()) {
				bean = it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getCode());
				System.out.println(bean.getKeyName());
				System.out.println(bean.getValue());
				System.out.println(bean.getStatus());
				System.out.println("-------------");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
