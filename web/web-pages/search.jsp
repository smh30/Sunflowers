<%--
  Created by IntelliJ IDEA.
  User: smh30
  Date: 29/01/2019
  Time: 12:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Search</title>
        <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">--%>

        <%--<!-- jQuery library -->--%>
        <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>--%>

        <%--<!-- Latest compiled JavaScript -->--%>
        <%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>--%>
        <%@ include file="../WEB-INF/partial/_partial_header.jsp" %>
    </head>
    <body>
        <%@ include file="../WEB-INF/partial/navbar.jsp" %>
        <div class="container">

            <h3>Search the Articles Database</h3>

            <form method="get" action="/home">
                <label for="author">Search by User: </label>
                <input type="text" id="author" name="author">
                <br>
                <label for="title">Search by Title: </label>
                <input type="text" id="title" name="title">
                <br>
                <label for="date">Search by Date: </label>
                <input type="date" id="date" name="date">
                <br>

                <input type="submit" value="Search">
            </form>

        </div>
        
    </body>
</html>
