<%--
  Created by IntelliJ IDEA.
  User: yab2
  Date: 8/02/2019
  Time: 11:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Admin Interface Comments</title>
        <%@include file="../WEB-INF/partial/_partial_header.jsp" %>
    </head>
    <body>
        <%@include file="../WEB-INF/partial/navbar.jsp" %>
        <div id="comments_table" class="table-responsive-md container">
            <div class="content">
                <table class="table table-sm">
                    <thead class="thead-light">
                        <tr>
                            <th>Comment Author:</th>
                            <th>Comment Content:</th>
                            <th>Comment ID:</th>
                            <th>Comment Parent:</th>
                            <th>Hide Comment:</th>
                            <th>Show Comment:</th>
                            <th>Comment Status:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="comment" items="${comments}">
                            <tr>
                                <td>${comment.commentAuthor.username} </td>
                                <td>${comment.commentContent}</td>
                                <td>${comment.commentID}</td>
                                <td>${comment.parentID}</td>
                                <td>
                                    <form method="post" action="adminhideshowcomment">
                                        <button type="submit" class="btn btn-primary">Hide Comment</button>
                                        <input type="hidden" name="commentID" value="${comment.commentID}"> <input
                                            type="hidden" name="articleID" value="${articleID}"> <input type="hidden"
                                                                                                        name="action"
                                                                                                        value="hide">
                                    </form>
                                </td>
                                <td>
                                    <form method="post" action="adminhideshowcomment">
                                        <button type="submit" class="btn btn-primary">Show Comment</button>
                                        <input type="hidden" name="commentID" value="${comment.commentID}"> <input
                                            type="hidden" name="articleID" value="${articleID}"> <input type="hidden"
                                                                                                        name="action"
                                                                                                        value="show">
                                    </form>
                                </td>
                                <td>
                                    <c:choose> <c:when test="${comment.hidden}"> Hidden </c:when>
                                        <c:otherwise> Showing </c:otherwise> </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>


                <a href="admininterface" class="btn btn-primary" type="submit">Back to Admin Interface Homepage</a>
            </div>
        </div>
    </body>
</html>
