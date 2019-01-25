<%--
  Created by IntelliJ IDEA.
  User: yab2
  Date: 25/01/2019
  Time: 3:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
    <!--just a basic form so i can test the servlet -->
    <!-- if the username is already taken, print a message/or show a popup??-->
<% if (request.getAttribute("message") != null){
    %>
${message}

    <%
} %>

    <form method="post" action="register">
        <label for="username">username:</label>
        <input type="text" id="username" name="username">
        <label for="password">password:</label>
        <input type="password" id="password" name="password">
        <input type="submit" value="submit">
    </form>
        
    </body>
</html>
