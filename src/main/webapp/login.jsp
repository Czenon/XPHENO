<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    	<meta charset="UTF-8">
        <title>XPHENO - Real Nigga Gaming</title>
        <link rel="icon" type="image/png" href="img/favicon.png">
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>
        <h1 style="text-align: center; color: brown;"><a href="index.jsp">XPHENO - REAL NIGGA GAMING</a></h1>
        <div id="container" class="container">
            <img src="img/banner.png" style="height: 150px; width: 100%; object-fit:cover; border: 2px solid rgb(30, 87, 68)">
            <img id="eye" src="img/eyeshitpost.gif">

            <div id="menu">
                <a href="index.jsp" class="gamemenu">Home</a>
                <a href="games.jsp" class="gamemenu">Games</a>
                <a href="upload.jsp" class="gamemenu">Upload</a>
                <a href="login.jsp" class="gamemenu">Log In</a>
            </div>
         <div id="formcontainer">
            <div id="center">
                <form action="/XPHENO/LoginServlet" method="post">
                    <br><br><br>
                    <h2 style="color:goldenrod; padding-bottom: 1em">Sign in to your account</h2>
                    <p>USERNAME</p>
                    <input type="text" id="username" name="username" placeholder="Enter your nickname" required>
                    <br>
                    <p>PASSWORD</p>
                    <input type="password" id="password" minlength="6" maxlength="20" placeholder="Enter your password" required>
                    <br><br>
                    <input type="reset"  value="Clear">
                    <input type="submit" value="Log in">
                </form>
                <p>Not a member yet? Register <a href="register.jsp">here!</a></p>
                <p>Forgot your password? Go fuck yourself.</p>
            </div>
         </div>
        </div>

    </body>
</html>