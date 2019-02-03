<%--
  Created by IntelliJ IDEA.
  User: yab2
  Date: 25/01/2019
  Time: 3:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <!-- I've linked in the Bootstrap things so that the nav will work -->
        <!-- NOTE: i got these from the w3 website rather than the ones from our lab, so if there's a problem they might need to be changed. The navbar didn't work properly if I used the other ones.
        <!-- Latest compiled and minified CSS -->
        <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">--%>

        <%--<!-- jQuery library -->--%>
        <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>--%>

        <%--<!-- Latest compiled JavaScript -->--%>
        <%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>--%>

        <%--Script below is for reCAPTCHA--%>


        <script src="https://www.google.com/recaptcha/api.js" async defer></script>

        <script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit"
                async defer>
        </script>

        <%@ include file="../WEB-INF/partial/_partial_header.jsp" %>

    </head>

    <body>
        <!-- i've included the navbar here so that i can have links to test the login etc with - steph -->
        <%@ include file="../WEB-INF/partial/navbar.jsp" %>
        <!--just a basic form so i can test the servlet -->
        <!-- if the username is already taken, print a message/or show a popup??-->
        <% if (request.getAttribute("message") != null) {
        %>
        ${message}
        <%
            } %>

        <form method="post" action="register">
            <%--Below line for reCAPTCHA--%>

            <button type="submit" class="btn btn-default hidden" id="btnSubmit">Submit</button>

            <label for="username">username:</label>
            <input type="text" id="username" name="username">
            <br>
            <label for="password">password:</label>
            <input type="password" id="password" name="password">
            <br>
                <div class="g-recaptcha" data-sitekey="6Lc52o4UAAAAAF2qwLx_jR66r2nUDGMTz9FSM2-N"
                     data-callback="recaptchaCallback"></div>
            <%--In here goes reCAPTCHA--%>

            <input type="submit" value="submit">
        </form>



        <script type="text/javascript">
            function recaptchaCallback() {
                var btnSubmit = document.getElementById("btnSubmit");

                if (btnSubmit.classList.contains("hidden")) {
                    btnSubmit.classList.remove("hidden");
                    btnSubmitclassList.add("show");
                }
            }
        </script>
    </body>
</html>
