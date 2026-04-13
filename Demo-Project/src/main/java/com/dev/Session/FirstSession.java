package com.dev.Session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/FirstSession")
public class FirstSession extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// if req.getSession(false) then don't create new session, if session is already
				// exist it will continue same session
		HttpSession session = req.getSession(false);

		if (session != null) {
			System.out.println("First Session : " + session.getId());
		} else {
			System.out.println("Session is null");
		}

	}

}
