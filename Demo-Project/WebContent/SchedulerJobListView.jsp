<%@page import="com.dev.bean.SchedulerJobBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<%
	List<SchedulerJobBean> list = (List) request.getAttribute("list");

	List<SchedulerJobBean> Nextlist = (List) request.getAttribute("Nextlist");

	int pageNo = (int) request.getAttribute("pageNo");
	Iterator<SchedulerJobBean> it = list.iterator();

	String successMsg = (String) request.getAttribute("successMsg");
	String errorMsg = (String) request.getAttribute("errorMsg");
	%>

	<div align="center">

		<h1>Job List</h1>
		<form action="SchedulerJobListCtl" method="post">
			<input type="hidden" name="pageNo" value="<%=pageNo%>">


			<h2 style="color: green"><%=successMsg != null ? successMsg : ""%></h2>
			<h2 style="color: red"><%=errorMsg != null ? errorMsg : ""%></h2>

			<div align="center">
				<table>
					<tr>
						<th>Job Name :</th>
						
						<!-- Search Filter -->
						<td><input type="text" name="name"
							placeholder="Search By Job Name"
							value="<%=request.getParameter("name") != null ? request.getParameter("name") : ""%>">
						</td>
						<td><input type="submit" name="operation" value="search"></td>
					</tr>
				</table>
				<table width="100%" border="1px">
					<tr style="background-color: skyblue">
						<th>Select</th>
						<th>Id</th>
						<th>Job Code</th>
						<th>Job Name</th>
						<th>Cron Expression</th>
						<th>Status</th>
						<th>Edit</th>
					</tr>

					<%
					while (it.hasNext()) {
						SchedulerJobBean bean = it.next();
					%>
					<tr>
						<th><input type="checkbox" name="ids"
							value="<%=bean.getId()%>"></th>
						<th><%=bean.getId()%></th>
						<th><%=bean.getCode()%></th>
						<th><%=bean.getName()%></th>
						<th><%=bean.getCronExpression()%></th>
						<th><%=bean.getStatus()%></th>
						<th><a href="SchedulerJobCtl?id=<%=bean.getId()%>">Edit</a></th>
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
</body>
</html>