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
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.username} </td>
                                <td><button onclick="checkRemoveUser()" value="/adminadduser">Remove User</button>
                                    <p id="remove"></p>
                                </td>

                                <td><button type="button" onclick="" >Reset User's Password</button></td>

                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </form>
        </div>

        <a href="#myPopup" class="btn btn-default" data-toggle="collapse">Add new user form</a>

        <div class="collapse" id="myPopup">

            <form method="post" action="adminadduser"  class="form-container">
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
                <input type="date" id="dateofbirthID" name="dateofbirth">
                <br>
                <label for="descID">Description:</label>
                <textarea rows="4" cols="80" id="descID" name="description">Description</textarea>
                <br>
                <label for="passwordID">Password:</label>
                <input type="password" id="passwordID" name="password">
                <br>
                <label for="emailID"> Email:</label>
                <input type="email" id="emailID" name="email">
                <br>
                <br>
                <%--Here we are adding in a new user--%>
                <button type="submit" class="btn" value="/adminadduser">Create new user</button>
            </form>
        </div>
        <script>
            <%--Google re this form how the popup works and if it can send info to servlet.--%>
            
            function checkRemoveUser() {
                var txt;
                if (confirm("Are you sure you want to remove this user?")) {
                    txt = "You pressed OK!";
                    <%--If press ok, refresh/redirect page, but also tell sql database to delete user--%>
                } else {
                    txt = "You pressed Cancel!";
                    <%--If press cancel, redirect back to admin interface--%>
                }
                document.getElementById("remove").innerHTML = txt;
            }
        </script>
    </body>
</html>
