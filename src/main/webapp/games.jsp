<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="MyPackage.Game" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <title>XPHENO - The Definitive Gaming Site</title>
        <link rel="icon" type="image/png" href="img/favicon.png">
        <link rel="stylesheet" href="css/games.css">
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
                    <p id="sitedescription">Check out some of these epic games!</p>
    <table id="gametable">
        <tr>
            <th class="theads">Title</th>
            <th class="theads">Genre</th>
            <th class="theads">Author</th>
            <th></th>
            <th></th>
        </tr>
        <%
        List<Game> games = (List<Game>) request.getAttribute("games");
        if (games == null || games.size() < 1 ) {
        	out.print("Nothing but ghosts here!");
        }
        else if (games != null) {
                for (Game game : games) {
        %>        
        <tr>
            <td hidden><%= game.getId() %></td>
            <td><a id="gamelink" href="gameview.jsp?id=<%= game.getId() %>&title=<%= game.getTitle() %>&author=<%= game.getAuthor() %>&genre=<%= game.getGenre() %>&description=<%= game.getDescription() %>&coverpath=<%= game.getCoverimgpath()%>"><%= game.getTitle() %></a></td>
            <td><%= game.getGenre() %></td>
            <td><%= game.getAuthor() %></td>
			<td><img id="screenshots" src="<%= game.getCoverimgpath() %>">
			</td>
  
            <td id="delupd">
            
            <% if ( 
            		session_u_name.equals(game.getAuthor())  
            	) {
            	
            	%>
            <a href="updategame.jsp?id=<%= game.getId() %>&title=<%= game.getTitle() %>&description=<%= game.getDescription() %>">Edit</a>
             |
            <a href="GameUploadServlet?action=deletegame&id=<%= game.getId() %>">Delete</a>
            <%}%>
             
            </td>
        </tr>
        <%
            }
        }
        %>
    </table>
                </div>		
            </div>
        </div>

    </body>
</html>