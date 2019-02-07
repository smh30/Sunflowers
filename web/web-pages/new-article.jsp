<!-- Created by IntelliJ IDEA. User: yab2 Date: 25/01/2019 Time: 3:16 PM To change this template use File | Settings | File Templates. -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" xmlns="http://www.w3.org/1999/xhtml" version="2.0">--%>

<html>
    <head>
        <meta charset="UTF-8">
        <title>AddNewArticle</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <%@ include file="../WEB-INF/partial/_partial_header.jsp" %>
        <script src="../ckeditor/ckeditor.js"></script>
        <link rel="stylesheet" type="text/css" href="css/style.css"/>

        <style type="text/css">
            #label1{
                font-family: "Arial Black", arial-black ;
            }
            #label2{
                font-family: "Arial Black", arial-black ;
            }

            #submitBtn{
                -webkit-transition-duration: 0.4s;
                transition-duration: 0.4s;
                border: 2px solid #3e3e3e;
                border-radius: 12px;
                margin-top: 20px;
            }

            #submitBtn:hover{
                background-color: #3e3e3e;
                color: white;
            }


        </style>


    </head>
    <body>
        <%@ include file="../WEB-INF/partial/navbar.jsp" %>

        <%--a message will appear here if creating an article failed for some reason such as too long title/body--%>
        <c:if test="${message != null}">
            <div class="alert alert-warning alert-dismissible" id="error-message">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a> ${message}
            </div>
        </c:if>

<%--submits to a different servlet depending on whether it's a new article or an edit--%>
        <c:choose>
            <c:when test="${newarticle != null}">
                <form action="/new-article" method="post">
            </c:when>
            <c:otherwise>
                <form action="/edit-article" method="post">
                <input type="hidden" name="articleID" value="${article.ID}"> <%--<%}%>--%>
            </c:otherwise>
        </c:choose>

        <div class="container" id="choose-date">
            <br>
            <label for="date">Optionally, select a publication date for your post:</label>
            <input type="date" id="date" name="pub-date">
        </div>

        <div class="container text-center">
            <br>
            <label id="label1" for="exampleFormControlInput1">Article Title Here:</label>
            <c:choose>
                <c:when test="${title !=null}">
                    <input type="text" name="article_title" class="form-control" id="exampleFormControlInput1"
                           value='${title}'>
                </c:when>
                <c:otherwise>
                    <input type="text" name="article_title" class="form-control" id="exampleFormControlInput1"
                           placeholder="Your new article title...">
                </c:otherwise>
            </c:choose>

        </div>


        <div class="container text-center">
            <br>
            <label id="label2" for="exampleFormControlTextarea1">Article Content Here:</label>
            <c:choose>
                <c:when test="${content != null}">
                <textarea class="form-control" name="article_content" id="exampleFormControlTextarea1"
                          rows="30">${content}</textarea>
                </c:when>
                <c:otherwise>
                    <textarea class="form-control" name="article_content" id="exampleFormControlTextarea1" rows="30"
                              placeholder="Content here..."></textarea>
                </c:otherwise>
            </c:choose>
        </div>


        <div class="container text-right">
             <button id="submitBtn" class="btn" type="submit">Submit</button>
        </div>
        </form>

            <script>
                CKEDITOR.replace('exampleFormControlTextarea1');
            </script>

    </body>
</html>

