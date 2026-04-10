<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>404 - Page Not Found</title>
<style>

body {
	margin: 0;
	padding: 0;
	font-family: Arial, sans-serif;
	background: linear-gradient(135deg, #667eea, #764ba2);
	color: #fff;
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
}

.container {
	text-align: center;
}

h1 {
	font-size: 120px;
	margin: 0;
}

h2 {
	font-size: 28px;
	margin: 10px 0;
}

p {
	margin: 15px 0;
	font-size: 18px;
}

a {
	display: inline-block;
	margin-top: 20px;
	padding: 12px 25px;
	background: #fff;
	color: #764ba2;
	text-decoration: none;
	border-radius: 25px;
	font-weight: bold;
	transition: 0.3s;
}

a:hover {
	background: #ddd;
}
</style>
</head>
<body>
	<div class="container">
		<h1>404</h1>
		<h2>Oops! Page Not Found</h2>
		<p>The page you are looking for doesn't exist or has been moved.</p>
		<a href="WelcomeCtl">Go Back Home</a>
	</div>
</body>

</html>