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

	List<UserBean> Nextlist = (List) request.getAttribute("Nextlist");

	int pageNo = (int) request.getAttribute("pageNo");
	Iterator<UserBean> it = list.iterator();

	String successMsg = (String) request.getAttribute("successMsg");
	String errorMsg = (String) request.getAttribute("errorMsg");
	%>


	<%@ include file="Header.jsp"%>
	<div align="center">
		<h1>User List</h1>

		<form action="UserListCtl" method="post">

			<h2 style="color: green"><%=successMsg != null ? successMsg : ""%></h2>
			<h2 style="color: red"><%=errorMsg != null ? errorMsg : ""%></h2>



			<%
			if (list.size() == 0) {
			%>
			<!--  	<h1 style="color: red">Record Not found</h1>
			<input type="submit" name="operation" value="back">-->
			<%
			} else {
			%>
			<input type="hidden" name="pageNo" value="<%=pageNo%>">

			<div align="center">
				<table>
					<tr>
						<th>First Name</th>
						<td><input type="text" name="firstName"
							value="<%=request.getParameter("firstName") != null ? request.getParameter("firstName") : ""%>"
							placeholder="search by firstName"></td>

						<th>Last Name</th>
						<td><input type="text" name="lastName"
							value="<%=request.getParameter("lastName") != null ? request.getParameter("lastName") : ""%>"
							placeholder="search by lastName"></td>

						<td><input type="submit" name="operation" value="search"></td>

					</tr>
				</table>

			</div>

			<div align="center">
				<table width="100%" border="1px">
					<tr style="background-color: skyblue">
						<th>Select</th>
						<th>Id</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Login</th>
						<th>Password</th>
						<th>DOB</th>
						<th>Edit</th>
					</tr>
					<%
					while (it.hasNext()) {
						UserBean bean = it.next();
					%>
					<tr align="center" style="background-color: #D3D3D3;">

						<td><input type="checkbox" name="ids" value=<%=bean.getID()%>></td>
						<td><%=bean.getID()%></td>
						<td><%=bean.getFirst_name()%></td>
						<td><%=bean.getLast_name()%></td>
						<td><%=bean.getLogin()%></td>
						<td><%=bean.getPassword()%></td>
						<td><%=bean.getDob()%></td>
						<td><a href="UserCtl?id=<%=bean.getID()%>">Edit</a></td>
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

						<td><input type="submit" name="operation" value="delete"></td>

						<td align="right"><input type="submit" name="operation"
							<%=Nextlist.size() == 0 ? "disabled" : ""%> value="next"></td>
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