

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%! String title = "Profile"; %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../WEB-INF/partial/_partial_header.jsp" %>
<title>${user.username}'s Profile</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>


<script type="text/javascript">
    function switchImage() {
        var image = document.getElementById("imageToSwap");
        var dropdown = document.getElementById("dlist");
        image.src = "images/default-photos-for-profile-page/" + dropdown.value;
    }
</script>

<style>

.form-control{
    width: 95%;
}


</style>
<body>
<%@ include file="../WEB-INF/partial/navbar.jsp" %>


<div class="container">

    <c:if test="${message!=null}">
        <div class="alert alert-warning alert-dismissible" id="error-message">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                ${message}
        </div>
    </c:if>

    <h1 class="py-3">${user.username}'s Account:</h1>



    <%--Below shows the user's selected image - either one of the default options, or if they've uploaded one, that one --%>
    <div class="row">
        <div class="col-md-6">
            <div class="transparent-white">
            <div id="image" class="text-center">
    <c:choose>
        <c:when test="${user.pictureURL != null && user.useDefaultImage == false}">
            <img id="imageToSwap" src="Uploaded-Photos/${user.pictureURL}" width="225" class="img-thumbnail">
        </c:when>
    <c:otherwise>
    <img id="imageToSwap" src="images/default-photos-for-profile-page/${user.defaultImage}" width="225" class="img-thumbnail">

    </c:otherwise>
    </c:choose>
            </div>

    <%--this block is for choosing which of the default images the user prefers. Saves to db when they submit--%>

        <form method="POST" action="editprofile">
            <fieldset>
                <legend>Choose default picture:</legend>
                <div class="form-group">
            <select id="dlist" name="default-img" class="form-control" onchange="switchImage()">
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
            </select> </div>
                <button class="btn btn-primary" type="submit" value="Use this image">Use this image</button>

            </fieldset>
        </form>


        <%--this is the form for uploading a custom avatar--%>
        <form method="POST" action="Upload" enctype="multipart/form-data">
            <fieldset>
                <legend>Choose your own picture to upload:</legend>
                <div class="form-group">
                <div class="custom-file">
            <input type="file" id="userPicture" name="userPicture" size="50" accept="image/png, image/jpeg" class="custom-file-input">
<label class="custom-file-label" for="userPicture">Choose File</label>
                </div></div>
                <button class="btn btn-primary" type="submit" value="Upload">Upload Image</button>

            </fieldset>
        </form>


            <hr>
            </div></div>


    <div class="form-group col-md-6">
        <div class="transparent-white">
        <%--a form to edit the other aspects of the user profile--%>
        <form action="editprofile" method="post">
            <fieldset>
                <legend>Update user info: </legend>

                <div class="form-group">
                <label for="unameID">Username:</label>
            <input type="text" id="unameID" name="username" value=${user.username} readonly class="form-control">
            </div>

                <div class="form-group">
                    <label for="email" >Email:</label>
                    <c:choose>
                        <c:when test="${user.email!=null && user.email != 'none'}">
                    <input type="email" id="email" maxlength="100" name="email" value='${user.email}' class="form-control">
                    </c:when>
                        <c:otherwise>
                            <input type="email" id="email" maxlength="100" name="email" class="form-control" required>
                            <small id="passwordInfo" class="form-text text-muted">Please enter an email address, otherwise we can't reset your password if you forget!</small>
                        </c:otherwise>
                    </c:choose>
                </div>


                <div class="form-group">
                <label for="rnameID">Real name:</label>
                <c:choose>
                    <c:when test="${user.realName!=null}">
                        <input type="text" id="rnameID" maxlength="50" name="realname" value="${user.realName}" class="form-control">
                    </c:when>
                    <c:otherwise>
                        <input type="text" id="rnameID" maxlength="50" name="realname" class="form-control">
                    </c:otherwise>
                </c:choose>
                </div>

                <div class="form-group">
            <label for="countryID">Country:</label>
            <c:choose>
                <c:when test="${user.country!=null}">
                    <input type="text" id="countryID" name="country" maxlength="100" value='${user.country}' class="form-control">
                </c:when>
                <c:otherwise>
                    <input type="text" id="countryID" name="country" maxlength="100" class="form-control">
                </c:otherwise>
            </c:choose>
                </div>

                    <div class="form-group">
            <label for="dateofbirthID">Date of birth:</label>
            <c:choose>
                <c:when test="${user.dateOfBirth!=null}">
                    <input type="date" id="dateofbirthID" name="dateofbirth" value="${user.dateOfBirth}" class="form-control">
                </c:when>
                <c:otherwise>
                    <input type="date" name="dateofbirth" id="dateofbirthID" class="form-control">
                </c:otherwise>
            </c:choose>
                    </div>


                        <div class="form-group">
            <label for="descID">Description:</label>
            <c:choose>
                <c:when test="${user.description!=null}">
                    <textarea rows="4" id="descID" name="description" maxlength="200" class="form-control">${user.description}</textarea>
                </c:when>
                <c:otherwise>
                    <textarea rows="4" id="descID" name="description" maxlength="200" class="form-control"></textarea>
                </c:otherwise>
            </c:choose>
                        </div>


                <input type="hidden" name="username" value="${user.username}">
                <button class="btn btn-primary" type="submit" value="Edit User">Edit User Info</button>
            </fieldset>
        </form><br>

<hr>
        <%--a separate form for changing the password--%>
        <form method="post" action="changePW">
            <fieldset><legend>Update Password:</legend>
                <input type="hidden" name="username" value="${user.username}" >
                <div class="form-group">
                    <label for="oldPassword">Current Password:</label>
            <input type="password" id="oldPassword" name="oldPassword" class="form-control">
                </div>
                    <div class="form-group">
                        <label for="newPassword">New Password:</label>
                <input type="password" id="newPassword" name="newPassword" class="form-control">
                    </div>
                <button class="btn btn-primary" type="submit" value="submit">Submit</button>
            </fieldset>
        </form><br>
<hr>
        <form method="post" action="deleteuser">
            <fieldset><legend>Delete Account</legend>
                <div class="form-group">
                    <input type="hidden" name="username" value="${user.username}">
                    <input type="hidden" name="admin" value="user">
                    <button class="btn btn-primary" type="submit" value="Delete User" onclick="return confirm('Are you sure you want to delete your account?')">Delete User</button>

                </div>
            </fieldset>
        </form>
        </div>
    </div>
</div>
</body>
</html>
