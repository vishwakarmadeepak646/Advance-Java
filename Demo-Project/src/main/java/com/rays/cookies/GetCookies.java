package com.rays.cookies;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetCookies")
public class GetCookies extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Cookie[] c = req.getCookies();
		

		for (int i = 0; i < c.length; i++) {

			Cookie cu = c[i];
			
			String name = cu.getName();
			String value = cu.getValue();
			
			System.out.println(name);
			System.out.println(value);

		}
		
		

	}

}
