package com.dev.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rs = req.getRequestDispatcher("HelloView.jsp");

		System.out.println("Inside doGet method");
		rs.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Inside doPost Method");

		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String Email = req.getParameter("Email");
		String password = req.getParameter("Password");
		String date = req.getParameter("DOB");
		
		System.out.println(firstName + " " + lastName + " " + Email + " " +password + " " + date);
		
		RequestDispatcher rs = req.getRequestDispatcher("HelloView.jsp");
		
		rs.forward(req, resp);
				
	}

}
