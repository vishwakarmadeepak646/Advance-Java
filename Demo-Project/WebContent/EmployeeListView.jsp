<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dev.bean.EmployeeBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee List</title>
</head>
<body>
	<%@ include file="Header.jsp"%>

	<%
	int pageNo = (int) request.getAttribute("pageNo");

	List<EmployeeBean> list = (List) request.getAttribute("list");
	List<EmployeeBean> Nextlist = (List) request.getAttribute("Nextlist");

	String successMsg = (String) request.getAttribute("successMsg");
	String errorMsg = (String) request.getAttribute("errorMsg");

	Iterator it = list.iterator();
	%>

	<div align="center">

		<form action="EmployeeListCtl" method="post">

			<h1>Employee List</h1>

			<h3 style="color: green">
				<%=successMsg != null ? successMsg : ""%></h3>
			<h3 style="color: red">
				<%=errorMsg != null ? errorMsg : ""%></h3>

			<table>
				<tr>
					<td><input type="text" name="name"
						value="<%=request.getParameter("name") != null ? request.getParameter("name") : ""%>"
						placeholder="Search using Name"></td>
					<td><input type="submit" name="operation" value="search"></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>">
			<!-- To provide current pageNo in post Method -->


			<table width="100%" border="1px">
				<tr style="background-color: skyblue">
					<th>Select</th>
					<th>Id</th>
					<th>Name</th>
					<th>Department</th>
					<th>Salary</th>
					<th>Email Id</th>
					<th>Edit</th>

				</tr>

				<%
				while (it.hasNext()) {

					EmployeeBean bean = (EmployeeBean) it.next();
				%>
				<tr>
					<th><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></th>
					<th><%=bean.getId()%></th>
					<th><%=bean.getName()%></th>
					<th><%=bean.getDepartment()%></th>
					<th><%=bean.getSalary()%></th>
					<th><%=bean.getEmail()%></th>
					<th><a href="EmployeeCtl?id=<%=bean.getId()%>">Edit</a></th>
				</tr>
				<%
				}
				%>
			</table>
			<div>
				<table width="100%">
					<tr>
						<td align="left"><input type="submit" name="operation"
							<%=pageNo == 1 ? "disabled" : ""%> value="previous"></td>

						<td align="center"><input type="submit" name="operation"
							value="delete"></td>

						<td align="right"><input type="submit" name="operation"
							<%=Nextlist.size() == 0 ? "disabled" : ""%> value="next"></td>
					</tr>

				</table>
			</div>

		</form>

	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>