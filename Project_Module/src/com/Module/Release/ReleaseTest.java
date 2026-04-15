package com.Module.Release;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ReleaseTest {

	public static void main(String[] args) throws Exception {
		getAdd();
		// getUpdate();
		// getDelete();
		// getSearch();
	}

	public static void getAdd() throws Exception {

		ReleaseBean bean = new ReleaseBean();
		ReleaseModel m = new ReleaseModel();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

		// bean.setId(11);
		bean.setCode("REl011");
		bean.setVersion("v.3.01");
		bean.setDate(s.parse("2000-09-08"));
		bean.setStatus("approved");

		m.add(bean);
	}

	public static void getUpdate() throws Exception {

		ReleaseBean bean = new ReleaseBean();
		ReleaseModel m = new ReleaseModel();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

		bean.setId(11);
		bean.setCode("REl011");
		bean.setVersion("v.3.11");
		bean.setDate(s.parse("1998-09-08"));
		bean.setStatus("approved");

		m.update(bean);

	}

	public static void getDelete() throws Exception {

		ReleaseBean bean = new ReleaseBean();
		ReleaseModel m = new ReleaseModel();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

		bean.setId(11);
		bean.setCode("REl011");
		bean.setVersion("v.3.11");
		bean.setDate(s.parse("1998-09-08"));
		bean.setStatus("approved");

		m.delete(bean);

	}

	public static void getSearch() throws Exception {
		ReleaseBean bean = new ReleaseBean();
		ReleaseModel m = new ReleaseModel();
		List<ReleaseBean> list = new ArrayList<ReleaseBean>();

		// bean.setId(5);
		// bean.setCode("REL005");
		bean.setStatus("Completed");

		list = m.search(bean, 1, 5);

		Iterator<ReleaseBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getCode());
			System.out.println(bean.getVersion());
			System.out.println(bean.getDate());
			System.out.println(bean.getStatus());
			System.out.println("----------------------");
		}
	}

}
