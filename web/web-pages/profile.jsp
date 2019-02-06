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
<%@ include file="../WEB-INF/partial/_partial_header.jsp" %>

<script type="text/javascript">
    function switchImage() {
        var image = document.getElementById("imageToSwap");
        var dropdown = document.getElementById("dlist");
        image.src = "../default-photos-for-profile-page/" + dropdown.value;
    }
</script>
<body>
<%@ include file="../WEB-INF/partial/navbar.jsp" %>


<div class="container-fluid">

    <c:if test="${message!=null}">
        <div class="alert alert-warning alert-dismissible" id="error-message">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                ${message}
        </div>
    </c:if>

    <h1>${user.username}'s Account:</h1>
    <br>
    <br>

    <%--Below shows the user's selected image - either one of the default options, or if they've uploaded one, that one --%>
    <c:choose>
        <c:when test="${user.pictureURL != null && user.useDefaultImage == false}">
            <img id="imageToSwap" src="../Uploaded-Photos/${user.pictureURL}" width="225">
        </c:when>
    <c:otherwise>
    <img id="imageToSwap" src="../default-photos-for-profile-page/${user.defaultImage}" width="225">

    </c:otherwise>
    </c:choose>

    <%--this block is for choosing which of the default images the user prefers. Saves to db when they submit--%>
    <div style="margin-left: 250px">
        <h4>Choose default picture: </h4>

        <form method="POST" action="/editprofile">
            <select id="dlist" name="default-img" onchange="switchImage()">
                <c:choose>
                    <c:when test="${user.pictureURL != null && user.useDefaultImage == false}">
                        <option value="custom" selected>Custom Image</option>
                        <option value="Default.jpg">Default</option>
                        <option value="CloneTrooper.jpg">Clone Trooper</option>
                        <option value="Jigglypuff.jpg">Jigglypuff</option>
                        <option value="Yoda.jpg">Yoda</option>
                </c:when>
                    <c:when test="${user.defaultImage == 'CloneTrooper.jpg'}">
                        <option value="Default.jpg">Default</option>
                        <option value="CloneTrooper.jpg" selected>Clone Trooper</option>
                        <option value="Jigglypuff.jpg">Jigglypuff</option>
                        <option value="Yoda.jpg">Yoda</option>
                    </c:when>
                    <c:when test="${user.defaultImage == 'Jigglypuff.jpg'}">
                        <option value="Default.jpg">Default</option>
                        <option value="CloneTrooper.jpg">Clone Trooper</option>
                        <option value="Jigglypuff.jpg" selected>Jigglypuff</option>
                        <option value="Yoda.jpg">Yoda</option>
                    </c:when>
                    <c:when test="${user.defaultImage == 'Yoda.jpg'}">
                        <option value="Default.jpg">Default</option>
                        <option value="CloneTrooper.jpg">Clone Trooper</option>
                        <option value="Jigglypuff.jpg">Jigglypuff</option>
                        <option value="Yoda.jpg" selected>Yoda</option>
                    </c:when>
                    <c:otherwise>
                        <option value="Default.jpg" selected>Default</option>
                        <option value="CloneTrooper.jpg">Clone Trooper</option>
                        <option value="Jigglypuff.jpg">Jigglypuff</option>
                        <option value="Yoda.jpg">Yoda</option>
                    </c:otherwise>
                </c:choose>
            </select>
            <input type="submit" value="Choose this image">
        </form>

        <%--todo at the moment it's not possible to go back to the default images after uploading an image, might have to sort that out--%>

        <%--this is the form for uploading a custom avatar--%>
        <form method="POST" action="/Upload" enctype="multipart/form-data">
            <h4>Choose your own picture to upload: </h4>
            <input type="file" id="userPicture" name="userPicture" size="50" accept="image/png, image/jpeg">
            <br>
            <br>
            <input type="submit" value="Upload">
        </form>
        <br>
        <br>

        <%--a form to edit the other aspects of the user profile--%>
        <form action="/editprofile" method="post">
            <fieldset>
                <legend>Update user info: </legend>

            <%--shouldn't need a choose here, there is always a username and it can't be edited--%>
            <label for="unameID">Username:</label>
            <input type="text" id="unameID" name="username" value=${user.username} readonly>
            <br>

                <label for="rnameID">Real name:</label>
                <c:choose>
                    <c:when test="${user.realName!=null}">
                        <input type="text" id="rnameID" name="realname" value="${user.realName}">
                    </c:when>
                    <c:otherwise>
                        <input type="text" id="rnameID" name="realname">
                    </c:otherwise>
                </c:choose>
                <br>

            <label for="countryID">Country:</label>
            <c:choose>
                <c:when test="${user.country!=null}">
                    <input type="text" id="countryID" name="country" value=${user.country}>
                </c:when>
                <c:otherwise>
                    <input type="text" id="countryID" name="country">
                </c:otherwise>
            </c:choose>
            <br>

            <label for="dateofbirthID">Date of birth:</label>
            <c:choose>
                <c:when test="${user.DOB!=null}">
                    <%--<input type="text" id="dateofbirthID" name="dateofbirth" value="${user.DOB}">--%>
                    <input type="date" id="dateofbirthID" name="dateofbirth" value="${user.DOB}">
                </c:when>
                <c:otherwise>
                    <%--<input type="text" id="dateofbirthID" name="dateofbirth">--%>
                    <input type="date" name="dateofbirth" id="dateofbirthID">
                </c:otherwise>
            </c:choose>
            <br>

                <label for="emailID">Email:</label>
                <c:choose>
                    <c:when test="${user.email!=null}">
                        <%--<input type="text" id="dateofbirthID" name="dateofbirth" value="${user.DOB}">--%>
                        <input type="text" id="emailID" name="email" value="${user.email}">
                    </c:when>
                    <c:otherwise>
                        <%--<input type="text" id="dateofbirthID" name="dateofbirth">--%>
                        <input type="text" name="email" id="emailID">
                    </c:otherwise>
                </c:choose>
                <br>

            <label for="descID">Description:</label>
            <c:choose>
                <c:when test="${user.description!=null}">
                    <textarea rows="4" cols="80" id="descID" name="description">${user.description}</textarea>
                    <%--<input type="text" id="descID" name="description" value="${user.description}">--%>
                </c:when>
                <c:otherwise>
                    <textarea rows="4" cols="80" id="descID" name="description"></textarea>
                    <%--<input type="text" id="descID" name="description">--%>
                </c:otherwise>
            </c:choose>
            <br><br>


                <input type="hidden" name="username" value="${user.username}">
                <input type="submit" value="Edit User">
            </fieldset>
        </form>


        <%--a separate form for changing the password--%>
        <form method="post" action="/changePW">
            <fieldset><legend>Update Password:</legend>
                <input type="hidden" name="username" value="${user.username}">
            <label for="oldPassword">Current Password:</label>
            <input type="password" id="oldPassword" name="oldPassword"><br>
                <label for="newPassword">New Password:</label>
                <input type="password" id="newPassword" name="newPassword"><br>
            <input type="submit" value="submit">
            </fieldset>
        </form>

<%--todo add an "are you sure???" pop-up to this button--%>
        <form method="post" action="/deleteprofile">
            <fieldset><legend>Delete Account</legend>
            <input type="hidden" name="username" value="${user.username}">
            <input type="submit" value="Delete User">
            </fieldset>
        </form>
    </div>
</div>
</body>
</html>
