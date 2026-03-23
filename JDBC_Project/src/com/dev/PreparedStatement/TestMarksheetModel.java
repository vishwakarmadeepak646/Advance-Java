package com.dev.PreparedStatement;

public class TestMarksheetModel {

	public static void main(String[] args) throws Exception {

		// getAdd();
		// getUpdate();
		// getDelete();
		// getFindById();
		getFindByName();

	}

	public static void getAdd() throws Exception {

		MarksheetBean bean = new MarksheetBean();

		bean.setRollNo(13);
		bean.setName("Rahul Prajapati");
		bean.setPhy(78);
		bean.setChm(94);
		bean.setMaths(88);

		MarksheetModel m = new MarksheetModel();
		int i = m.add(bean);

		System.out.println("Record Inserted at : " + i);
	}

	public static void getUpdate() throws Exception {
		MarksheetBean bean = new MarksheetBean();

		bean.setName("Rakesh");
		bean.setPhy(99);
		bean.setChm(99);
		bean.setMaths(99);
		bean.setRollNo(11);

		MarksheetModel m = new MarksheetModel();
		m.update(bean);
	}

	public static void getDelete() throws Exception {
		MarksheetBean bean = new MarksheetBean();

		bean.setRollNo(13);

		MarksheetModel m = new MarksheetModel();
		m.delete(bean);

	}

	public static void getFindById() throws Exception {

		MarksheetBean bean = new MarksheetBean();
		MarksheetModel m = new MarksheetModel();

		bean = m.findByID(1);

		if (bean != null) {
			System.out.println(bean.getRollNo());
			System.out.println(bean.getName());
			System.out.println(bean.getPhy());
			System.out.println(bean.getChm());
			System.out.println(bean.getMaths());
		} else {
			System.out.println("No Record found");
		}

	}
	
	public static void getFindByName() throws Exception{
		MarksheetBean bean = new MarksheetBean();
		MarksheetModel m = new MarksheetModel();

		bean = m.findByName("Harish"); // Here we are not getting two name ?

		if (bean != null) {
			System.out.println(bean.getRollNo());
			System.out.println(bean.getName());
			System.out.println(bean.getPhy());
			System.out.println(bean.getChm());
			System.out.println(bean.getMaths());
		} else {
			System.out.println("No Record found");
		}
	}

}
