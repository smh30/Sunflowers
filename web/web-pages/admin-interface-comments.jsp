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
        <div class="comments_table">
            <form method=post>
                <table border="2">
                    <thead>
                        <tr>
                            <th>Comment Author:</th>
                            <th>Comment Content:</th>
                            <th>Comment ID:</th>
                            <th>Comment Parent:</th>
                            <th>Hide Comment:</th>
                            <th>Show Comment:</th>
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
                                    <button onclick="" value="">Hide Comment</button>
                                    <p id="hidecomment"></p>
                                </td>
                                <td>
                                    <button onclick="" value="">Show Comment</button>
                                    <p id="showcomment"></p>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>
    </body>
</html>
