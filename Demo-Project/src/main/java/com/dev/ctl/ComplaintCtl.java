package com.dev.ctl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.bean.ComplaintBean;
import com.dev.model.ComplaintModel;

@WebServlet("/ComplaintCtl")
public class ComplaintCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
// Running the Edit link
		ComplaintBean bean = new ComplaintBean();
		ComplaintModel model = new ComplaintModel();
		
		String id = request.getParameter("id");
		
		if (id != null) {
			try {
				bean = model.FindByPk(Integer.parseInt(id));
				request.setAttribute("bean", bean);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("ComplaintView.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");

		ComplaintBean bean = new ComplaintBean();
		ComplaintModel model = new ComplaintModel();

		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String description = request.getParameter("description");
		String status = request.getParameter("status");
		String email = request.getParameter("email");

		try {

			bean.setUser_name(name);
			bean.setComplaint_type(type);
			bean.setDescription(description);
			bean.setStatus(status);
			bean.setEmail(email);

			model.add(bean);
			request.setAttribute("successMsg", "Record updated successfully");

		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("ComplaintView.jsp");
		rd.forward(request, response);
	}
}
