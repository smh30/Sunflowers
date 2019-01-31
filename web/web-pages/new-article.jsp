<!--
  Created by IntelliJ IDEA.
  User: yab2
  Date: 25/01/2019
  Time: 3:16 PM
  To change this template use File | Settings | File Templates.
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" xmlns="http://www.w3.org/1999/xhtml" version="2.0">--%>

    <html>
    <head>
        <meta charset="UTF-8">
        <title>AddNewArticle</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
        <body>
        <%@ include file="navbar.jsp" %>

        <% if (request.getAttribute("message") != null){
        %>
        ${message}

        <%
            } %>

        <% if (request.getAttribute("newarticle")!= null){%>
        <form action="new-article" method="post">
            <%}else{%>
            <form action="/edit-article" method="post">
                <input type="hidden" name="articleID" value="${article.ID}">
                <%}%>

            <%--<form action="/new-article" method="post">--%>

                <div class="container text-center">
                    <label for="exampleFormControlInput1">Article Title Here:</label>
                    <% if (request.getAttribute("title") != null){
                    %>
                    <input type="text" name="article_title" class="form-control" id="exampleFormControlInput1" value=${title}>
                    <%}else{ %>
                    <input type="text" name="article_title" class="form-control" id="exampleFormControlInput1" placeholder="Your new article title...">
                    <%  } %>
                    <br>
                    <br>
                </div>


                <div class="container text-center">
                    <label for="exampleFormControlTextarea1">Article Content Here:</label>

                    <% if (request.getAttribute("content") != null){
                    %>
                    <textarea class="form-control"  id="exampleFormControlTextarea1" rows="30">${content}</textarea>
                    <br>
                    <%}else{ %>
                    <textarea class="form-control" name="article_content" id="exampleFormControlTextarea1" rows="30" placeholder="Content here..."></textarea>
                    <br>
                    <%  } %>
                </div>


                <div class="container text-right">
                    <button class="btn btn-primary" type="submit">Submit</button>

                </div>
            </form>



        </body>
    </html>

