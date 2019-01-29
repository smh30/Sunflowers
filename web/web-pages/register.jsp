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
        <!-- I've linked in the Bootstrap things so that the nav will work -->
        <!-- NOTE: i got these from the w3 website rather than the ones from our lab, so if there's a problem they might need to be changed. The navbar didn't work properly if I used the other ones.
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>

    <body>
    <!-- i've included the navbar here so that i can have links to test the login etc with - steph -->
    <%@ include file="navbar.jsp" %>
    <!--just a basic form so i can test the servlet -->
    <!-- if the username is already taken, print a message/or show a popup??-->
<% if (request.getAttribute("message") != null){
    %>
${message}

    <%
} %>

        <%--todo coordinate this with Yaz's profile edit--%>
    <form method="post" action="register">
        <label for="username">username:</label>
        <input type="text" id="username" name="username"><br>
        <label for="password">password:</label>
        <input type="password" id="password" name="password"><br>

        <%--todo do we need to gather all of this data when registering users, or can it happen later only when editing??--%>
        Ignore these ones, we will redirect to the profile edit page instead
        <label for="realname">Name:</label>
        <input type="text" id="realname" name="realname">
        <label for="country">Country:</label>
        <input type="text" id="country" name="country">
        <label for="dob">D.O.B:</label>
        <input type="date" id="dob" name="dob">
        <label for="bio">Bio:</label>
        <textarea id="bio" rows="10" cols="50"></textarea><br>

        <input type="submit" value="submit">
    </form>
        
    </body>
</html>
