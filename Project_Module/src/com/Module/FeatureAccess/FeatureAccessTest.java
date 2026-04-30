package com.Module.FeatureAccess;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FeatureAccessTest {

	public static void main(String[] args) {
		// getAdd();
		// getUpdate();
		// getDelete();
		// findByPK();
		// findByUserName();
		 search();
		
	}

	public static void getAdd() {
		try {
			FeatureAccessBean bean = new FeatureAccessBean();
			FeatureAccessModel model = new FeatureAccessModel();

			bean.setAccessCode("ACC07");
			bean.setFeatureName("Dashboard");
			bean.setUserName("Rakesh");
			bean.setStatus("pending");
			long i = model.add(bean);
			System.out.println("Row record added successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getUpdate() {
		try {
			FeatureAccessBean bean = new FeatureAccessBean();
			FeatureAccessModel model = new FeatureAccessModel();
			bean.setAccessId(7);
			bean.setAccessCode("ACC07");
			bean.setFeatureName("Dashboard");
			bean.setUserName("Raman");
			bean.setStatus("pending");
			model.update(bean);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getDelete() {
		try {
			FeatureAccessBean bean = new FeatureAccessBean();
			FeatureAccessModel model = new FeatureAccessModel();
			bean.setAccessId(7);

			model.delete(bean);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void findByPK() {
		FeatureAccessBean bean = new FeatureAccessBean();
		FeatureAccessModel model = new FeatureAccessModel();
		try {
			bean = model.findByPK(1);

			if (bean != null) {
				System.out.println(bean.getAccessId());
				System.out.println(bean.getAccessCode());
				System.out.println(bean.getFeatureName());
				System.out.println(bean.getUserName());
				System.out.println(bean.getStatus());
				System.out.println("-----------------------");
			} else {
				System.out.println("No record find");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void findByUserName() {
		FeatureAccessBean bean = new FeatureAccessBean();
		FeatureAccessModel model = new FeatureAccessModel();
		try {
			bean = model.findByUserName("Ramesh");

			if (bean != null) {
				System.out.println(bean.getAccessId());
				System.out.println(bean.getAccessCode());
				System.out.println(bean.getFeatureName());
				System.out.println(bean.getUserName());
				System.out.println(bean.getStatus());
				System.out.println("-----------------------");
			} else {
				System.out.println("No record find");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void search() {
		try {
			FeatureAccessBean bean = new FeatureAccessBean();
			FeatureAccessModel model = new FeatureAccessModel();
		//	bean.setStatus("pending");
		//	bean.setAccessId(4);
		//	bean.setUserName("Rakesh");
			
			List<FeatureAccessBean> list = new ArrayList<FeatureAccessBean>();
			list = model.search(bean, 1, 4);
			Iterator<FeatureAccessBean> it = list.iterator();

			while (it.hasNext()) {
				bean = it.next();
				System.out.println(bean.getAccessId());
				System.out.println(bean.getAccessCode());
				System.out.println(bean.getFeatureName());
				System.out.println(bean.getUserName());
				System.out.println(bean.getStatus());
				System.out.println("-----------------------");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
