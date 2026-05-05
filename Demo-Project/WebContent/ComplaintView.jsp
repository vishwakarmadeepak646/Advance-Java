<%@page import="com.dev.bean.ComplaintBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Complaint View</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	
	<%
			String successMsg = (String) request.getAttribute("successMsg");
			String errorMsg = (String) request.getAttribute("errorMsg");

			ComplaintBean bean = (ComplaintBean) request.getAttribute("bean");
			%>

	<div align="center">
		<form action="ComplaintCtl" method="post">

			<h1></h1>
			<h1><%=bean != null ? "Update Complaint Registration" : "Complaint Registration"%></h1>

			<h3 style="color: green"><%=successMsg != null ? successMsg : ""%></h3>
			<h3 style="color: red"><%=errorMsg != null ? errorMsg : ""%></h3>

			<table>
			
			<input type="hidden" name="id"
				value="<%=bean != null ? bean.getId() : ""%>">
				
				<tr>
					<th>User Name:</th>
					<td><input type="text" name="name" value="<%=bean != null ? bean.getUser_name() : ""%>"
						placeholder="Enter User Name"></td>
				</tr>



				<tr>
					<th>Type:</th>
					<td><input type="text" name="type" value="<%=bean != null ? bean.getComplaint_type() : ""%>"
						placeholder="Enter type"></td>
				</tr>

				<tr>
					<th>Description :</th>
					<td><input type="text" name="description" value="<%=bean != null ? bean.getDescription() : ""%>"
						placeholder="Enter Description"></td>
				</tr>
				<tr>
					<th>Status :</th>
					<td><input type="text" name="status" value="<%=bean != null ? bean.getStatus() : ""%>"
						placeholder="Enter Status"></td>
				</tr>
				<tr>
					<th>Email :</th>
					<td><input type="text" name="email" value="<%=bean != null ? bean.getEmail() : ""%>"
						placeholder="Enter Email"></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation" value="<%=bean != null ? "Update" : "Save"%>"></td>
				</tr>


			</table>

		</form>
	</div>



</body>
</html>