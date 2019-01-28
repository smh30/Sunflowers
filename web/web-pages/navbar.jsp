<%--
  Created by IntelliJ IDEA.
  User: stephaniehope
  Date: 25/01/19
  Time: 9:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<!-- THIS IS A NAVBAR WHICH CAN BE INCLUDED INTO ALL OF THE OTHER PAGES AS NECESSARY -->
<!-- todo add actual link destinations to all the buttons -->
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">BLOGIFY</a>
        </div>
        <ul class="nav navbar-nav">

            <li class="nav-item active"><a class="nav-link" href="#">Home</a></li>
            <c:if test="${sessionScope.username == null}">
                <!-- show only the login and register if nobody is logged in -->
                <li class="dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                        Login
                    </a>
                    <div class="dropdown-menu px-2">
                        <%--todo get the dropdown form to have proper padding/margins --%>
                        <form class="px-4" method="post" action="login">
                            <div class="form-group">
                                <input type="text" class="form-control" id="username" placeholder="username"
                                       name="username">
                                <input type="password" class="form-control" id="password" placeholder="password"
                                       name="password">
                            </div>
                            <div class="form-group">
                                <input type="submit" value="Login">
                            </div>
                        </form>
                    </div>
                </li>

                <li class="nav-item"><a class="nav-link" href="register">Register</a></li>
            </c:if>

            <c:if test="${sessionScope.username != null}">
            <!-- show the profile, 'look at your own entries' and logout only if logged in -->
            <li class="nav-item"><a class="nav-link" href="#">Profile</a></li>
            <li class="nav-item"><a class="nav-link" href="#">My Blog</a></li>
            <li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="navbar-text">welcome, ${sessionScope.username}</li>
            </c:if>


        </ul>
    </div>
</nav>
</body>
</html>