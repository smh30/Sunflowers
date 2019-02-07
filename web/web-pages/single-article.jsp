<%@ page import="ictgradschool.project.JavaBeans.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="ictgradschool.project.JavaBeans.Article" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.IOException" %><%--
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
    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
    <%--library for icon--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>

    <script type="text/javascript">

        function getAuthorInfo(authorName) {
            console.log("name to search:" + authorName);
            $.ajax({
                url: "userInfo",
                type: "POST",
                data: {username: authorName},
                success: function (msg) {

                    console.log("a message arrived back from userInfo: " + JSON.stringify(msg));
                    console.log("the real name:" + msg.realname);
                    //do the thing to show if it's good or not
                    $('#modal-title').text("Username: " + msg.username);
                    $('#modal-body').text("");
                    $('#modal-body').append("<img src=" + msg.image + "><br>");

                    if (msg.realname !== "" && msg.realname !== null) {
                        $('#modal-body').append("Real Name: " + msg.realname + "<br>");
                    }
                    if (msg.dob != null) {
                        $('#modal-body').append("Date of Birth: " + msg.dob + "<br>");
                    }
                    if (msg.country !== "" && msg.country !== null) {
                        $('#modal-body').append("Country: " + msg.country + "<br>");
                    }
                    if (msg.bio !== "" && msg.bio !== null) {
                        $('#modal-body').append("Bio: " + msg.bio + "<br>");
                    }


                    $("#userInfoModal").modal('show');


                }
            })
        }
    </script>
    <style>

        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        * {
            box-sizing: border-box;
        }

        .red {
            color: red;
        }

        /* Button used to open the contact form - fixed at the bottom of the page */
        .open-button {

            background-color: #555;
            color: white;
            padding: 5px 5px;
            border: none;
            cursor: pointer;
            opacity: 0.8;
            bottom: 23px;
            right: 28px;
            width: 90px;

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
            max-width: 100%;
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
            padding: 5px 5px;
            border: none;
            cursor: pointer;
            width: 45%;
            margin-bottom: 10px;
            margin-left: 3%;
            opacity: 0.8;
            display: inline-block;
        }

        /* Add a red background color to the cancel button */
        .form-container .cancel {
            width: 45%;
            display: inline-block;
            margin-right: 1%;
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

    <%--Article Display--%>
    <div class="article">

        <c:choose>
            <c:when test="${article.title == null}">
                <h2><a href="/article?articleID=${article.ID}">Untitled</a></h2>
            </c:when>
            <c:otherwise>
                <h2><a href="/article?articleID=${article.ID}">${article.title}</a></h2>
            </c:otherwise>
        </c:choose>

        <p><a href="#" onclick="getAuthorInfo('${article.author.username}')">Author: ${article.author.username}</a>
        </p>

        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
        <c:if test="${not empty article.timestamp}">
    <span title="${article.timestamp}"><fmt:formatDate value="${article.timestamp}"
                                                       pattern="MM/dd/yyyy HH:mm"/></span>
        </c:if>
        <%--<p>${article.timestamp}</p>--%>

        <c:if test="${postdated}">
            <div class="alert alert-warning alert-dismissible" id="error-message">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                This article is postdated: it will appear on the site on the date you selected
            </div>

        </c:if>

        <p>${article.articleText}</p>


        <%--the 'edit' and 'delete' buttons will only appear if the logged in user wrote the article --%>
        <c:if test="${article.author.username == sessionScope.username}">
            <div class="d-flex flex-row-reverse">
            <form method="get" action=/edit-article class="px-2">
                <input type="hidden" name="articleID" value="${article.ID}">
                <button class="btn btn-primary" type="submit" value="Edit Article"><i class='fas fa-edit'></i> Edit Article</button>
            </form>


            <form method="post" action="/deleteArticle" class="px-2">
                <input type="hidden" name="articleID" value="${article.ID}">
                <button class="btn btn-primary" type="submit" value="Delete Article"><i class='fas fa-meh'></i> Delete Article</button>
            </form>
            </div>
        </c:if>
        <hr>
    </div>




    <%--Comments Pool--%>
    <div class="comments-pool">
        <%!
            private void output(Comment comment, JspWriter out, boolean canDelete, boolean canReply){
                try {
                    //Comment Output
                    //Check if comments can be deleted
                    if(canDelete) {
                        //If author can delete comments
                        //Output comment content first

                        out.print("<div>"+comment.getCommentAuthor().getUsername()+":"+"</div>");
//                        out.println("<div>" + comment.getCommentContent() + "</div>");

                        out.println("<div style=\"margin-left: 20px;\">" + comment.getCommentContent());

                        //Show delete button
                        out.println(
                                "<div class=\"d-flex flex-row-reverse\"><form method=\"GET\" action=\"/deletecomment\">" +
                                        "<input type=\"hidden\" name=\"articleID\" value=\""+comment.getArticleId()+"\">" +
                                        "<input type=\"hidden\" name=\"commentID\" value=\""+comment.getCommentID()+"\">" +
                                        "<button class=\"btn btn-primary btn-sm\"  type=\"submit\" value=\"Delete Comment\">Delete Comment</button>"+
                                        "</form></div>"
                        );
                    }else{
                        //Otherwise just output comment
                        out.println("<div style=\"margin-left: 20px;\">" + comment.getCommentContent());
                    }
                    //Check if can reply comment
                    if(canReply){
                        //If so, show the reply function
                        //Show Reply Button
                        out.println(

//                                    "<button id=\"reply-btn-"+comment.getCommentID()+"\" class=\"open-button\" onclick=\"openForm("+comment.getCommentID()+")\">Reply</button>"+

                                "<button id=\"reply-btn-"+comment.getCommentID()+"\" class=\"open-button btn btn-primary btn-sm\" onclick=\"openForm("+comment.getCommentID()+")\">Reply</button>"

                        );
                        //Reply area
                        out.println(
                                "<div class=\"form-popup\" id=\"myForm-"+comment.getCommentID()+"\">" +
                                        "<form method=\"POST\" action=\"/addNestedComment\" class=\"form-container\">" +
                                        "<input type=\"hidden\" name=\"articleID\" value=\""+comment.getArticleId()+"\">" +
                                        "<input type=\"hidden\" name=\"commentID\" value=\""+comment.getCommentID()+"\">" +
                                        "<label for=\"content\"><b>Reply Comment:</b></label>" +
                                        "<input type=\"text\" id=\"content\" placeholder=\"Comment here...\" name=\"content\">" +
                                        "<button type=\"submit\" class=\"btn\">Submit</button>" +
                                        "<button type=\"button\" class=\"btn cancel\" onclick=\"closeForm("+comment.getCommentID()+")\">Close</button>" +
                                        "</form>" +
                                        "</div>"
                        );
                    }
                    //Comment Recursion
                    if (comment.getChildren()!=null) {
                        for (Comment c : comment.getChildren()) {
                            output(c, out, canDelete, canReply);
                        }
                    }
                    out.println("</div>");
                }catch (IOException e){
                    System.err.println(e.fillInStackTrace());
                }
            }
        %>
        <%
            Article article = (Article) request.getAttribute("article");
            List<Comment> rootComments = (List<Comment>) request.getAttribute("comment");
            for(Comment c:rootComments){
                //In the case when the author is the article author or the user who left comment, they can delete comment
                boolean canDelete = (article.getAuthor().getUsername() .equals( request.getSession().getAttribute("username"))) || (c.getCommentAuthor().getUsername().equals(request.getSession().getAttribute("username")));
                //In the case for any signed in users, they can do reply
                boolean canReply = (request.getSession().getAttribute("username") != null);
                output(c, out, canDelete, canReply);
            }
        %>
    </div>

    <%--Comment Publish Form--%>
    <c:if test="${sessionScope.username != null}">
        <%--another form which posts to /addcomment
                 text field for writing comment
                 submit button--%>
    <div class="add-comment">
        <div class="form-group">
            <form method="post" action="/addcomment">
                <input type="hidden" name="articleID" value="${article.ID}">
                <label for="comment">Comment:</label>
                <textarea class="form-control" name="comment" rows="5" id="comment"
                          placeholder="Comment here:"></textarea>
                <br>
                <button class="btn btn-primary" type="submit" value="Add Comment">Add Comment</button>
            </form>

        </div>
    </div>

    </c:if>



                <%-- the previous code for comments --%>

        <%--<c:forEach items="${comment}" var="comment">--%>
        <%--<c:if test="${comment.commentAuthor.username != 'deleted'}">--%>

        <%--<div class="comment">--%>
                <%--&lt;%&ndash; use a table to hold the comments &ndash;%&gt;--%>
            <%--<p>${comment.commentAuthor.username} :</p>--%>

            <%--<p>${comment.commentContent}</p>--%>

            <%--<c:if test="${comment.children.size()!=0}">--%>
                <%--<c:forEach var="currentComment" items="${comment.children}" varStatus="">--%>
                    <%--&lt;%&ndash; TODO show the children comment for each root comment &ndash;%&gt;--%>
                <%--</c:forEach>--%>
            <%--</c:if>--%>

                <%--&lt;%&ndash;<c:forEach items="${comment.children}" var="children">&ndash;%&gt;--%>

                <%--&lt;%&ndash;<div class="red">${children.commentAuthor.username}</div>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<div class="red">${children.commentContent}</div>&ndash;%&gt;--%>


                <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>


            <%--<c:if test="${article.author.username == sessionScope.username || comment.commentAuthor.username == sessionScope.username}">--%>
            <%--<form method="GET" action="/deletecomment">--%>
                <%--<input type="hidden" name="articleID" value="${article.ID}">--%>
                <%--<input type="hidden" name="commentID" value="${comment.commentID}">--%>

                <%--<input type="submit" value="Delete comment">--%>


            <%--</form>--%>

                <%--&lt;%&ndash; the popup form for replying the comment &ndash;%&gt;--%>

            <%--<button id="reply-btn-${comment.commentID}" class="open-button" onclick="openForm(${comment.commentID})">Reply--%>
            <%--</button>--%>

            <%--<div class="form-popup" id="myForm-${comment.commentID}">--%>
                <%--<form method="post" action="/addNestedComment" class="form-container">--%>
                    <%--<input type="hidden" name="articleID" value="${article.ID}">--%>
                    <%--<input type="hidden" name="commentID" value="${comment.commentID}">--%>

                    <%--<label for="content"><b>Reply comment:</b></label>--%>
                    <%--<input type="text" id="content" placeholder="comment here..." name="content">--%>

                    <%--<button type="submit" class="btn">Submit</button>--%>
                    <%--<button type="button" class="btn cancel" onclick="closeForm(${comment.commentID})">Close</button>--%>
                <%--</form>--%>
            <%--</div>--%>


                <%--&lt;%&ndash;<button class="open-button" onclick="openForm()">Reply</button>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<div class="form-popup" id="myForm">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<form action="/action_page.php" class="form-container">&ndash;%&gt;--%>

                <%--&lt;%&ndash;<label for="reply"><b>Reply</b></label>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<input type="textarea" placeholder="Enter Reply" id="reply" name="reply" required>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<button type="submit" class="btn">Submit</button>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<button type="button" class="btn cancel" onclick="closeForm()">Close</button>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</form>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

                <%--&lt;%&ndash;this form will pop up when the button is pressed&ndash;%&gt;--%>
            <%--<a href="#hiddenreply${comment.commentID}" class="btn btn-default" data-toggle="collapse">show reply box</a>--%>
            <%--<div id="hiddenreply${comment.commentID}" class="collapse">--%>
                <%--<form>--%>
                    <%--<label for="nested-reply">reply: </label>--%>
                    <%--<textarea id="nested-reply" rows="5" cols="30"></textarea>--%>
                <%--</form>--%>


                <%--</c:if>--%>

            <%--</div>--%>


            <%--</c:if>--%>


        <%--</div>--%>


        <%--</c:forEach>--%>

        <%--<c:if test="${sessionScope.username != null}">--%>
            <%--&lt;%&ndash;another form which posts to /addcomment--%>
                     <%--text field for writing comment--%>
                     <%--submit button&ndash;%&gt;--%>
        <%--<div class="comments">--%>
            <%--<div class="form-group">--%>
                <%--<form method="post" action="/addcomment">--%>
                    <%--<input type="hidden" name="articleID" value="${article.ID}">--%>
                    <%--<label for="comment">Comment:</label>--%>
                    <%--<textarea class="form-control" name="comment" rows="5" id="comment"--%>
                              <%--placeholder="Comment here:"></textarea>--%>
                    <%--<br>--%>
                    <%--<input type="submit" value="Add Comment">--%>
                <%--</form>--%>

            <%--</div>--%>
        <%--</div>--%>

        <%--</c:if>--%>




    <script>
        function openForm(id) {
            var className = "myForm-" + id;
            document.getElementById(className).style.display = "block";
        }

        function closeForm(id) {
            var className = "myForm-" + id;
            document.getElementById(className).style.display = "none";
        }
    </script>

    <%--this modal pops up if the username is clicked, it shows the user info--%>
    <div class="modal" id="userInfoModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title" id="modal-title">Modal Heading</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body" id="modal-body">

                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>

            </div>
        </div>
    </div>

</body>
</html>

