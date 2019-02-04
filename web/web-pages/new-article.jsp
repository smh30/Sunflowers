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
        <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">--%>

        <%--<!-- jQuery library -->--%>
        <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>--%>

    <%--<!-- Latest compiled JavaScript -->--%>
    <%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>--%>

        <%--trying these bootstrap links instead, think prev may have been old versiton--%>
        <%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">--%>
        <%--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
        <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>--%>
        <%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>--%>
        <%@ include file="../WEB-INF/partial/_partial_header.jsp" %>

        <script src="../ckeditor/ckeditor.js"></script>
</head>
<body>
<%@ include file="../WEB-INF/partial/navbar.jsp" %>


        <% if (request.getAttribute("message") != null){
        %><div class="alert alert-warning alert-dismissible" id="error-message" >
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        ${message}
</div>
        <%
            } %>

        <% if (request.getAttribute("newarticle")!= null){%>
        <form action="/new-article" method="post">
            <%-- figure out what to do here --%>
            <%}else{%>
        <form action="/edit-article" method="post">
            <input type="hidden" name="articleID" value="${article.ID}">
                <%}%>
            <div class="container" id="choose-date">
                <label for="date">Optionally, select a publication date for your post:</label>
                <input type="date" id="date" name="pub-date">
            </div>

                <div class="container text-center">


                    <label for="exampleFormControlInput1">Article Title Here:</label>
                    <% if (request.getAttribute("title") != null){
                    %>
                    <input type="text" name="article_title" class="form-control" id="exampleFormControlInput1" value='${title}'>
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
                    <textarea class="form-control"  name="article_content" id="exampleFormControlTextarea1" rows="30">${content}</textarea>
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

        <script>
            CKEDITOR.replace('exampleFormControlTextarea1');
        </script>

        </body>
    </html>

