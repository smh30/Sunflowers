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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>

<body>
<!-- i've included the navbar here so that i can have links to test the login etc with - steph -->
<%@ include file="navbar.jsp" %>
<div class="container">
<%--display the articles as appropriate--%>
<c:if test="${empty articles}">
    <p>No articles found for your search parameters</p>
</c:if>

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

    <%--</c:if>--%>
    </c:forEach>


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
