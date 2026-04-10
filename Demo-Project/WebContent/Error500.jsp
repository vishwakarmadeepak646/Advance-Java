<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>

<title>500 - Internal Server Error</title>
<style>
body {
	margin: 0;
	padding: 0;
	font-family: Arial, sans-serif;
	background: linear-gradient(135deg, #ff6a00, #ee0979);
	color: #fff;
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
}

.container {
	text-align: center;
	max-width: 600px;
}

h1 {
	font-size: 100px;
	margin: 0;
}

h2 {
	margin: 10px 0;
}

p {
	font-size: 18px;
}

.error-box {
	margin-top: 20px;
	padding: 15px;
	background: rgba(0, 0, 0, 0.3);
	border-radius: 10px;
	font-size: 14px;
	text-align: left;
}

a {
	display: inline-block;
	margin-top: 20px;
	padding: 12px 25px;
	background: #fff;
	color: #ee0979;
	text-decoration: none;
	border-radius: 25px;
	font-weight: bold;
}

a:hover {
	background: #ddd;
}
</style>
</head>
<body>
	<div class="container">
		<h1>500</h1>
		<h2>Internal Server Error</h2>
		<p>Something went wrong on the server. Please try again later.</p>
		<!-- Exception details (useful for debugging) -->
		<div class="error-box">
			<strong>Error Details:</strong><br>
			<%=exception%>
		</div>
		<a href="index.jsp">Go Back Home</a>
	</div>
</body>
</html>