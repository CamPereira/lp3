<%-- 
    Document   : index
    Created on : 05/11/2017, 10:05:26
    Author     : Camila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Social Network</title>
        <meta charset="utf-8"/>
        <link rel="stylesheet" type="text/css" href="css/estilo.css"/>
    </head>
    <body>
        <section id="loginsection">
            <h1>LOGIN</h1>
            <form action="Controller" method="POST">
                <p>
                    <label for="username">Username:</label><br>
                    <input type="text" name="username" id="username"
                           placeholder="username" value="${cookie.username.value}"/>
                </p>
                <p>
                    <label for="password">Password:</label><br>
                    <input type="password" name="password" id="password"
                           placeholder="password" value="${cookie.password.value}"/>
                </p>
                <p>
                    <input type="checkbox" name="remember" id="remember"/><label for="remember">Remember password</label>
                </p>
                <input type="hidden" name="command" value="Userlp2.login"/>
                <p>
                    <input type="submit" value="LOGIN"/>
                </p>
                <p>
                    <a href="#">Forgot Password?</a>
                    <a href="register.jsp">Register</a>
                </p>
            </form>
        </section>
    </body>
</html>
