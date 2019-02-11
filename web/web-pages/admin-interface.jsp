<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Admin Interface</title>
        <%@include file="../WEB-INF/partial/_partial_header.jsp" %>
        <style>
            .content {
                padding: 6px;
                margin: 7px;
                border: 2px solid #666666;
                border-radius: 12px;
                background-color: white;
            }
        </style>
    </head>
    <body>
        <%@include file="../WEB-INF/partial/navbar.jsp" %>
        <c:if test="${message!=null}">
            <div class="alert alert-warning alert-dismissible" id="error-message">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a> ${message}
            </div>
        </c:if>
        <div class="container">
            <div class="content">
                The admin console is designed for use on a full-sized screen. Using it on mobile may be difficult.
            </div>

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
                                            <input type="hidden" name="username" value="${user.username}"> <input
                                                type="hidden" name="admin" value="admin"> <input type="submit"
                                                                                                 value="Delete User"
                                                                                                 class="btn btn-primary btn-sm"
                                                                                                 onclick="return confirm('Are you sure?')"/>
                                            <p id="remove"></p>
                                        </form>
                                    </td>
                                    <td>
                                        <form method="post" action="adminresetpassword">
                                            <button type="submit" class="btn btn-primary">Reset User's Password</button>
                                            <input type="hidden" name="username" value="${user.username}">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <br>
            <div class="content">
                <a href="#myPopup" class="btn btn-primary" data-toggle="collapse">Show 'Add new user' form</a>
                <div class="collapse" id="myPopup">
                    <form method="post" action="adminadduser" class="form-container">
                        <h2>Add user: </h2><br> <label for="unameID">Username:</label> <input type="text" id="unameID"
                                                                                              maxlength="30"
                                                                                              name="username"
                                                                                              class="form-control" pattern="^\S{4,30}" required> <br>
                        <label for="rnameID">Real Name:</label> <input type="text" id="rnameID" name="realname"
                                                                       maxlength="50" class="form-control"> <br> <label
                            for="countryID">Country:</label> <input type="text" id="countryID" maxlength="100"
                                                                    name="country" class="form-control"> <br> <label
                            for="dateofbirthID">Date of birth:</label> <input type="date" id="dateofbirthID"
                                                                              name="dateofbirth" class="form-control">
                        <br> <label for="descID">Description:</label> <textarea rows="4" cols="80" id="descID"
                                                                                maxlength="200" name="description"
                                                                                class="form-control">Description</textarea>
                        <br> <label for="passwordID">Password:</label> <input type="password" id="passwordID"
                                                                              name="password" minlength="8"
                                                                              class="form-control" required> <br> <label
                            for="emailID"> Email:</label> <input type="email" id="emailID" name="email" maxlength="100"
                                                                 class="form-control"> <br> <br>
                        <button type="submit" class="btn btn-primary" value="adminadduser">Create new user</button>
                    </form>
                </div>
            </div>
            <br>
            <div class="content responsive-md">
                <h2>Article Admin Table</h2>
                <div class="table responsive-md">
                    <table class="table table-sm">
                        <thead class="thead-light">
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
                                    <td>
                                        <c:choose> <c:when
                                                test="${article.title == null|| empty article.title}"> Untitled </c:when>
                                            <c:otherwise>
                                                ${article.title}
                                            </c:otherwise> </c:choose>
                                    </td>
                                    <td>${article.author.username}</td>
                                    <td>
                                        <form method="post" action="adminhideshowarticle">
                                            <button type="submit" class="btn btn-primary">Hide Article</button>
                                            <input type="hidden" name="articleID" value="${article.ID}"> <input
                                                type="hidden" name="action" value="hide">
                                        </form>
                                    </td>
                                    <td>
                                        <form method="post" action="adminhideshowarticle">
                                            <button type="submit" class="btn btn-primary">Show Article</button>
                                            <input type="hidden" name="articleID" value="${article.ID}"> <input
                                                type="hidden" name="action" value="show">
                                        </form>
                                    </td>
                                    <td>
                                        <form method="post" action="admincomments">
                                            <input type="hidden" name="articleID" value="${article.ID}"> <input
                                                type="hidden" name="admin" value="admin"> <input type="submit"
                                                                                                 class="btn btn-primary"
                                                                                                 value="Show Comments Table">
                                        </form>
                                    </td>
                                    <td>
                                        <c:choose> <c:when test="${article.hidden}"> Hidden </c:when>
                                            <c:otherwise> Showing </c:otherwise> </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
        <script>
            function checkRemoveUser() {
                var txt;
                if (confirm("Are you sure you want to remove this user?")) {
                    txt = "You pressed OK!";
                } else {
                    txt = "You pressed Cancel!";
                }
                document.getElementById("remove").innerHTML = txt;
            }
        </script>
    </body>
</html>
