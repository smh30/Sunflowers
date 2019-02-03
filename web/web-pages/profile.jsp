<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yab2
  Date: 25/01/2019
  Time: 3:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--TODO: Write JS external file and pop in????--%>
<html>
    <%! String title = "Profile"; %>
    <%@ include file="../WEB-INF/_partial_header.jsp" %>

    <script type="text/javascript">
        function switchImage() {
            var image = document.getElementById("imageToSwap");
            var dropdown = document.getElementById("dlist");
            image.src = dropdown.value;
        }
    </script>
    <body>
        <%@ include file="../WEB-INF/partial/navbar.jsp" %>


        <div class="container-fluid">

            <h1>${user.username}'s Account:</h1>
            <br>
            <br>
            <%--Adding form for uploading default user pic (the one the user sees when they click on the profile webpage)--%>
            <%--Below is the default user pic link --%>

            <img id="imageToSwap" src="../default-photos-for-profile-page/Default.jpg" width="225">

            <div style="margin-left: 250px">
                <h4>Choose default picture: </h4>

                <form method="POST" action="/Upload" enctype="multipart/form-data">
                    <select id="dlist" onchange="switchImage()">
                        <option value="../default-photos-for-profile-page/CloneTrooper.jpg">Clone Trooper</option>
                        <option value="../default-photos-for-profile-page/Jigglypuff.jpg">Jigglypuff</option>
                        <option value="../default-photos-for-profile-page/Yoda.jpg">Yoda</option>
                    </select>
                    <h4>Choose your own picture to upload: </h4>
                    <input type="file" id="userPicture" name="userPicture" size="50" accept="image/png, image/jpeg">
                    <br>
                    <br>
                    <input type="submit" value="Upload">
                </form>
                <br>
                <br>
                <%--Image form ends. Edit from here--%>

                <form action="/editprofile" method="post">

                    <label for="unameID">Username:</label>
                    <c:choose>
                        <c:when test="${user.username!=null}">
                            <input type="text" id="unameID" name="username" value=${user.username}>
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="unameID" name="username">
                        </c:otherwise>
                    </c:choose>


                    <label for="countryID">Country:</label>
                    <c:choose>
                        <c:when test="${user.country!=null}">
                            <input type="text" id="countryID" name="country" value=${user.country}>
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="countryID" name="country">
                        </c:otherwise>
                    </c:choose>

                    <label for="rnameID">Real name:</label>
                    <c:choose>
                        <c:when test="${user.realName!=null}">
                            <input type="text" id="rnameID" name="realname" value="${user.realName}">
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="rnameID" name="realname">
                        </c:otherwise>
                    </c:choose>

                    <label for="dateofbirthID">Date of birth:</label>
                    <c:choose>
                        <c:when test="${user.DOB!=null}">
                            <input type="text" id="dateofbirthID" name="dateofbirth" value="${user.DOB}">
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="dateofbirthID" name="dateofbirth">
                        </c:otherwise>
                    </c:choose>

                    <label for="pictureurlID">Picture URL:</label>
                    <c:choose>
                        <c:when test="${user.pictureURL!=null}">
                            <input type="text" id="pictureurlID" name="pictureurl" value="${user.pictureURL}">
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="pictureurlID" name="pictureurl">
                        </c:otherwise>
                    </c:choose>

                    <label for="descID">Description:</label>
                    <c:choose>
                        <c:when test="${user.description!=null}">
                            <input type="text" id="descID" name="description" value="${user.description}">
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="descID" name="description">
                        </c:otherwise>
                    </c:choose>

                    <%--TODO: link up password in more secure way - talk to Steph re hashing--%>
                    <label for="pwordID">Password:</label>
                    <input type="text" id="pwordID" name="password">
                    <br>


                <div class="user">
                    <%--All of these are showing up on the profile webpage--%>
                    <p></p>


                        <input type="hidden" name="username" value="${user.username}">
                        <input type="submit" value="Edit User">
                    </form>


                    <form method="post" action="/deleteprofile">
                        <input type="hidden" name="username" value="${user.username}">
                        <input type="submit" value="Delete User">
                    </form>
                </div>
            </div>
    </body>
</html>
