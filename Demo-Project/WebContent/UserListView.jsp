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
	int pageNo = (int) request.getAttribute("pageNo");
	Iterator<UserBean> it = list.iterator();
	%>


	<%@ include file="Header.jsp"%>
	<div align="center">
		<h1>User List</h1>

		<form action="UserListCtl" method="post">

			<%
			if (list.size() == 0) {
			%>
			<h1 style="color: red">Record Not found</h1>
			<input type="submit" name="operation" value="back">
			<%
			} else {
			%>
			<input type="text" name="pageNo" value="<%=pageNo%>">

			<div align="center">
				<table width="100%" border="1px">
					<tr style="background-color: skyblue">

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
					<tr align="center" style="background-color: #D3D3D3;">
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
			</div>
			<div>
				<table width="100%">
					<tr>
						<td><input type="submit" name="operation"
							<%=pageNo == 1 ? "disabled" : ""%> value="previous"></td>
						<td align="right"><input type="submit" name="operation"
							<%=list.size() < 10 ? "disabled" : ""%> value="next"></td>
					</tr>
				</table>
			</div>
		</form>
		<%
		}
		%>

	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>