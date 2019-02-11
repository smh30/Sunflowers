

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Home</title>
    <%@ include file="../WEB-INF/partial/_partial_header.jsp" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

    <style type="text/css">

        .article{
            padding: 6px;
            margin: 7px;
            border:2px solid #666666;
            border-radius: 12px;
            background-color: whitesmoke;
        }

    </style>

</head>

<body>
<%@ include file="../WEB-INF/partial/navbar.jsp" %>
<div class="bg">
    <c:if test="${message!=null}">
        <div class="alert alert-warning alert-dismissible" id="error-message">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <%--a message will display if a user has tried to login but had a wrong username or password--%>
                ${message}
        </div>
    </c:if>
<div class="container">

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

    <c:forEach items="${articles}" var="article">
        <div class="article">
            <c:choose>
                <c:when test="${article.title == null || empty article.title}">
                    <h2><a href="article?articleID=${article.ID}">Untitled</a></h2>
                </c:when>
                <c:otherwise>
                    <h2><a href="article?articleID=${article.ID}">${article.title}</a></h2>
                </c:otherwise>
            </c:choose>

            <p><a href="#" onclick="getAuthorInfo('${article.author.username}');return false;">Author: ${article.author.username}</a>
            </p>
            <c:if test="${not empty article.timeString}">

                <p>${fn:substring(article.timeString,0,16)}</p>
            </c:if>

            <c:set var="wholetext" value="${article.articleText}"/>
                <c:set var="firstpart" value="${fn:substring(wholetext,0, 500 )}"/>
                <c:set var="therest" value="${fn:substring(wholetext, 500, wholetext.length()-1)}"/>
                <c:set var="endpara" value="${fn:substringBefore(therest,'</p>')}"/>

                <c:set var="ismore" value="${fn:substringAfter(therest, '</p>')}"/>
                ${firstpart}<c:if test="${not empty therest}"><c:if test="${not empty ismore}">${endpara}
                            <br><a href="article?articleID=${article.ID}">read more</a>
                        </c:if>

                <c:set var="p" value="${fn:indexOf(therest, '</p>')}"/>

                <c:if test="${p == -1}">
                    <c:set var="endline" value="${fn:substring(therest, 0, fn:indexOf(therest, '\\\\n'))}"/>..${endline}
                </c:if>
            </c:if>
        </div>
    </c:forEach>

    <%--if there are more than 10 articles for the current search, show the back button to see more--%>
    <c:if test="${fn:length(articles) == 10}">
        <div class="mx-auto" style="width: 200px;">
            <form method="get" action="home">
                <input type="hidden" name="sort" value="${currentsort}">
                <input type="hidden" name="author" value="${searchParams.searchAuthor}">
                <input type="hidden" name="title" value="${searchParams.searchTitle}">
                <input type="hidden" name="date" value="${searchParams.searchDate}">
                <input type="hidden" name="currentback" value="${currentback}">

                <button class="btn btn-primary" type="submit" value="back" name="back" id="back">Back</button>
                <c:if test="${currentback != 0}">
                    <button class="btn btn-primary"  type="submit" value="forward" name="forward" id="forward">Forward</button>
                </c:if>
            </form>
        </div>
    </c:if>

</div>
</div>

<%@ include file="../WEB-INF/partial/_userinfomodal.jsp" %>

</body>
</html>
