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
                <%--All of these are showing up on the profile webpage--%>
                <p>${user.username}'s Account: </p>

                    <%--todo: copied from Yun's code--%>
                    ask Yun or Steph for help!
                    <form method="post" action=#>
                        <input type="hidden" name="username" value="${user.username}">
                        <input type="submit" value="Edit User">
                    </form>


                    <form method="post" action="#">
                        <input type="hidden" name="username" value="${user.username}">
                        <input type="submit" value="Delete User">
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

                <% if (request.getAttribute("country")!= null){%>
                <form action="/Upload" method="post">
                <%-- figure out what to do here --%>
                </form>
                <%}else{%>
                <form action="/editprofile" method="post">
                <input type="hidden" name="country" value="${user.country}"></form>
                <%}%>

                <% if (request.getAttribute("realname")!= null){%>
                <form action="/Upload" method="post">
                    <%-- figure out what to do here --%>
                </form>
                <%}else{%>
                <form action="/editprofile" method="post">
                    <input type="hidden" name="realname" value="${user.realName}"></form>
                <%}%>

                <% if (request.getAttribute("description")!= null){%>
                <form action="/Upload" method="post">
                    <%-- figure out what to do here --%>
                </form>
                <%}else{%>
                <form action="/editprofile" method="post">
                    <input type="hidden" name="desc" value="${user.desc}"></form>
                <%}%>

                <% if (request.getAttribute("imageURL")!= null){%>
                <form action="/Upload" method="post">
                    <%-- figure out what to do here --%>
                </form>
                <%}else{%>
                <form action="/editprofile" method="post">
                    <input type="hidden" name="imageURL" value="${user.pictureURL}"></form>
                <%}%>

                <% if (request.getAttribute("dateofbirth")!= null){%>
                <form action="/Upload" method="post">
                    <%-- figure out what to do here --%>
                </form>
                <%}else{%>
                <form action="/editprofile" method="post">
                    <input type="hidden" name="dateofbirth" value="${user.DOB}"></form>
                <%}%>

                <% if (request.getAttribute("username")!= null){%>
                <form action="/Upload" method="post">
                    <%-- figure out what to do here --%>
                </form>
                <%}else{%>
                <form action="/editprofile" method="post">
                    <input type="hidden" name="username" value="${user.username}"></form>
                <%}%>


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
                    <% if (request.getAttribute("username") != null){
                    %>
                    <input type="text" id="unameID" name="username" value=${user.username}>
                    <br>
                    <%}else{ %>
                    <input type="text" id="unameID" name="username">
                    <br>
                    <%  } %>

                    <label for="countryID">Country:</label>
                    <% if (request.getAttribute("country") != null){
                    %>
                    <input type="text" id="countryID" name="country" value=${user.country}>
                    <br>
                    <%}else{ %>
                    <input type="text" id="countryID" name="country">
                    <br>
                    <%  } %>

                    <label for="rnameID">Real name:</label>
                    <% if (request.getAttribute("realname") != null){
                    %>
                    <input type="text" id="rnameID" name="realname" value="${user.realName}">
                    <br>
                    <%}else{ %>
                    <input type="text" id="rnameID" name="realname">
                    <br>
                    <%  } %>

                    <label for="dateofbirthID">Date of birth:</label>
                    <% if (request.getAttribute("dateofbirth") != null){
                    %>
                    <input type="text" id="dateofbirthID" name="dateofbirth" value="${user.DOB}">
                    <br>
                    <%}else{ %>
                    <input type="text" id="dateofbirthID" name="dateofbirth">
                    <br>
                    <%  } %>

                    <%--TODO: Make this more useable--%>
                    <label for="pictureurlID">Picture URL:</label>
                    <% if (request.getAttribute("pictureurl") != null){
                    %>
                    <input type="text" id="pictureurlID" name="pictureurl" value="${user.pictureURL}">
                    <br>
                    <%}else{ %>
                    <input type="text" id="pictureurlID" name="pictureurl">
                    <br>
                    <%  } %>

                    <%--TODO: Make this more useable--%>
                    <label for="descID">Description:</label>
                    <% if (request.getAttribute("description") != null){
                    %>
                    <input type="text" id="descID" name="description" value="${user.desc}">
                    <br>
                    <%}else{ %>
                    <input type="text" id="descID" name="description">
                    <br>
                    <%  } %>

                    <%--TODO: link up password in more secure way - talk to Steph re hashing--%>
                    <label for="pwordID">Password:</label>
                    <input type="text" id="pwordID" name="password">
                    <br>
                </form>

                <%--TODO: Link this up to a servlet--%>
                <input type="submit" value="submit">
                <br>
                <br>
                <%--TODO: Link this up to a servlet--%>
                <input type="submit" value="submit">
                <%--TODO: Make this webpage RESPONSIVE--%>

            </div>
        </div>
    </body>
</html>
