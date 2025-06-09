<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>XPHENO - The Definitive Gaming Site</title>
        <link rel="icon" type="image/png" href="img/favicon.png">
        <link rel="stylesheet" href="css/gameview.css">
    </head>
    <body>
        <h1 style="text-align: center; color: brown;"><a href="index.jsp">XPHENO - The Definitive Gaming Site</a></h1>
        <div id="container" class="container">
            <img src="img/banner.png" style="height: 150px; width: 100%; object-fit:cover; border: 2px solid rgb(30, 87, 68)">

            <div id="menu">
                <a href="index.jsp" class="gamemenu">Home</a>
                <a href="/XPHENO/GameUploadServlet?action=getgames" class="gamemenu">Games</a>
                <a href="upload.jsp" class="gamemenu">Upload</a>
                <a href="login.jsp" class="gamemenu">                
                <%
                if ((String)session.getAttribute("username") == null) 
                	out.print("Log In");
                
                %></a>
                <a href="logout.jsp" class="gamemenu"><% 
                
                if ((String)session.getAttribute("username") != null) 
                	out.print("Log Out");
                
                %>
                </a>
                <div>Logged in as <b><%
			String session_u_name = (String)session.getAttribute("username");
                if (session_u_name == null) {session_u_name = "Guest";}
                out.print(session_u_name);
%>
</b>.</div>                
            </div>
            <div class="container2">
                <div id="left">
                    <img src="img/img.jpg" style="max-width: 30vh; max-height: 70vh;">
                </div>
                <div id="middle">
                    <h2 id="gametitle"><%= request.getParameter("title") %></h2>
                    <img id="gamecover" class="screenshots" src="<%= request.getParameter("coverpath")%>">
                    <hr>
                    <table id="gametable">
                        <tr>          
                            <th></th>
                            <th></th>
                        </tr>
                        <tr>
                            <td>Author:</td>
                            <td id="author" class="th2"><%= request.getParameter("author") %></td>
                        </tr>
                        <tr>
                            <td>Genre:</td>
                            <td id="genre" class="th2"><%= request.getParameter("genre") %></td>
                        </tr>
                        <tr>
                            <td>Description:</td>
                            <td id="description" class="th2"><%= request.getParameter("description") %></td>
                        </tr>
                    </table>
                </div>

            </div>
        </div>

    </body>
</html>