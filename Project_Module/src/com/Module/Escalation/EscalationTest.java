package com.Module.Escalation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EscalationTest {

	public static void main(String[] args) throws Exception {
		//getAdd();
		// getUpdate();
		// getDelete();
		// getSearch();
		
		getFindByPK();
	}

	public static void getAdd() throws Exception {
		EscalationBean bean = new EscalationBean();
		EscalationModel m = new EscalationModel();

		// bean.setId(11);
		bean.setCode("ESC0011");
		bean.setLevel("l3");
		bean.setAssignedTo("Rajan");
		bean.setStatus("Approved");

		m.add(bean);

	}

	public static void getUpdate() throws Exception {
		EscalationBean bean = new EscalationBean();
		EscalationModel m = new EscalationModel();

		bean.setId(11);
		bean.setCode("ESC0011");
		bean.setLevel("L6");
		bean.setAssignedTo("Gajanan");
		bean.setStatus("Approved");

		m.update(bean);

	}

	public static void getDelete() throws Exception {
		EscalationBean bean = new EscalationBean();
		EscalationModel m = new EscalationModel();

		bean.setId(11);

		m.delete(bean);

	}
	
	public static void getFindByPK() {
		
		EscalationBean bean = new EscalationBean();
		EscalationModel m = new EscalationModel();
		
		bean = m.findByPK(3);
		
		if(bean != null) {
			
			System.out.println(bean.getId());
			System.out.println(bean.getCode());
			System.out.println(bean.getLevel());
			System.out.println(bean.getAssignedTo());
			System.out.println(bean.getStatus());
			System.out.println("-----------------------");
		}else {
			System.out.println("No record found");
		}
		
		
	}

	public static void getSearch() throws Exception {
		EscalationBean bean = new EscalationBean();
		EscalationModel m = new EscalationModel();
		List<EscalationBean> list = new ArrayList<EscalationBean>();

		list = m.search(bean, 1, 5);

		Iterator<EscalationBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();

			System.out.println(bean.getId());
			System.out.println(bean.getCode());
			System.out.println(bean.getLevel());
			System.out.println(bean.getAssignedTo());
			System.out.println(bean.getStatus());
			System.out.println("-----------------------");
		}

	}
}
