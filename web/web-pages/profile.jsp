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
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

        <title>Profile</title>
        <script type="text/javascript">
            function switchImage() {
                var image = document.getElementById("imageToSwap");
                var dropdown = document.getElementById("dlist");
                image.src = dropdown.value;
            }
        </script>
    </head>
    <body>
        <%@ include file="navbar.jsp" %>


        <div class="container-fluid">

            <div class="user">
                <p>${user.username}'s Account: </p>
                <%--TODO: Grab JSTL and put it into forms?????--%>
                <p>${user.country}</p>
                <p>${user.real_name}</p>
                <p>${user.description}</p>
                <p>${user.date_of_birth}</p>
                <p>${user.image}</p>


                <%--todo --%>
                <form method="post" action=#>
                    <input type="hidden" name="articleID" value="${article.ID}">
                    <input type="submit" value="Edit Article">
                </form>


                <form method="post" action="#">
                    <input type="hidden" name="articleID" value="${article.ID}">
                    <input type="submit" value="Delete Article">
                </form>

            </div>
            <h1>User Account</h1>
            <br>
            <br>
            <%--Adding form for uploading default user pic (the one the user sees when they click on the profile webpage)--%>
            <%--Below is the default user pic link --%>

            <img id="imageToSwap" src="../default-photos-for-profile-page/Default.jpg" width="225">

            <div style="margin-left: 250px">
                <h4>Choose default picture: </h4>
                <%--TODO: What servlet are we linking to here?? Will be for uploading own photo--%>
                <form method="POST" action= "/Upload" enctype="multipart/form-data">
                    <select id="dlist" onchange="switchImage()">
                        <option value="../default-photos-for-profile-page/CloneTrooper.jpg">Clone Trooper</option>
                        <option value="../default-photos-for-profile-page/Jigglypuff.jpg">Jigglypuff</option>
                        <option value="../default-photos-for-profile-page/Yoda.jpg">Yoda</option>
                    </select>
                    <h4>Choose your own picture to upload: </h4>
                    <input type="file" id ="userPicture" name="userPicture" size="50" accept="image/png, image/jpeg">
                    <br>
                    <br>
                    <input type="submit" value="Upload">
                    <br>
                    <br>

                    <%--TODO: Create forms for: description--%>
                    <label for="unameID">Username:</label>
                    <input type="text" id="unameID" name="username">
                    <br>
                    <label for="pwordID">Password:</label>
                    <input type="text" id="pwordID" name="password">
                    <br>
                    <label for="countryID">Country:</label>
                    <input type="text" id="countryID" name="country">
                    <br>
                    <label for="rnameID">Real name:</label>
                    <input type="text" id="rnameID" name="realname">
                    <br>
                    <label for="dateofbirthID">Date of birth:</label>
                    <input type="text" id="dateofbirthID" name="dateofbirth">
                    <br>
                    <%--TODO: Make this more useable--%>
                    <label for="pictureurlID">Picture URL:</label>
                    <input type="text" id="pictureurlID" name="picture url">
                    <br>
                </form>

                <%--TODO: Link this up to a servlet--%>
                <input type="submit" value="submit">


                <label for="profile_text"> About Text</label>
                <textarea id="profile_text"
                          name="profile_text_area"
                          rows="4"
                          cols="40">
                </textarea>
                <br>
                <br>
                <%--TODO: Link this up to a servlet--%>
                <input type="submit" value="submit">
                <%--TODO: Make this webpage RESPONSIVE--%>


                <%--In profile.jsp, have && etc EL user. ask Yun.--%>
                <%--Set into form inputs - one called placeholder = $[user.realname} PASSWORD--%>
                <%--value = if submit, value already was... Not great for updating!!--%>
                <%--But if had value, would update every value in database every time--%>
                <%--VALUE WINS--%>

            </div>
        </div>
    </body>
</html>
