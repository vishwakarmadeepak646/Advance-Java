package com.Project.Module;

import java.util.Iterator;
import java.util.List;

public class TestComplaint {

	public static void main(String[] args) throws Exception {

		getAdd();
		// getUpdate();
		// getDelete();
		// getSearch();
		// getEmail();
	}

	public static void getAdd() throws Exception {
		ComplaintBean c = new ComplaintBean();
		ComplaintModel cm = new ComplaintModel();
		c.setId(cm.nextPk());
		c.setUser_name("Radha");
		c.setComplaint_type("Torcher");
		c.setDescription("Mentally torcher");
		c.setStatus("InProgress");
		c.setEmail("radha@gmail.com");

		int i = cm.add(c);

		System.out.println(i + "th row inserted");
	}

	public static void getUpdate() throws Exception {
		ComplaintBean c = new ComplaintBean();

		c.setId(2);
		c.setUser_name("Radha");
		c.setComplaint_type("Torcher");
		c.setDescription("Mentally torcher");
		c.setStatus("InProgress");

		ComplaintModel cm = new ComplaintModel();
		cm.update(c);

	}

	public static void getDelete() throws Exception {

		ComplaintBean c = new ComplaintBean();

		c.setId(13);
		c.setStatus("InProgress");

		ComplaintModel cm = new ComplaintModel();
		cm.delete(c);

	}

	public static void getEmail() throws Exception {

		ComplaintBean bean = new ComplaintBean();
		ComplaintModel m = new ComplaintModel();

		bean = m.FindByEmail("vikas@gmail.com");

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getUser_name());
			System.out.print("\t\t" + bean.getComplaint_type());
			System.out.print("\t\t" + bean.getDescription());
			System.out.print("\t\t" + bean.getStatus());
			System.out.println("\t" + bean.getEmail());
		}

	}

	public static void getSearch() throws Exception {
		ComplaintBean bean = new ComplaintBean();
		ComplaintModel m = new ComplaintModel();

		List<ComplaintBean> list = m.search(bean, 1, 3);

		Iterator<ComplaintBean> it = list.iterator();

		System.out.print("id");
		System.out.print("\t" + "User Name");
		System.out.print("\t" + "Complaint Tpe");
		System.out.print("\t" + "Description");
		System.out.print("\t" + "Status");
		System.out.println("\t" + "Email");

		while (it.hasNext()) {
			bean = it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getUser_name());
			System.out.print("\t\t" + bean.getComplaint_type());
			System.out.print("\t\t" + bean.getDescription());
			System.out.print("\t\t" + bean.getStatus());
			System.out.println("\t" + bean.getEmail());

		}

	}

}
