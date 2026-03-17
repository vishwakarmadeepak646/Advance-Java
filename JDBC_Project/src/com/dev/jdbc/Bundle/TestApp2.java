package com.dev.jdbc.Bundle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class TestApp2 {

	public static void main(String[] args) {

		
		ResourceBundle rb = ResourceBundle.getBundle("com.dev.jdbc.Bundle.app");

		String url = rb.getString("url");
		String driver = rb.getString("driver");
		String pwd = rb.getString("pwd");
		String username = rb.getString("username");

		System.out.println(url + "\n" + driver + "\n" + pwd + "\n" + username);

		System.out.println("------------------------------------------------------");
		
		System.out.println("To listin in hindi, Please press 1");
		System.out.println("To listing in Spanish, Please press 2");
		try {
		Scanner sc = new Scanner(System.in);
		
		int i = sc.nextInt();
		
		if(i == 1) {
			rb = ResourceBundle.getBundle("com.dev.jdbc.Bundle.app", new Locale("hi"));}
			
		if(i == 2) {
			rb = ResourceBundle.getBundle("com.dev.jdbc.Bundle.app", new Locale("sp"));
		}
		String s = rb.getString("greeting");
		System.out.println(s);
		}catch(Exception e){
			System.out.println("Invalid Selection getting Exception : \n" + e.getMessage());
			
		}
		
	}
}
