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
 I don't intend to leave it like this; I want to make it so login can be done with the navbar even if pw is wrong.
Need a simple page for testing though.

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
