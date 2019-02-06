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
        <script type="text/javascript">function openForm() {
            alert('Button pressed!');
        }

        function closeForm() {
            alert('Button pressed!')
        }
        </script>
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
        <%--Using Yun's pop-up form from single-article.jsp as e.g.--%>

        <button class="open-button" onclick="openForm()">Add User</button>

        <div class="form-popup" id="myForm">
            <form method="post" action="adminadduser" class="form-container">
                <h2>Add user: </h2>
                <br>
                <label for="unameID">Username:</label>
                <input type="text" id="unameID" name="username">
                <br>
                <label for="rnameID">Real Name:</label>
                <input type="text" id="rnameID" name="realname">
                <br>
                <label for="countryID">Country:</label>
                <input type="text" id="countryID" name="country">
                <br>
                <label for="dateofbirthID">Date of birth:</label>
                <input type="text" id="dateofbirthID" name="dateofbirth">
                <br>
                <label for="descID">Description:</label>
                <textarea rows="4" cols="80" id="descID" name="description">Description</textarea>
                <br>
                <label for="passwordID">Password:</label>
                <input type="text" id="passwordID" name="password">
                <br>
                <br>
                <button type="submit" class="btn" id="openButton">Create new user</button>
                <button type="button" class="btn cancel" id="closeButton" onclick="closeForm()">Close</button>
            </form>
        </div>

        <script>
                function openForm() {
                var openButton = document.getElementById("myForm").style.display = "block";
                <%--How to toggle form????--%>

            }

            function closeForm() {
                var closeButton = document.getElementById("myForm").style.display = "none";
                closeButton.onclick = openForm;
            }
        </script>
    </body>
</html>
