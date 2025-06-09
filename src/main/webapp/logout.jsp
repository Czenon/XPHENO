	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.* " %>
<%@ page import="MyPackage.User" %>
<% 
	if (session != null) {
		session.invalidate();
	}
    response.sendRedirect("index.jsp"); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logout page</title>
</head>
<body>

</body>
</html>