<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Add View</title>
</head>
<body>
	<%@ include file="Header.jsp"%>

	<%
	String successMsg = (String) request.getAttribute("successMsg");
	String errorMsg = (String) request.getAttribute("errorMsg");
	%>

	<form action="EmployeeCtl" method="post">
		<div align="center">
			<h1>Add Employee</h1>
			<h3 style="color: green"><%=successMsg != null ? successMsg : ""%></h3>
			<h3 style="color: red">	<%=errorMsg != null ? errorMsg : ""%></h3>
			
			


			<table>

				<tr>
					<th>Name :</th>
					<td><input type="text" name="name" value=""
						placeholder="Enter Your Name"></td>
				</tr>

				<tr>
					<th>Department :</th>
					<td><input type="text" name="department" value=""
						placeholder="Enter Your Department"></td>
				</tr>
				<tr>
					<th>Salary :</th>
					<td><input type="text" name="salary" value=""
						placeholder="Enter Your Salary"></td>
				</tr>
				<tr>
					<th>Email Id :</th>
					<td><input type="email" name="email" value=""
						placeholder="Enter Your Email id"></td>
				</tr>
				<tr>
					<th></th>
					<td><input type="submit" name="operation" value="SAVE""></td>
				</tr>


			</table>




		</div>




	</form>



	<%@ include file="Footer.jsp"%>
</body>
</html>