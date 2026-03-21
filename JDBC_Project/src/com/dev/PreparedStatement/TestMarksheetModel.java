package com.dev.PreparedStatement;

public class TestMarksheetModel {

	public static void main(String[] args) throws Exception {
		
		//getAdd();
		//getUpdate();
		getDelete();

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

}
