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
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
</head>

<body>

<%@ include file="../WEB-INF/partial/navbar.jsp" %>
<div class="container">
<%--a message will display if a user has tried to login but had a wrong username or password--%>
    <c:if test="${message!=null}">
        <div class="alert alert-warning alert-dismissible" id="error-message" >
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                ${message}
        </div>
    </c:if>

    <%--if there are no articles, this message will appear--%>
<c:if test="${empty articles}">
    <p>No articles found for your search parameters</p>
</c:if>

    <%--begin dropdown for selecting sort order for articles--%>
    <p id="sort_options">
    <form action="home" method="GET">
    <input type="hidden" name="author" value="${searchParams.searchAuthor}">
    <input type="hidden" name="title" value="${searchParams.searchTitle}">
    <input type="hidden" name="date" value="${searchParams.searchDate}">
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
        <%--todo maybe format the page so that these divs have a border or something to separate the articles?? or are cards? or whatever?--%>
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

            <%--this block converts the timestamp to a nicer format for viewing on the page--%>
            <c:if test="${not empty article.timestamp}">
                <span title="${article.timestamp}">
                <fmt:formatDate value="${article.timestamp}" pattern="MM/dd/yyyy HH:mm" /></span>
            </c:if>

            <p>${article.articleText}</p>
            <%--todo add readmore for long articles?--%>
            <hr>
        </div>
    </c:forEach>
<%--end articles display--%>

    <%--begin forward/ back block --%>
    <%--todo see if this can be done with jstl/el instead for correctness' sake--%>
    <%!
        String getURL;
        String backParam;
    %>

    <%
       getURL=request.getQueryString();
       backParam = request.getParameter("back");
    %>

    <% int back = 0;
    if (backParam!= null) {
        back = Integer.parseInt(backParam);
    }
    back += 10;
    %>

  
    <%--todo get this so it works in search resuls as well (ie with other params)--%>
<c:if test="${fn:length(articles) == 10}">
    <%--<div id="goback">--%>
    <%--do an "if geturl is null (ie there are no search params do this--%>
    <a href="?back=<%=back%>">older articles</a>
    <%--else if not null and no back concat onto the geturl &back= ? --%>
    <%--else if there's a back already, increase it by 10--%>

    <% if(back>=11){
        int forward = back -=20;
        %>

   || <a href="?back=<%=forward%>">newer articles</a>
    <%
    }%>

    <%--</div>--%>
    </c:if>

    Another try at the back function
    <form method="post" action="/home">
        <input type="hidden" name="sort" value="${currentsort}">
        <input type="hidden" name="searchAuthor" value="${searchParams.searchAuthor}">
        <input type="hidden" name="searchTitle" value="${searchParams.searchTitle}">
        <input type="hidden" name="searchDate" value="${searchParams.searchDate}">
        <input type="submit" value="back" name="back" id="back"><input type="submit" value="forward" name="forward" id="forward">
    </form>



</div>

</body>
</html>
