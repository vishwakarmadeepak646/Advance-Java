package com.dev.PreparedStatement;

public class TestMarksheetModel {

	public static void main(String[] args) throws Exception {
		
		getAdd();

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

}
