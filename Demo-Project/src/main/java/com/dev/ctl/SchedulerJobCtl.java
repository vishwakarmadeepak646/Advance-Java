package com.dev.ctl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.bean.SchedulerJobBean;
import com.dev.model.SchedulerJobModel;

@WebServlet("/SchedulerJobCtl")
public class SchedulerJobCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// When you accessing the Edit link

		SchedulerJobBean bean = new SchedulerJobBean();
		SchedulerJobModel model = new SchedulerJobModel();

		String id = request.getParameter("id");

		if (id != null) {
			try {
				bean = model.findByPk(Integer.parseInt(id));
				request.setAttribute("bean", bean);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("SchedulerJobView.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");

		SchedulerJobBean bean = new SchedulerJobBean();
		SchedulerJobModel m = new SchedulerJobModel();

		String name = request.getParameter("name");
		String code = request.getParameter("code");
		String cronExpression = request.getParameter("cronExpression");
		String status = request.getParameter("status");

		try {
			bean.setCode(code);
			bean.setCronExpression(cronExpression);
			bean.setName(name);
			bean.setStatus(status);

			System.out.println("OP => " + op);

			if (op.equals("Update")) {
				bean.setId(Integer.parseInt(request.getParameter("id")));
				m.update(bean);
				request.setAttribute("successMsg", "Job Updated Successfully");
				request.setAttribute("bean", bean);
			} else {
				m.add(bean);
				request.setAttribute("successMsg", "Data Added successfully");
			}

		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("SchedulerJobView.jsp");
		rd.forward(request, response);
	}

}
