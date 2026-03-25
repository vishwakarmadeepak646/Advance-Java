package com.Project.Module;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

public class TestComplaintTicket {

	public static void main(String[] args) throws Exception {

		// getAdd();
		// getUpdate();
		// getDelete();

		findbyPK();
	}

	public static void getAdd() throws Exception {
		ComplaintTicketBean bean = new ComplaintTicketBean();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

		bean.setId(13);
		bean.setIssueType("Network");
		bean.setCreatedDate(s.parse("2026-02-01"));
		bean.setStatus("Open");

		ComplaintTicketModel c = new ComplaintTicketModel();
		int i = c.add(bean);
		System.out.println(i + " th row inserted in table");

		System.out.println("----------------------------");
	}

	public static void getUpdate() throws Exception {
		ComplaintTicketBean bean = new ComplaintTicketBean();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

		bean.setId(1);
		bean.setIssueType("Network");
		bean.setCreatedDate(s.parse("2026-02-01"));
		bean.setStatus("Open");

		ComplaintTicketModel c = new ComplaintTicketModel();
		c.update(bean);

		System.out.println("-------------------------");
	}

	public static void getDelete() throws Exception {
		ComplaintTicketBean bean = new ComplaintTicketBean();

		bean.setId(1);

		ComplaintTicketModel c = new ComplaintTicketModel();
		c.delete(bean);
	}

	public static void findbyPK() throws Exception {
		ComplaintTicketBean bean = new ComplaintTicketBean();
		ComplaintTicketModel c = new ComplaintTicketModel();
		bean = c.FindByPK(3);

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getIssueType());
			System.out.println(bean.getCreatedDate());
			System.out.println(bean.getStatus());

		} else {
			System.out.println("Result Not Found");
		}
		System.out.println("-----------------------");
	}
}
