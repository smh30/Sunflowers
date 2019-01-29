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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%@ include file="navbar.jsp" %>
        <div class="container">

            <div class="article">
                <h2>${article.title}</h2>

                <p>Author: ${article.author.username}</p>
                <p>${article.timestamp}</p>
                <p>${article.articleText}</p>


                <%--todo --%>
                <form method="post" action=#>
                <input type="submit" value="Edit Article">
                </form>
<form method="post" action="#">
                <input type="submit" value="Delete Article">
</form>
            </div>


        </div>

    </body>
</html>
