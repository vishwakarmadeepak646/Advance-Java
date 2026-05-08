package com.dev.ctl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.bean.EmployeeBean;
import com.dev.model.EmployeeModel;

@WebServlet("/EmployeeListCtl")
public class EmployeeListCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EmployeeBean bean = new EmployeeBean();
		EmployeeModel model = new EmployeeModel();

		int pageNo = 1;
		int pageSize = 5;

		try {
			List<EmployeeBean> list = model.search(bean, pageNo, pageSize);
			List<EmployeeBean> Nextlist = model.search(bean, pageNo + 1, pageSize);

			request.setAttribute("pageNo", pageNo);
			request.setAttribute("list", list);
			request.setAttribute("Nextlist", Nextlist);

		} catch (Exception e) {

			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("EmployeeListView.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("operation");

		String[] ids = request.getParameterValues("ids");

		int pageNo = 1;
		int pageSize = 5;

		EmployeeBean bean = new EmployeeBean();
		EmployeeModel model = new EmployeeModel();

		if (op.equals("previous")) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			pageNo--;
		}
		if (op.equals("next")) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			pageNo++;
		}
		
		if(op.equals("delete")) {
			
			if( ids != null && ids.length > 0) {
				for(String i : ids) {
					
					EmployeeBean delBean = new EmployeeBean();
					delBean.setId(Long.parseLong(i));
					try {
						model.delete(delBean);
						request.setAttribute("successMsg", "Records Deleted Successfully");
					} catch (Exception e) {
					
						e.printStackTrace();
					}
				}
			}else {
				request.setAttribute("errorMsg", "Select atleast one record");
			}
			}
		
		if(op.equals("search")) {
			
			bean.setName(request.getParameter("name"));
		}
		

		try {

			List<EmployeeBean> list = model.search(bean, pageNo, pageSize);
			List<EmployeeBean> Nextlist = model.search(bean, pageNo + 1, pageSize);

			request.setAttribute("pageNo", pageNo);
			request.setAttribute("list", list);
			request.setAttribute("Nextlist", Nextlist);

		} catch (Exception e) {

			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("EmployeeListView.jsp");
		rd.forward(request, response);
	}

}
