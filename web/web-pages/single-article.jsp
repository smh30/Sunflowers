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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">--%>

        <%--<!-- jQuery library -->--%>
        <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>--%>

        <%--<!-- Latest compiled JavaScript -->--%>
        <%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>--%>

        <%@ include file="../WEB-INF/partial/_partial_header.jsp" %>

        <style>
            body {font-family: Arial, Helvetica, sans-serif;}
            * {box-sizing: border-box;}

            /* Button used to open the contact form - fixed at the bottom of the page */
            .open-button {
                background-color: #555;
                color: white;
                padding: 16px 20px;
                border: none;
                cursor: pointer;
                opacity: 0.8;

                bottom: 23px;
                right: 28px;
                width: 100px;
            }

            /* The popup form - hidden by default */
            .form-popup {
                display: none;

                bottom: 0;
                right: 15px;
                border: 3px solid #f1f1f1;
                z-index: 9;
            }

            /* Add styles to the form container */
            .form-container {
                max-width: 300px;
                padding: 10px;
                background-color: white;
            }

            /* Full-width input fields */
            .form-container input[type=text], .form-container input[type=password] {
                width: 100%;
                padding: 15px;
                margin: 5px 0 22px 0;
                border: none;
                background: #f1f1f1;
            }

            /* When the inputs get focus, do something */
            .form-container input[type=text]:focus, .form-container input[type=password]:focus {
                background-color: #ddd;
                outline: none;
            }

            /* Set a style for the submit/login button */
            .form-container .btn {
                background-color: #4CAF50;
                color: white;
                padding: 16px 20px;
                border: none;
                cursor: pointer;
                width: 100%;
                margin-bottom:10px;
                opacity: 0.8;
            }

            /* Add a red background color to the cancel button */
            .form-container .cancel {
                background-color: red;
            }

            /* Add some hover effects to buttons */
            .form-container .btn:hover, .open-button:hover {
                opacity: 1;
            }
        </style>
    </head>
    <body>
        <%@ include file="../WEB-INF/partial/navbar.jsp" %>
        <div class="container">

            <div class="article">
                <h2>${article.title}</h2>

                <p>Author: ${article.author.username}</p>
                <%--todo make a message show up if viewing a new post-dated article--%>
                <p>${article.timestamp}</p>
                <p>${article.articleText}</p>


                <%--the 'edit' and 'delete' buttons will only appear if the logged in user wrote the article --%>
                <c:if test="${article.author.username == sessionScope.username}">
                <form method="get" action=/edit-article>
                    <input type="hidden" name="articleID" value="${article.ID}">
                <input type="submit" value="Edit Article">
                </form>


                <form method="post" action="/deleteArticle">
                    <input type="hidden" name="articleID" value="${article.ID}">
                <input type="submit" value="Delete Article">
                </form>
                </c:if>
            </div>



            <%--wtf is this empty 'if' meant to be doing?????--%>
            <c:if test="${empty articles}">
            </c:if>



            <c:forEach items="${comment}" var="comment">
            <c:if test="${comment.commentAuthor.username != 'deleted'}">

            <div class="comment">
                    <%-- use a table to hold the comments --%>
                <p>${comment.commentAuthor.username} :</p>

                <p>${comment.commentContent}</p>

                        <c:if test="${article.author.username == sessionScope.username || comment.commentAuthor.username == sessionScope.username}">
                            <form method="GET" action="/deletecomment">
                                <input type="hidden" name="articleID" value="${article.ID}">
                                <input type="hidden" name="commentID" value="${comment.commentID}">


                                <input type="submit" value="Delete comment">


                            </form>

                            <%-- the popup form for replying the comment --%>
                            <%--<button class="open-button" onclick="openForm()">Reply</button>--%>
                            <%--<div class="form-popup" id="myForm">--%>
                                <%--<form action="/action_page.php" class="form-container">--%>

                                    <%--<label for="reply"><b>Reply</b></label>--%>
                                    <%--<input type="textarea" placeholder="Enter Reply" id="reply" name="reply" required>--%>

                                    <%--<button type="submit" class="btn">Submit</button>--%>
                                    <%--<button type="button" class="btn cancel" onclick="closeForm()">Close</button>--%>
                                <%--</form>--%>
                            <%--</div>--%>

                        <%--this form will pop up when the button is pressed--%>
                        <a href="#hiddenreply${comment.commentID}" class="btn btn-default" data-toggle="collapse">show reply box</a>
                                <div id="hiddenreply${comment.commentID}" class="collapse">
                                    <form>
                                        <label for="nested-reply">reply: </label>
                                        <textarea id="nested-reply" rows="5" cols="30"></textarea>
                                    </form>

                        </c:if>

            </div>


                 </c:if>


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

            <script>
                function openForm() {
                    document.getElementById("myForm").style.display = "block";
                }

                function closeForm() {
                    document.getElementById("myForm").style.display = "none";
                }
            </script>

    </body>
</html>
