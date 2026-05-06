package com.dev.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.bean.ComplaintBean;
import com.dev.model.ComplaintModel;

@WebServlet("/ComplaintListCtl")
public class ComplaintListCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ComplaintBean bean = new ComplaintBean();
		ComplaintModel model = new ComplaintModel();

		int pageNo = 1;
		int pageSize = 5;

		try {
			List<ComplaintBean> list = model.search(bean, pageNo, pageSize);
			List<ComplaintBean> Nextlist = model.search(bean, pageNo + 1, pageSize);

			request.setAttribute("pageNo", pageNo);
			request.setAttribute("list", list);
			request.setAttribute("Nextlist", Nextlist);

		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("ComplaintListView.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pageNo = 1;
		int pageSize = 5;
		String op = request.getParameter("operation");
		System.out.println(op);

		ComplaintBean bean = new ComplaintBean();
		ComplaintModel model = new ComplaintModel();

		

		 String ids[] = request.getParameterValues("ids");

		if (op.equals("next")) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			pageNo++;
		}
		if (op.equals("previous")) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			pageNo--;
		}
		
		
		if (op.equals("delete")) {
			if (ids != null && ids.length > 0) {
				for (String id : ids) {
					ComplaintBean jobBean = new ComplaintBean();
					jobBean.setId(Integer.parseInt(id));
					try {
						model.delete(jobBean);
						request.setAttribute("successMsg", "Record Deleted Successfully");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				request.setAttribute("errorMsg", "Select atleast one record");
			}}
		
		if(op.equalsIgnoreCase("search")) {
			bean.setUser_name(request.getParameter("name"));
		}
		
		
		try {
			List<ComplaintBean> list = model.search(bean, pageNo, pageSize);
			List<ComplaintBean> Nextlist = model.search(bean, pageNo + 1, pageSize);

			request.setAttribute("pageNo", pageNo);
			request.setAttribute("list", list);
			request.setAttribute("Nextlist", Nextlist);

		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("ComplaintListView.jsp");
		rd.forward(request, response);
	}

}
