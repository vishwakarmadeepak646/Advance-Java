<%@page import="java.util.Iterator"%>
<%@page import="com.dev.bean.ComplaintBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Complaint List</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<%
	List<ComplaintBean> list = (List) request.getAttribute("list");

	List<ComplaintBean> Nextlist = (List) request.getAttribute("Nextlist");

	int pageNo = (int) request.getAttribute("pageNo");
	Iterator<ComplaintBean> it = list.iterator();

	String successMsg = (String) request.getAttribute("successMsg");
	String errorMsg = (String) request.getAttribute("errorMsg");
	%>
	<div align="center">


		<h1>Complaint List</h1>
		<form action="ComplaintListCtl" method="post">
		
		<input type="hidden" name="pageNo" value="<%=pageNo%>">

			<h2 style="color: green"><%=successMsg != null ? successMsg : ""%></h2>
			<h2 style="color: red"><%=errorMsg != null ? errorMsg : ""%></h2>

			<div align="center">
				<table width="100%" border="1px">
					<tr style="background-color: skyblue">
						<th>Select</th>
						<th>Id</th>
						<th>UserName</th>
						<th>Type</th>
						<th>Description</th>
						<th>Status</th>
						<th>Email</th>
						<th>Edit</th>
					</tr>
					<%
					while (it.hasNext()) {
						ComplaintBean bean = it.next();
					%>

					<tr>
						<th><input type="checkbox" name="ids"
							value="<%=bean.getId()%>"></th>
						<th><%=bean.getId()%></th>
						<th><%=bean.getUser_name()%></th>
						<th><%=bean.getComplaint_type()%></th>
						<th><%=bean.getDescription()%></th>
						<th><%=bean.getStatus()%></th>
						<th><%=bean.getEmail()%></th>
						<th><a href="ComplaintCtl?id=<%=bean.getId()%>">Edit</a></th>

					</tr>
					<%
					}
					%>
				</table>
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
</body>
</html>