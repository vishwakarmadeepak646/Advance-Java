package com.Module.Tracking;

import java.util.Iterator;
import java.util.List;

public class TestTracking {

	public static void main(String[] args) throws Exception {

		getAdd();
		// getUpdate();
		// getDelete();
		// getFindByPK();
		// getSearch();
	}

	public static void getAdd() throws Exception {
		TrackingBean bean = new TrackingBean();
		TrackingModel m = new TrackingModel();

		bean.setId(m.nextPk());
		bean.setNumber("TRK1012");
		bean.setLocation("Sagar");
		bean.setStatus("Shipped");

		long i = m.add(bean);

		System.out.println(i + " This id is added");
	}

	public static void getUpdate() throws Exception {
		TrackingBean bean = new TrackingBean();
		TrackingModel m = new TrackingModel();

		bean.setId(12);
		bean.setNumber("TRK1012");
		bean.setLocation("Khurai");
		bean.setStatus("Shipped");

		m.update(bean);

	}

	public static void getDelete() throws Exception {
		TrackingBean bean = new TrackingBean();
		TrackingModel m = new TrackingModel();

		bean.setId(12);
		bean.setNumber("TRK1012");
		bean.setLocation("Khurai");
		bean.setStatus("Shipped");

		m.delete(bean);

	}

	public static void getFindByPK() throws Exception {
		TrackingBean bean = new TrackingBean();
		TrackingModel m = new TrackingModel();

		bean = m.FindByPK(4);

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getNumber());
			System.out.println(bean.getLocation());
			System.out.println(bean.getStatus());

			System.out.println("----------------------");
		}

	}

	public static void getSearch() throws Exception {
		TrackingBean bean = new TrackingBean();
		TrackingModel m = new TrackingModel();

		bean.setStatus("s");
		bean.setLocation("d");
//		bean.setId(5);

		List<TrackingBean> list = m.search(bean, 1, 5);

		Iterator<TrackingBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();

			System.out.println(bean.getId());
			System.out.println(bean.getNumber());
			System.out.println(bean.getLocation());
			System.out.println(bean.getStatus());

			System.out.println("----------------------");
		}

	}

}
