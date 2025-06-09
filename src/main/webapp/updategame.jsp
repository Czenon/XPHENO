<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    	<meta charset="UTF-8">
        <title>XPHENO - The Definitive Gaming Site</title>
        <link rel="icon" type="image/png" href="img/favicon.png">
        <link rel="stylesheet" href="css/upload.css">
    </head>
    <body>
        <h1 style="text-align: center; color: brown;"><a href="index.jsp">XPHENO - The Definitive Gaming Site</a></h1>
        <div id="container" class="container">
            <img src="img/banner.png" style="height: 150px; width: 100%; object-fit:cover; border: 2px solid rgb(30, 87, 68); border-radius: 2px;">

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
                if (session_u_name == null) {session_u_name = "Guest"; session.setAttribute("username", "Guest");}
                out.print(session_u_name);
%>
</b></div>                
            </div>
            <div class="container2">
                <div id="left">
                    <img src="img/img.jpg" style="max-width: 30vh; max-height: 70vh;">
                </div>
                <div id="middle">
                    <p id="sitedescription">Update your game data</p>
                    <hr>
                 <div id="desc">
                            <label>Game Title:</label>
                            <br>
                            <label>Genre:</label>
                            <br>
                            <label>Description:</label>
                            <br><br><br><br><br><br>
                            <label>Upload cover image:</label>
                    </div>
                 <div id="inputs">
                 <form action="/XPHENO/GameUploadServlet?action=updategame" method="post" enctype="multipart/form-data">
                 <input type="hidden" name="id" value="<%= request.getParameter("id") %>">
                    <input id="gametitle" name="gametitle" type="text" value="<%= request.getParameter("title") %>" required>
                    <br>
                    <select id="gamegenre" name="gamegenre" required>
                        <option value="FPS">FPS</option>
                        <option value="RTS">RTS</option>
                        <option value="Turn-Based Strategy">Turn-Based Strategy</option>
                        <option value="Racing">Racing</option>
                        <option value="Survival">Survival</option>
                        <option value="4X">4X</option>
                    </select>
                    <br>
                    <textarea id="gamedesc" name="gamedesc"  rows="8" required><%= request.getParameter("description") %></textarea>
                    <br>
                    <input type="file" id="file" name="file" accept="image/jpeg, image/png" required>
                    <br><br>
                    <input id="clearbutton" type="reset" value="Clear all fields">
                    <input id="releasebutton" onclick="showUploadMsg()" type="submit" value="Update your game data!">
                </form>
                <p id="statuslabel"></p>    
                </div>

                </div>
            </div>
        </div>
    <script src="upload.js"></script>
    </body>
</html>