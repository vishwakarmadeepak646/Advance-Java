package com.dev.Session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SecondSession")
public class SecondSession extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// if req.getSession(true) then it will create new session, if existing session is there then it will run the same.
		HttpSession session = req.getSession(true);

		if (session != null) {
			
			System.out.println("Second Session : " + session.getId());
		}

	}

}
