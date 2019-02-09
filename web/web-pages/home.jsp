<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <%@ include file="../WEB-INF/partial/_partial_header.jsp" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

                    $('#modal-body').append("<div id=\"image\" class=\"text-center\"><img src="+ msg.image+"></div>");

                    if (msg.realname !== "" && msg.realname !== null){
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

    <style type="text/css">

        .article{
            padding: 6px;
            margin: 7px;
            border:2px solid #666666;
            border-radius: 12px;
            background-color: white;
        }

        a{
            color: #a94300;
        }



    </style>

</head>

<body>

<%@ include file="../WEB-INF/partial/navbar.jsp" %>
<div class="bg">
<div class="container">


    <%--a message will display if a user has tried to login but had a wrong username or password--%>
    <c:if test="${message!=null}">
        <div class="alert alert-warning alert-dismissible" id="error-message">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                ${message}
        </div>
    </c:if>

    <%--if there are no articles, this message will appear--%>
    <c:if test="${empty articles}">
            <div class="alert alert-warning alert-dismissible" id="error-message">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                No articles found for your search parameters
            </div>
    </c:if>

    <%--begin dropdown for selecting sort order for articles--%>
<div id="sort-dropdown" class="d-flex flex-row-reverse pt-2">
    <form action="home" method="GET" class="form-inline">
        <input type="hidden" name="author" value="${searchParams.searchAuthor}">
        <input type="hidden" name="title" value="${searchParams.searchTitle}">
        <input type="hidden" name="date" value="${searchParams.searchDate}">
        <label for="sort-options">Sort articles by:   </label>
        <select name="sort-options" id="sort-options" class="form-control"  onchange="this.form.submit()">
            <c:choose>
                <c:when test="${currentsort == 'title-z'}">
                    <option value="newest">Newest First</option>
                    <option value="oldest">Oldest First</option>
                    <option value="author-a">Author</option>
                    <option value="author-z">Author (reversed)</option>
                    <option value="title-a">Title</option>
                    <option value="title-z" selected="selected">Title (reversed)</option>
                </c:when>
                <c:when test="${currentsort == 'oldest'}">
                    <option value="newest">Newest First</option>
                    <option value="oldest" selected="selected">Oldest First</option>
                    <option value="author-a">Author</option>
                    <option value="author-z">Author (reversed)</option>
                    <option value="title-a">Title</option>
                    <option value="title-z">Title (reversed)</option>
                </c:when>
                <c:when test="${currentsort == 'author-a'}">
                    <option value="newest">Newest First</option>
                    <option value="oldest">Oldest First</option>
                    <option value="author-a" selected="selected">Author</option>
                    <option value="author-z">Author (reversed)</option>
                    <option value="title-a">Title</option>
                    <option value="title-z">Title (reversed)</option>
                </c:when>
                <c:when test="${currentsort == 'author-z'}">
                    <option value="newest">Newest First</option>
                    <option value="oldest">Oldest First</option>
                    <option value="author-a">Author</option>
                    <option value="author-z" selected="selected">Author (reversed)</option>
                    <option value="title-a">Title</option>
                    <option value="title-z">Title (reversed)</option>
                </c:when>
                <c:when test="${currentsort == 'title-a'}">
                    <option value="newest">Newest First</option>
                    <option value="oldest">Oldest First</option>
                    <option value="author-a">Author</option>
                    <option value="author-z">Author (reversed)</option>
                    <option value="title-a" selected="selected">Title</option>
                    <option value="title-z">Title (reversed)</option>
                </c:when>
                <c:otherwise>
                    <option value="newest" selected="selected">Newest First</option>
                    <option value="oldest">Oldest First</option>
                    <option value="author-a">Author</option>
                    <option value="author-z">Author (reversed)</option>
                    <option value="title-a">Title</option>
                    <option value="title-z">Title (reversed)</option>
                </c:otherwise>
            </c:choose>
        </select>
    </form>
</div>


    <%--      end sort order dropdown     --%>


    <%--  begin showing articles    --%>
    <c:forEach items="${articles}" var="article">
        <div class="article">
            <c:choose>
                <c:when test="${article.title == null || empty article.title}">
                    <h2><a href="article?articleID=${article.ID}">Untitled</a></h2>
                </c:when>
                <c:otherwise>
                    <h2><a href="article?articleID=${article.ID}">${article.title}</a></h2>
                </c:otherwise>
                <%--todo make the 'author' link or popup the user info box/page--%>
            </c:choose>

            <p><a href="#" onclick="getAuthorInfo('${article.author.username}');return false;">Author: ${article.author.username}</a>
            </p>
                <%--this block converts the timestamp to a nicer format for viewing on the page--%>
            <c:if test="${not empty article.timestamp}">
                <span title="${article.timestamp}">
                <fmt:formatDate value="${article.timestamp}" pattern="MM/dd/yyyy HH:mm"/></span>
            </c:if>

            <c:set var="wholetext" value="${article.articleText}"/>
                <c:set var="firstpart" value="${fn:substring(wholetext,0, 500 )}"/>
                <c:set var="therest" value="${fn:substring(wholetext, 500, wholetext.length()-1)}"/>
                <c:set var="endpara" value="${fn:substringBefore(therest,'</p>')}"/>
                <c:set var="ismore" value="${fn:substringAfter(therest, '</p>')}"/>
                ${firstpart}
                <c:if test="${not empty ismore}">---
                    ${endpara}</p>
                    <a href="article?articleID=${article.ID}">read more</a>
            </c:if>


        </div>
    </c:forEach>
    <%--end articles display--%>

    <%--if there are more than 10 articles for the current search, show the back button to see more--%>
    <c:if test="${fn:length(articles) == 10}">
        <div class="mx-auto" style="width: 200px;">
            <form method="get" action="home">
                <input type="hidden" name="sort" value="${currentsort}">
                <input type="hidden" name="author" value="${searchParams.searchAuthor}">
                <input type="hidden" name="title" value="${searchParams.searchTitle}">
                <input type="hidden" name="date" value="${searchParams.searchDate}">
                <input type="hidden" name="currentBack" value="${currentback}">

                <button class="btn btn-primary" type="submit" value="back" name="back" id="back">Back</button>
                <c:if test="${currentback != 0}">
                    <button class="btn btn-primary"  type="submit" value="forward" name="forward" id="forward">Forward</button>
                </c:if>
            </form>
        </div>
    </c:if>

</div>
</div>

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
