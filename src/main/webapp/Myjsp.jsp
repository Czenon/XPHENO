<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My first dynamic web project</title>
</head>
<body>
	<form action="/Valmiera/RegistrationServlet" method="post">
	 <label> Insert username:</label>
	 <input type="text" name="username"><br>
	 <label> Insert password:</label>
	 <input type="text" name="password"><br>
	 <input type="submit" value="submit">
	</form>
</body>
</html>