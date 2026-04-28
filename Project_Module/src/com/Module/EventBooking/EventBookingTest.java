package com.Module.EventBooking;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EventBookingTest {

	public static void main(String[] args) throws Exception {
		// getAdd();
		// getUpdate();
		// getDelete();
		// findByPK();
		// testfindByDate() ;
		search();
	}

	public static void getAdd() throws Exception {
		try {
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			EventBookingBean bean = new EventBookingBean();
			EventBookingModel model = new EventBookingModel();
			bean.setName("Retirement party");
			bean.setDate(s.parse("2026-05-12"));
			bean.setSeat(220);
			long l = model.add(bean);
			System.out.println(l + " id added in table");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getUpdate() throws Exception {

		try {
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			EventBookingBean bean = new EventBookingBean();
			EventBookingModel model = new EventBookingModel();
			bean.setId(11);
			bean.setName("Party");
			bean.setDate(s.parse("2026-04-26"));
			bean.setSeat(100);
			model.update(bean);
			System.out.println("Row updated successfully...");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void getDelete() {
		try {
			EventBookingBean bean = new EventBookingBean();
			EventBookingModel model = new EventBookingModel();
			bean.setId(11);
			model.delete(bean);
			System.out.println("Row deleted Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void findByPK() {

		try {
			EventBookingBean bean = new EventBookingBean();
			EventBookingModel model = new EventBookingModel();

			bean = model.findByPk(1);
			if (bean != null) {
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getDate());
				System.out.println(bean.getSeat());
			} else {
				System.out.println("Record Not found");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testfindByDate() {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		try {
			EventBookingBean bean = new EventBookingBean();
			EventBookingModel model = new EventBookingModel();
			bean = model.findByDate(s.parse("2026-05-12"));

			if (bean != null) {
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getDate());
				System.out.println(bean.getSeat());
			} else {
				System.out.println("Record Not found");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void search() {
		try {
			EventBookingBean bean = new EventBookingBean();
			EventBookingModel model = new EventBookingModel();
			List<EventBookingBean> list = new ArrayList<EventBookingBean>();
			list = model.search(bean, 1, 5);
			Iterator<EventBookingBean> it = list.iterator();

			while (it.hasNext()) {
				bean = it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getDate());
				System.out.println(bean.getSeat());
				System.out.println("---------------");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
