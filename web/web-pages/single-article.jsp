
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>
    <c:choose>
        <c:when test="${article.title == null|| empty article.title}">Untitled Article
        </c:when>
        <c:otherwise>
            ${article.title}
        </c:otherwise>
    </c:choose>
    </title>

    <%@ include file="../WEB-INF/partial/_partial_header.jsp" %>
    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>


    <%--library for the comment pool(this is what's wreckong the navbar--%>
    <%--<link href="https://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">--%>
    <%--<link rel="stylesheet" href="style.css">--%>
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>--%>
    <%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>--%>

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



</head>
<body>

<%@ include file="../WEB-INF/partial/navbar.jsp" %>
<c:if test="${message!=null}">
    <div class="alert alert-warning alert-dismissible" id="error-message">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${message}
    </div>
</c:if>
<div class="container">

    <%--Article Display--%>
    <div class="article pt-5">

        <c:choose>
            <c:when test="${article.title == null|| empty article.title}">
                <h2><a href="article?articleID=${article.ID}">Untitled</a></h2>
            </c:when>
            <c:otherwise>
                <h2><a href="article?articleID=${article.ID}">${article.title}</a></h2>
            </c:otherwise>
        </c:choose>


        <p><a href="#" onclick="getAuthorInfo('${article.author.username}')">Author: ${article.author.username}</a>
        </p>

        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

        <c:if test="${not empty article.timeString}">
            <p>${fn:substring(article.timeString,0,16)}</p>
        </c:if>


        <c:if test="${postdated}">
            <div class="alert alert-warning alert-dismissible" id="error-message">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                This article is postdated: it will appear on the site on the date you selected
            </div>

        </c:if>
<div class="transparent-white">
        <p>${article.articleText}</p>
</div>


        <%--the 'edit' and 'delete' buttons will only appear if the logged in user wrote the article --%>
        <c:if test="${article.author.username == sessionScope.username}">
            <div class="d-flex flex-row-reverse">
            <form method="get" action="edit-article" class="px-2">
                <input type="hidden" name="articleID" value="${article.ID}">
                <button id="editarticle" class="btn btn-primary" type="submit" value="Edit Article"><i class='fas fa-edit'></i> Edit Article</button>
            </form>


            <form method="post" action="deleteArticle" class="px-2">
                <input type="hidden" name="articleID" value="${article.ID}">
                <button id="deletearticle" class="btn btn-primary" type="submit" value="Delete Article"><i class='fas fa-meh'></i> Delete Article</button>
            </form>
            </div>
        </c:if>
        <hr>
    </div>



    <%--Comment Publish Form--%>
    <c:if test="${sessionScope.username != null}">
        <%--another form which posts to /addcomment
                 text field for writing comment
                 submit button--%>
    <div class="add-comment">
        <div class="form-group">
            <form method="post" action="addcomment">
                <input type="hidden" name="articleID" value="${article.ID}">
                <label for="addcomment">Comment:</label>
                <textarea class="form-control" name="comment" rows="5" id="addcomment"
                          placeholder="Comment here:" maxlength="1000"></textarea>
                <br>
                <button id="addcommentbtn" class="btn btn-primary" type="submit" value="Add Comment">Add Comment</button>
            </form>

        </div>
    </div>

    </c:if>



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

        <div class="page-header">
            <h2 class="reviews">Comments</h2>
            <hr>
        </div>

    <myTags:childComments list="${comment}"/>
</body>
</html>

