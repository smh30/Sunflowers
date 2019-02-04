<%--
  Created by IntelliJ IDEA.
  User: yab2
  Date: 4/02/2019
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Admin Interface</title>

        <%@include file="../WEB-INF/partial/_partial_header.jsp" %>
    </head>

    <body>
        <%@include file="../WEB-INF/partial/navbar.jsp" %>
        <div class="container">
            <form method=post>
                <table border="2">
                    <thead>
                        <tr>
                            <td>Username:</td>
                            <td>Remove Option:</td>
                        </tr>
                    </thead>
                    <tbody>
                        <%--Not sure if can put EL in without brackets???--%>
                        <c:forEach var="user" items="">
                            <tr>
                                    <%--Need to link this up from database--%>
                                <td>${user.username} </td>
                            </tr>
                            <tr>
                                <td>
                                    <button type="button" onclick="">Remove</button>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </form>
        </div>
        <%--Add "Add User" button--%>
    </body>
</html>
