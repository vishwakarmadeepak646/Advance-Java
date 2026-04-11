package com.Module.Export;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExportTest {

	public static void main(String[] args) throws Exception {

		getAdd();
		// getUpdate();
		// getDelete();
		//getSearch();
	}

	public static void getAdd() throws Exception {

		ExportBean bean = new ExportBean();
		ExportModel m = new ExportModel();

		//bean.setId(11);
		bean.setCode("Export");
		bean.setFileName("Data.txt");
		bean.setFormat("txt");
		bean.setStatus("Approved");
		m.add(bean);
		

	}

	public static void getUpdate() throws Exception {
		ExportBean bean = new ExportBean();
		ExportModel m = new ExportModel();

		bean.setId(11);
		bean.setCode("Import");
		bean.setFileName("Data2.txt");
		bean.setFormat("txt");
		bean.setStatus("Approved");
		m.update(bean);

	}

	public static void getDelete() throws Exception {
		ExportBean bean = new ExportBean();
		ExportModel m = new ExportModel();

		bean.setId(11);
		m.delete(bean);
	}

	public static void getSearch() throws Exception {
		
		ExportBean bean = new ExportBean();
		ExportModel m = new ExportModel();
		List<ExportBean> list = new ArrayList<ExportBean>();
		
		//bean.setStatus("p");
		//bean.setId(5);

		list = m.search(bean, 1, 5);

		Iterator<ExportBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();

			System.out.println(bean.getId());
			System.out.println(bean.getCode());
			System.out.println(bean.getFileName());
			System.out.println(bean.getFormat());
			System.out.println(bean.getStatus());
			System.out.println("-----------------------");
		}
	}
}
