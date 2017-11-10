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
            <h1>SIGN UP</h1>
            <form action="Controller" method="POST">
                <p>
                    <label for="username">Username:</label><br>
                    <input type="text" name="username" id="username" placeholder="username" required/>
                </p>
                <p>
                    <label for="password">Password:</label><br>
                    <input type="password" name="password" id="password" placeholder="password" required/>
                </p>
                <p>
                    <label for="password2">Confirm Password:</label><br>
                    <input type="password" name="password2" id="password2" placeholder="confirm password" required/>
                </p>
                
                <p>
                    <label for="firstname">First Name:</label><br>
                    <input type="text" name="firstname" id="firstname" 
                           placeholder="first name" required/>
                </p>
                
                <p>
                    <label for="lastname">Last Name:</label><br>
                    <input type="text" name="lastname" id="lastname" 
                           placeholder="last name" required/>
                </p>
                
                <p>
                    <label for="email">Email:</label><br>
                    <input type="email" name="email" id="email" 
                           placeholder="email" required/>
                </p>
                
                <p>
                    <label for="birthday">Birthday:</label><br>
                    <input type="date" name="birthday" id="birthday" 
                           placeholder="birthday" required/>
                </p>
   
                <input type="hidden" name="command" value="Userlp2.register"/>
                <p>
                    <input type="submit" value="SIGN UP"/>
                </p>
            </form>
        </section>
    </body>
</html>
