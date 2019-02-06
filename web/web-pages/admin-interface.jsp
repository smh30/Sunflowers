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
                            <th>Username:</th>
                            <th>Remove Option:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%--Not sure if can put EL in without brackets???--%>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.username} </td>
                                <td>
                                    <button type="button" onclick="">Remove User</button>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </form>
        </div>
        <%--Add "Add User" button--%>
        <div class="container">
            <%--Have followed example of button in register.jsp--%>
            <%--Am I able to put just a button in a form????--%>
            <form method="post" action="register">
            <button type="button" value="Submit" id="btn submit">Add User</button>
            </form>
        </div>
    </body>
</html>
