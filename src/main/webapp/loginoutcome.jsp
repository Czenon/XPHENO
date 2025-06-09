<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.* " %>
<%@ page import="MyPackage.User" %>
<% 
String username = (String) request.getAttribute("username"); 
    session.setAttribute("username", username); 
    response.sendRedirect("index.jsp"); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login outcome</title>
</head>
<body>

</body>
</html>