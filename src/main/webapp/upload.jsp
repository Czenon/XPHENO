<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    	<meta charset="UTF-8">
        <title>XPHENO - Real Nigga Gaming</title>
        <link rel="icon" type="image/png" href="img/favicon.png">
        <link rel="stylesheet" href="css/upload.css">
    </head>
    <body>
        <h1 style="text-align: center; color: brown;"><a href="index.jsp">XPHENO - REAL NIGGA GAMING</a></h1>
        <div id="container" class="container">
            <img src="img/banner.png" style="height: 150px; width: 100%; object-fit:cover; border: 2px solid rgb(30, 87, 68); border-radius: 2px;">
            <img id="eye" src="img/eyeshitpost.gif">

            <div id="menu">
                <a href="index.jsp" class="gamemenu">Home</a>
                <a href="games.jsp" class="gamemenu">Games</a>
                <a href="upload.jsp" class="gamemenu">Upload</a>
                <a href="login.jsp" class="gamemenu">Log In</a>
            </div>
            <div class="container2">
                <div id="left">
                    <img src="img/img.jpg" style="max-width: 30vh;">
                </div>
                <div id="middle">
                    <p id="sitedescription">Upload your epic game here! (Satisfaction might be guaranteed)</p>
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
                 <form action="/XPHENO/GameUploadServlet" method="post" enctype="multipart/form-data">
                    <input id="gametitle" name="gametitle" type="text" required>
                    <br>
                    <select id="gamegenre" name="gamegenre" required>
                        <option value="FPS">FPS</option>
                        <option value="RTS">RTS</option>
                        <option value="turnbased">Turn-Based Strategy</option>
                        <option value="racing">Racing</option>
                        <option value="survival">Survival</option>
                        <option value="4X">4X</option>
                    </select>
                    <br>
                    <textarea id="gamedesc" name="gamedesc" rows="8" required></textarea>
                    <br>
                    <input type="file" id="file" name="file" accept="image/jpeg, image/png">
                    <br><br>
                    <input id="clearbutton" type="reset" value="Clear all fields">
                    <input id="releasebutton" onclick="showUploadMsg()" type="submit" value="Publish your game!">
                </form>
                <p id="statuslabel"></p>    
                </div>

                </div>
            </div>
        </div>
    <script src="upload.js"></script>
    </body>
</html>