<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav id="navbar" class="navbar navbar-expand-lg navbar-toggleable-lg">

    <div class="container-fluid">
        <c:if test="${sessionScope.username == null}">
            <a class="navbar-brand" href="home">Social Sunflowers</a>
        </c:if>

        <c:if test="${sessionScope.username != null}">
            <a class="navbar-brand" href="home"><img src="images/sunflower-4.png" alt="Icon" height="45" width="45"/>
                Social Sunflowers</a>
        </c:if>
        <button class="navbar-toggler navbar-toggler-right navbar-dark" type="button" data-toggle="collapse"
                data-target="#navbar-links"
                aria-controls="navbar-links" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>


        </button>
        <div class="collapse navbar-collapse" id="navbar-links">
            <ul class="nav navbar-nav">


                <li class="nav-item active"><a class="nav-link" href="home"><i class="fa fa-fw fa-home"></i>Home</a>
                </li>
                <li class="nav-item"><a class="nav-link" href="search"><i class="fa fa-fw fa-search"></i>Search</a></li>

                <c:if test="${sessionScope.username == null}">
                    <!-- show only the login and register if nobody is logged in -->
                    <li class="dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown"><i
                                class="fa fa-fw fa-user"></i>
                            Login
                        </a>
                        <div class="dropdown-menu pl-2 pr-4">
                            <form class="px-2" method="post" action="login">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="username" placeholder="username"
                                           name="username"></div>
                                <div class="form-group">
                                    <input type="password" class="form-control" id="password" placeholder="password"
                                           name="password">
                                </div>
                                <div class="form-group">
                                    <input type="hidden" name="from" value="${pageContext.request.requestURI}">
                                    <input type="hidden" name="articleID" value="${article.ID}">
                                    <input type="submit" class="btn-primary" value="Login">
                                </div>
                            </form>
                        </div>
                    </li>


                    <li class="nav-item"><a class="nav-link" href="register"><i class='fas fa-plus'></i> Register</a>
                    </li>


                </c:if>


                <c:if test="${sessionScope.username != null}">
                <!-- show the profile, 'look at your own entries' and logout only if logged in -->

                <li class="nav-item"><a class="nav-link" href="new-article"><i class='fas fa-file-alt'></i> New Article</a>
                </li>

                <li class="nav-item"><a class="nav-link" href="profile"><i class='fas fa-book-reader'></i> Profile</a>
                </li>

                <li class="nav-item"><a class="nav-link" href="home?author=${sessionScope.username}"><i
                        class='far fa-folder'></i> My Blog</a></li>

                <c:if test="${sessionScope.admin!=null}">

                    <li class="nav-item"><a class="nav-link" href="admininterface"><i class='fas fa-pen-alt'></i>Admin
                        Interface</a>
                    </li>
                </c:if>

                <li class="nav-item"><a class="nav-link" href="logout"><i class='fas fa-sign-out-alt'></i> Logout</a>
                </li>
            </ul>
        </div>

        </c:if>


    </div>
</nav>
