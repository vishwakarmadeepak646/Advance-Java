package com.dev.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.bean.UserBean;
import com.dev.model.UserModel;

@WebServlet("/UserListCtl")
public class UserListCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserBean bean = new UserBean();
		UserModel model = new UserModel();

		int pageNo = 1;
		int pageSize = 10;

		try {
			List<UserBean> list = model.search(bean, pageNo, pageSize);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("list", list);

		} catch (Exception e) {

			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("UserListView.jsp");

		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");
		System.out.println("Op ==> " + op);
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		int pageNo = 1;
		int pageSize = 10;

		String[] ids = request.getParameterValues("ids");

		if (op.equals("delete")) {

			if (ids != null && ids.length > 0) {
				for (String id : ids) {
					UserBean deletebean = new UserBean();
					deletebean.setId(Integer.parseInt(id));
					try {
						model.delete(deletebean);
						request.setAttribute("successMsg", "records deleted successfully");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}else {
				request.setAttribute("errorMsg", "Select atleast one record");
			}

		}

		if (op.equals("next")) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			pageNo++;
		}

		if (op.equals("previous")) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			pageNo--;
		}

		try {
			List<UserBean> list = model.search(bean, pageNo, pageSize);
			request.setAttribute("list", list);
			request.setAttribute("pageNo", pageNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("UserListView.jsp");

		rd.forward(request, response);
	}
}
