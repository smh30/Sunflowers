<%--
  Created by IntelliJ IDEA.
  User: yab2
  Date: 25/01/2019
  Time: 3:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Home</title>

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


<p>content goes here</p>
<%--display the articles as appropriate--%>

    <c:forEach items="${articles}" var="article">
        <h2>${article.title}</h2>
        <p>Author: ${article.author.username}</p>
        <p>${article.timestamp}</p>
        <p>${article.articleText}</p>


    </c:forEach>






</body>
</html>
