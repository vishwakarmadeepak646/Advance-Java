<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login View Page</title>
</head>
<body>

<%
String successMsg = (String) request.getAttribute("successMsg");
String errorMsg = (String)request.getAttribute("errorMsg");
%>

	<%@ include file="Header.jsp"%>

	<div align="center">
		<form action="LoginCtl" method="post">

			<table>

				<h3>User Login</h3>
				<h4 style="color: green"><%= successMsg != null ? successMsg : "" %></h3>
				<h4 style="color:red"><%= errorMsg != null ? errorMsg : "" %>
				
				<tr>
					<th>Login :</th>
					<td><input type="email" name="login" value=""
						placeholder="Enter your email id"></td>
				</tr>


				<tr>
					<th>Password :</th>
					<td><input type="password" name="password" value=""
						placeholder="Enter your password"></td>
				</tr>

				<tr>
					<td><input type="submit" name="operation" value="Sign In">
					</td>
				</tr>



			</table>
		</form>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>