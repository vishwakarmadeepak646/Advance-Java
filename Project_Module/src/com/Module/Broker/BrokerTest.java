package com.Module.Broker;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BrokerTest {

	public static void main(String[] args) throws Exception {

		 //getAdd();
		// getUpdate();
		// getDelete();
		// getSearch();
		findByPk();
	}

	public static void getAdd() throws Exception {
		BrokerBean bean = new BrokerBean();
		BrokerModel m = new BrokerModel();
		bean.setName("Rakesh");
		bean.setContactNumber("9900654543");
		bean.setCompany("DHL");
		m.add(bean);

	}

	public static void getUpdate() throws Exception {
		BrokerBean bean = new BrokerBean();
		BrokerModel m = new BrokerModel();
		bean.setId(11);
		bean.setName("Bhavesh");
		bean.setContactNumber("9900654543");
		bean.setCompany("DHL");
		m.update(bean);

	}

	public static void getDelete() throws Exception {
		BrokerBean bean = new BrokerBean();
		BrokerModel m = new BrokerModel();
		bean.setId(13);

		m.delete(bean);

	}
	
	public static void findByPk() throws Exception {
		
		BrokerBean bean = new BrokerBean();
		BrokerModel m = new BrokerModel();
		
		bean = m.findByPk(4);
		
		if(bean != null) {
			
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getContactNumber());
			System.out.println(bean.getCompany());
			System.out.println("-----------------");
		}else{
			System.out.println("No record found");
		}

	}

	public static void getSearch() throws Exception {
		BrokerBean bean = new BrokerBean();
		BrokerModel m = new BrokerModel();

		List<BrokerBean> list = new ArrayList<BrokerBean>();
		// bean.setId(3);
		//bean.setName("D");
		bean.setCompany("DHL");
		list = m.search(bean, 1, 5);

		Iterator<BrokerBean> it = list.iterator();

		while(it.hasNext()) {
			bean = it.next();
			
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getContactNumber());
			System.out.println(bean.getCompany());
			System.out.println("-----------------");
		}
	}
}
