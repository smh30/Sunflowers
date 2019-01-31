<%--
  Created by IntelliJ IDEA.
  User: yab2
  Date: 25/01/2019
  Time: 3:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <input type="hidden" name="articleID" value="${article.ID}">
                <input type="submit" value="Edit Article">
                </form>


                <form method="post" action="#">
                    <input type="hidden" name="articleID" value="${article.ID}">
                <input type="submit" value="Delete Article">
                </form>

            </div>


         <%-- show single comment under the article --%>
            <c:if test="${empty articles}">

            </c:if>

            <c:forEach items="${comment}" var="comment">
            <div class="comment">
                    <%-- use a table to hold the comments --%>
                <p>${comment.commentAuthor} :</p>

                <p>${comment.commentContent}</p>

                <input type="submit" value="Delete comment">
            </div>
            </c:forEach>

<c:if test="${sessionScope.username != null}">
            <%--another form which posts to /addcomment
                     text field for writing comment
                     submit button--%>
            <div class = "comments">
                <div class="form-group">
                    <form method="post" action="/addcomment">
                        <input type="hidden" name="articleID" value="${article.ID}">
                    <label for="comment">Comment:</label>
                    <textarea class="form-control" name="comment"  rows="5" id="comment" placeholder="Comment here:"></textarea>
                        <br>
                    <input type="submit" value="Add Comment">
                    </form>
                </div>
            </div>

            </c:if>
    </body>
</html>
