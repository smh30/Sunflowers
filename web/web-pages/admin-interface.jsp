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
        <style>
            .content{
                padding: 6px;
                margin: 7px;
                border:2px solid #666666;
                border-radius: 12px;
                background-color: white;
            }
        </style>
    </head>

    <body>
        <%@include file="../WEB-INF/partial/navbar.jsp" %>
        <div class="container">
            <div class="content">
            <h2>User Admin Table</h2>
<div class="table-responsive-md">
                <table class="table table-sm">
                    <thead class="thead-light">
                        <tr>
                            <th>Username:</th>
                            <th>Remove Option:</th>
                            <th>Reset User's Password:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.username} </td>
                                <td>
                                    <form method="post" action="deleteuser">
                                        <input type="hidden" name="username" value="${user.username}">
                                        <input type="hidden" name="admin" value="admin">
                                            <%--<button onclick="checkRemoveUser()">Remove User</button>--%>
                                        <input type="submit" value="Delete User" class="btn btn-primary btn-sm"
                                               onclick="return confirm('Are you sure?')"/>
                                        <p id="remove"></p>
                                    </form>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-primary btn-sm" onclick="">Reset User's Password</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
</div></div>

        <br>
            <div class="content">
<div class="row">
        <a href="#myPopup" class="text-center btn btn-primary " data-toggle="collapse">Show 'Add new user' form</a>
</div>

        <div class="collapse" id="myPopup">

            <form method="post" action="adminadduser" class="form-container">
                <h2>Add user: </h2>
                <br>
                <label for="unameID">Username:</label>
                <input type="text" id="unameID" name="username" class="form-control">
                <br>
                <label for="rnameID">Real Name:</label>
                <input type="text" id="rnameID" name="realname" class="form-control">
                <br>
                <label for="countryID">Country:</label>
                <input type="text" id="countryID" name="country" class="form-control">
                <br>
                <label for="dateofbirthID">Date of birth:</label>
                <input type="date" id="dateofbirthID" name="dateofbirth" class="form-control">
                <br>
                <label for="descID">Description:</label>
                <textarea rows="4" cols="80" id="descID" name="description" class="form-control">Description</textarea>
                <br>
                <label for="passwordID">Password:</label>
                <input type="password" id="passwordID" name="password" class="form-control">
                <br>
                <label for="emailID"> Email:</label>
                <input type="email" id="emailID" name="email" class="form-control">
                <br>
                <br>
                <%--Here we are adding in a new user--%>
                <button type="submit" class="btn btn-primary" value="adminadduser">Create new user</button>
            </form>
        </div>
        </div>
        <br>
            <div class="content">
            <h2>Article Admin Table</h2>
                <div class="table-responsive-md">
                <table border="2" class="table">
                    <thead>
                        <tr>
                            <th>Article Title:</th>
                            <th>Article Author:</th>
                            <th>Hide Article:</th>
                            <th>Show Article:</th>
                            <th>Show Related Comments:</th>
                            <th>Article Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="article" items="${articles}">
                            <tr>
                                <td>${article.title}</td>
                                <td>${article.author.username}</td>
                                <td>
                                    <form method="post" action="adminhideshowarticle">
                                        <button type="submit" class="btn btn-primary btn-sm">Hide Article</button>
                                        <input type="hidden" name="articleID" value="${article.ID}">
                                        <input type="hidden" name="action" value="hide">
                                    </form>
                                </td>
                                <td>
                                    <form method="post" action="adminhideshowarticle">
                                   <button type="submit" class="btn btn-primary btn-sm">Show Article</button>
                                        <input type="hidden" name="articleID" value="${article.ID}">
                                    <input type="hidden" name="action" value="show">
                                    </form>
                                </td>
                                <td>
                                    <form method="post" action="admincomments">
                                        <input type="hidden" name="articleID" value="${article.ID}">
                                        <input type="hidden" name="admin" value="admin">
                                        <input type="submit" value="Show Comments Table" class="btn btn-primary btn-sm">
                                    </form>
                                </td>
                                <td>
                                <c:choose>
                                    <c:when test="${article.hidden}">
                                        Hidden
                                    </c:when>
                                    <c:otherwise>
                                        Showing
                                    </c:otherwise>
                                </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                </div></div>
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
