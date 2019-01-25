<%--
  Created by IntelliJ IDEA.
  User: stephaniehope
  Date: 25/01/19
  Time: 9:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

            <!--todo show the login and register only if nobody is logged in -->
            <li class="nav-item"><a class="nav-link" href="#">Login</a></li>
            <li class="nav-item"><a class="nav-link" href="register">Register</a></li>

            <!--todo show the profile and 'look at your own entries' only if logged in -->
            <li class="nav-item"><a class="nav-link" href="#">Profile</a></li>
            <li class="nav-item"><a class="nav-link" href="#">My Blog</a></li>
            <li class="nav-item"><a class="nav-link" href="#">Logout</a></li>

            <!-- Dropdown, not sure if needed but leaving this code here in case because i hate them, delete if unnecessary -->
            <li class="dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                    Dropdown link??
                </a>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="#">Link 1</a></li>
                    <li><a class="dropdown-item" href="#">Link 2</a></li>
                    <li><a class="dropdown-item" href="#">Link 3</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>
</body>
</html>
