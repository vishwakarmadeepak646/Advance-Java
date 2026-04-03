<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User List</title>
</head>
<body>

	<%
	List<UserBean> list = (List) request.getAttribute("list");
	Iterator<UserBean> it = list.iterator();
	%>


	<%@ include file="Header.jsp"%>
	<div align="center">
		<h1>User List</h1>

		<form action="UserListCtl" method="post">

			<table width="90%" border="1px">

				<tr>
					<th>Id</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Login</th>
					<th>Password</th>
					<th>DOB</th>
				</tr>
				<%
				while (it.hasNext()) {
					UserBean bean = it.next();
				%>
				<tr>
					<td><%=bean.getID()%></td>
					<td><%=bean.getFirst_name()%></td>
					<td><%=bean.getLast_name()%></td>
					<td><%=bean.getLogin()%></td>
					<td><%=bean.getPassword()%></td>
					<td><%=bean.getDob()%></td>
				</tr>
				<%
				}
				%>


			</table>


		</form>



	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>