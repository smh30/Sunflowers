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
            <h1>User Account</h1>
            <br>
            <br>
            <%--Adding form for uploading default user pic (the one the user sees when they click on the profile webpage)--%>
            <%--Below is the default user pic link --%>

            <img id="imageToSwap" src="../default-photos-for-profile-page/Default.jpg" width="225">

            <div style="margin-left: 250px">
                <h4>Choose default picture: </h4>
                <%--TODO: What servlet are we linking to here?? Will be for uploading own photo--%>
                <form action="UploadProfilePictureServlet" method="post" enctype="multipart/form-data">
                    <select id="dlist" onchange="switchImage()">
                        <option value="../default-photos-for-profile-page/CloneTrooper.jpg">Clone Trooper</option>
                        <option value="../default-photos-for-profile-page/Jigglypuff.jpg">Jigglypuff</option>
                        <option value="../default-photos-for-profile-page/Yoda.jpg">Yoda</option>
                    </select>
                    <h4>Choose your own picture to upload: </h4>
                    <input type="file" name="userPicture" size="50"/>
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
                </form>
                <label for="profile_text"> About Text</label>
                <textarea id="profile_text"
                          name="profile_text_area"
                          rows="4"
                          cols="40">

                </textarea>

                <%--TODO: Make this webpage RESPONSIVE--%>

            </div>
        </div>
    </body>
</html>
