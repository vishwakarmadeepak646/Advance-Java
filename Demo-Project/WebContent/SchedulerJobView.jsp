<%@page import="com.dev.bean.SchedulerJobBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
	String successMsg = (String) request.getAttribute("successMsg");
	String errorMsg = (String) request.getAttribute("errorMsg");
	SchedulerJobBean bean = (SchedulerJobBean) request.getAttribute("bean");
	%>
	<div align="center">
		<form action="SchedulerJobCtl" method="post">


			<h1><%=bean != null ? "Update Job Registration" : "Scheduler Job Registration"%></h1>

			<h3 style="color: green"><%=successMsg != null ? successMsg : ""%></h3>
			<h3 style="color: red"><%=errorMsg != null ? errorMsg : ""%></h3>

			<input type="hidden" name="id"
				value="<%=bean != null ? bean.getId() : ""%>">

			<table>
				<tr>
					<th>Code:</th>
					<td><input type="text" name="code"
						value="<%=bean != null ? bean.getCode() : ""%>"
						placeholder="Enter Code"></td>
				</tr>

				<tr>
					<th>Name:</th>
					<td><input type="text" name="name"
						value="<%=bean != null ? bean.getName() : ""%>"
						placeholder="Enter Name"></td>
				</tr>



				<tr>
					<th>Cron Expression:</th>
					<td><input type="text" name="cronExpression"
						value="<%=bean != null ? bean.getCronExpression() : ""%>"
						placeholder="Enter Cron Expression"></td>
				</tr>

				<tr>
					<th>Status :</th>
					<td><input type="text" name="status"
						value="<%=bean != null ? bean.getStatus() : ""%>"
						placeholder="Enter Status"></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=bean != null ? "Update" : "Save"%>"></td>
				</tr>


			</table>

		</form>
	</div>

</body>
</html>