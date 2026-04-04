<%@page import="com.dev.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="left">



	<%
	UserBean userBean = (UserBean) session.getAttribute("user");
	%>

	<%
	if (userBean != null) {
	%>
	<h1><%="Hii, " + userBean.getFirst_name()%></h1>

	<a href="UserCtl"> Add User</a> |
	<a href="UserListCtl">User List</a> |
	<a href="LoginCtl?operation=logout">Logout</a>
	<%
	} else {
	%>

	<h3>Hii, Guest</h3>

	<a href="UserRegistrationCtl"> User Registration |</a>
	<a href="LoginCtl"> User Login | </a>
	<%
	}
	%>
	<a href="WelcomeCtl "> Welcome </a>
	<hr>
</body>
</html>