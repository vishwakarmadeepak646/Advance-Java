<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HelloServlet Page</title>
</head>
<body>
	<div align="center">
		<h1>Hello Severlet</h1>

		<form action="HelloServlet" method="post">

			<table>

				<tr>

					<th>First Name :</th>
					<td><input type="text" name="firstName" value=""
						placeholder="Enter first Name"></td>

				</tr>

				<tr>
					<th>Last Name :</th>
					<td><input type="text" name="lastName" value=""
						placeholder="Enter Last Name"></td>

				</tr>
				<tr>
					<th>Login ID :</th>
					<td><input type="email" name="Email" value=""
						placeholder="Enter Email id">
				<tr>
					<th>Password :</th>
					<td><input type="password" name="Password" value=""
						placeholder="Enter your password"></td>

				</tr>


				<tr>
					<th>DOB :</th>
					<th><input type="date" name="DOB" value=""></th>
				</tr>


				<tr>

					<td><input type="submit" name="operation" value="save"></td>

				</tr>
			</table>

		</form>

	</div>
</body>
</html>