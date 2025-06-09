<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    	<meta charset="UTF-8">
        <title>XPHENO - The Definitive Gaming Site</title>
        <link rel="icon" type="image/png" href="img/favicon.png">
        <link rel="stylesheet" href="css/index.css">
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
</b></div> 
            </div>
            <div class="container2">
                <div id="left">
                    <img src="img/img.jpg" style="max-width: 30vh; max-height: 70vh;">
                </div>
                <div id="middle">
                    <p id="sitedescription">Welcome to XPHENO - the definitive game sharing website! Check out a myriad of games on display or make your own account and share yours with the rest of the world!</p>
                    
                </div>
                <div id="right">
                    <p style="text-align: center; margin-left: 2%; margin-right: 2%;">Latest upload:<br> Armored Core 6 </p>
                    <img src="img/ac6.png" class="screenshots">
                    
                    <p style="text-align: center; margin-left: 2%; margin-right: 2%;">Hottest game this month:<br> Painkiller</p>

                    <img src="img/painkiller.png" class="screenshots">
                    <p style="text-align: center; margin-left: 2%; margin-right: 2%;">Rating: 4.5/5 <br>★★★★½</p>
                </div>
            </div>
        </div>
    </body>
</html>