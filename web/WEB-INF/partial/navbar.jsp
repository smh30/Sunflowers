<!-- THIS IS A NAVBAR WHICH CAN BE INCLUDED INTO ALL OF THE OTHER PAGES AS NECESSARY -->
<!-- todo add actual link destinations to all the buttons -->
    <!--todo navbar expand/collapse seems to be stuck on md no matter what i change this to -->
<nav class="navbar navbar-expand-sm navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">BLOGIFY</a>
        </div>
        <ul class="nav navbar-nav">

            <li class="nav-item active"><a class="nav-link" href="/home">Home</a></li>
            <li class="nav-item"><a class="nav-link" href="/web-pages/search.jsp">Search</a></li>
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
                                <input type="hidden" name="from" value="${pageContext.request.requestURI}">
                                <input type="hidden" name="articleID" value="${article.ID}">
                                <input type="submit" value="Login">
                            </div>
                        </form>
                    </div>
                </li>

                <li class="nav-item"><a class="nav-link" href="register">Register</a></li>

            </c:if>

            <c:if test="${sessionScope.username != null}">
            <!-- show the profile, 'look at your own entries' and logout only if logged in -->


            <li class="nav-item"><a class="nav-link" href="/new-article">New Article</a> </li>

            <li class="nav-item"><a class="nav-link" href="/profile">Profile</a></li>

            <li class="nav-item"><a class="nav-link" href="/home?author=${sessionScope.username}">My Blog</a></li>
            <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="navbar-text">welcome, ${sessionScope.username}</li>
            </c:if>


        </ul>
    </div>
</nav>
