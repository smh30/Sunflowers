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

    <!-- I've linked in the Bootstrap things so that the nav will work -->
    <!-- NOTE: i got these from the w3 website rather than the ones from our lab, so if there's a problem they might need to be changed. The navbar didn't work properly if I used the other ones.

    <!-- Latest compiled and minified CSS -->
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">--%>
   <%----%>
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
</head>

<body>
<!-- i've included the navbar here so that i can have links to test the login etc with - steph -->
<%@ include file="../WEB-INF/partial/navbar.jsp" %>
<div class="container">
<%--display the articles as appropriate--%>
<c:if test="${empty articles}">
    <p>No articles found for your search parameters</p>
</c:if>

    <%--begin dropdown for selecting sort order for articles--%>
    <p id="sort_options">
    <form action="home" method="GET">
    <label for="sort-options">Sort articles by: </label>
        <select name="sort-options" id="sort-options" onchange="this.form.submit()">
            <c:choose>
            <c:when test="${currentsort == 'title-z'}">
            <option value="newest">Newest First</option>
            <option value="oldest">Oldest First</option>
            <option value="author-a">Author</option>
            <option value="author-z">Author (reversed)</option>
            <option value="title-a">Title</option>
            <option value="title-z" selected="selected">Title (reversed)</option></c:when>
                <c:when test="${currentsort == 'oldest'}">
                    <option value="newest">Newest First</option>
                    <option value="oldest" selected="selected">Oldest First</option>
                    <option value="author-a">Author</option>
                    <option value="author-z">Author (reversed)</option>
                    <option value="title-a">Title</option>
                    <option value="title-z">Title (reversed)</option></c:when>
                <c:when test="${currentsort == 'author-a'}">
                    <option value="newest" >Newest First</option>
                    <option value="oldest">Oldest First</option>
                    <option value="author-a" selected="selected">Author</option>
                    <option value="author-z">Author (reversed)</option>
                    <option value="title-a">Title</option>
                    <option value="title-z">Title (reversed)</option></c:when>
                <c:when test="${currentsort == 'author-z'}">
                    <option value="newest">Newest First</option>
                    <option value="oldest">Oldest First</option>
                    <option value="author-a">Author</option>
                    <option value="author-z" selected="selected">Author (reversed)</option>
                    <option value="title-a">Title</option>
                    <option value="title-z">Title (reversed)</option></c:when>
                <c:when test="${currentsort == 'title-a'}">
                    <option value="newest" >Newest First</option>
                    <option value="oldest">Oldest First</option>
                    <option value="author-a">Author</option>
                    <option value="author-z">Author (reversed)</option>
                    <option value="title-a" selected="selected">Title</option>
                    <option value="title-z">Title (reversed)</option></c:when>
                <c:otherwise>
                    <option value="newest" selected="selected">Newest First</option>
                    <option value="oldest">Oldest First</option>
                    <option value="author-a">Author</option>
                    <option value="author-z">Author (reversed)</option>
                    <option value="title-a">Title</option>
                    <option value="title-z">Title (reversed)</option></c:otherwise>

            </c:choose>
        </select>
    </form>
    </p>
    <%--      end sort order dropdown     --%>


<%--  begin showing articles    --%>
    <c:forEach items="${articles}" var="article">
        <%--<c:if test="${article.author.username != 'deleted'}">--%>
        <div class="article">
            <c:choose>
            <c:when test="${article.title == null}">
                <h2><a href="/article?articleID=${article.ID}">Untitled</a></h2>
            </c:when>
                <c:otherwise>
            <h2><a href="/article?articleID=${article.ID}">${article.title}</a></h2>
                </c:otherwise>
            <%--todo make the 'author' link or popup the user info box/page--%>
            </c:choose>
        <p>Author: ${article.author.username}</p>
        <p>${article.timestamp}</p>
        <p>${article.articleText}</p>
            <%--todo add readmore--%>
            <%--todo add "see comments" and maybe a counter of how many comments there are--%>
            <hr>
        </div>
    </c:forEach>
<%--end articles display--%>

    <%--begin forward/ back block --%>
    <%--todo see if this can be done with jstl/el instead for correctness' sake--%>
    <%
        String getURL=request.getQueryString();
        String param = request.getParameter("back");
    %>

    <% int back = 0;
    if (param!= null) {
        back = Integer.parseInt(param);

    }
    back += 10;
    %>

  
    <%--todo get this so it works in search resuls as well (ie with other params)--%>
<c:if test="${fn:length(articles) == 10}">
    <%--<div id="goback">--%>
    <a href="?back=<%=back%>">older articles</a>

    <% if(back>=11){
        int forward = back -=20;
        %>

   || <a href="?back=<%=forward%>">newer articles</a>
    <%
    }%>

    <%--</div>--%>
    </c:if>



</div>

</body>
</html>
