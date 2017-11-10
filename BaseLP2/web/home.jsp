<%-- 
    Document   : home
    Created on : 05/11/2017, 10:09:25
    Author     : Camila
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Social Network</title>
        <meta charset="utf-8"/>
        <link rel="stylesheet" type="text/css" href="css/estilo.css"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
    </head>
    <body>
        <c:if test="${user == null}">
            <c:redirect url="index.jsp"></c:redirect>
        </c:if>
        <section id="mainContainer">
            <aside>
                <%@include file="WEB-INF/jspf/menu.jspf" %>
                <form id="formPost" action="Controller" method="POST">
                    <p>
                        O que você está pensando?<br>
                        <textarea name="posttext"></textarea>
                    </p>
                    <input type="hidden" name="command" value="Post.post"/>
                    <p>
                        <input type="submit" value="POST"/>
                    </p>
                </form>
                <article id="msgSection">
                    ${msg}
                    <c:set var="msg" value="" scope="session"></c:set>
                </article>
            </aside>
            <section id="mainSection">
            <c:forEach var="post" items="${user.posts}">
                <article>
                    <p>${post.posttext}</p>
                </article>
            </c:forEach>
            </section>
        </section>
    </body>
</html>