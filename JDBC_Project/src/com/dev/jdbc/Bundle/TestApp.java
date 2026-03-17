package com.dev.jdbc.Bundle;

import java.util.Locale;
import java.util.ResourceBundle;

public class TestApp {

	public static void main(String[] args) {

		ResourceBundle rb = ResourceBundle.getBundle("com.dev.jdbc.Bundle.app");

		String url = rb.getString("url");
		String driver = rb.getString("driver");
		String pwd = rb.getString("pwd");
		String username = rb.getString("username");

		System.out.println(url + "\n" + driver + "\n" + pwd + "\n" + username);

		System.out.println("------------------------------------------------------");

		rb = ResourceBundle.getBundle("com.dev.jdbc.Bundle.app", new Locale("hi"));

		String s = rb.getString("greeting");
		System.out.println(s);

	}

}
