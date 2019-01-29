<%--
  Created by IntelliJ IDEA.
  User: stephaniehope
  Date: 26/01/19
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
 THIS PAGE WILL STOP BEING A THING EVENTUALLY
    BUT FOR NOW: YOUR USERNAME OR PASSWORD WAS INCORRECT

 <% if (request.getAttribute("message") != null){
 %>
 ${message}

 <%
     } %>

 <form method="post" action="login">
     <label for="username">username:</label>
     <input type="text" id="username" name="username">
     <label for="password">password:</label>
     <input type="password" id="password" name="password">
     <input type="submit" value="submit">
 </form>
</body>
</html>
