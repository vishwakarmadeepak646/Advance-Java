package com.dev.ctl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.bean.EmployeeBean;
import com.dev.model.EmployeeModel;

@WebServlet("/EmployeeCtl")
public class EmployeeCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EmployeeBean bean = new EmployeeBean();
		EmployeeModel model = new EmployeeModel();

		String id = request.getParameter("id");

		if (id != null) {

			try {
				bean = model.findByPk(Integer.parseInt(id));
				request.setAttribute("bean", bean);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("EmployeeView.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");
		EmployeeBean bean = new EmployeeBean();
		EmployeeModel model = new EmployeeModel();

		String name = request.getParameter("name");
		String department = request.getParameter("department");
		String salary = request.getParameter("salary");
		String email = request.getParameter("email");

		try {

			bean.setName(name);
			bean.setDepartment(department);
			bean.setSalary(Long.parseLong(salary));
			bean.setEmail(email);

			System.out.println("Current Operation" + op);

			if (op.equals("update")) {

				bean.setId(Integer.parseInt(request.getParameter("id")));
				model.update(bean);
				request.setAttribute("successMsg", "Job Updated Successfully");
				request.setAttribute("bean", bean);
			}else {

			model.add(bean);
			request.setAttribute("successMsg", "Data added successfully");}

		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("EmployeeView.jsp");
		rd.forward(request, response);
	}

}
