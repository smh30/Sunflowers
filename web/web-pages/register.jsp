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

        <%@ include file="../WEB-INF/partial/_partial_header.jsp" %>

        <%--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>

        <%--Script below is for reCAPTCHA--%>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>

        <script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit"
                async defer>
        </script>


<%--this last jquery import is the non-slim version, need for ajax--%>
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"
                integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
                crossorigin="anonymous" type="text/javascript"></script>


        <style>
            .red {
                border: 2px solid red;
            }
        </style>


        <script type="text/javascript">

                function checkName(nameCheck) {

                        $.ajax({
                            url: "checkname",
                            type: "POST",
                            data: {name: nameCheck},
                            success: function (msg) {
                                console.log(msg);
                                //do the thing to show if it's good or not
                                if(msg ==="true") {
                                    console.log("the message was true");
                                    // $("#username").css({border: "2px solid red"});
                                    // $('#nameFail').addClass("red");
                                    // $('#nameFail').text("Please choose a different username");

                                    $('#nameFail').html("<div class=\"alert alert-warning alert-dismissible\" id=\"error-message\" >" +
                                        "<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>Please choose a different username</div>")

                                    //$('#nameFail').toggle(true);
                                }
                                // } else {
                                //     $('#nameFail').removeClass("red");
                                //     $('#nameFail').text("");
                                // }
                            }
                        })
                    }
        </script>




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

        <div id="nameFail">
        </div>

        <form method="post" action="register">
            <%--Below line for reCAPTCHA--%>


            <label for="username">username:</label>
                <%--the onchange means that when the box loses focus it does the thing --%>
            <input type="text" id="username" name="username" onchange="checkName(this.value)">

            <br>
            <label for="password">password:</label>
            <input type="password" id="password" name="password">
            <br>
            <div class="g-recaptcha" data-sitekey="6Lc52o4UAAAAAF2qwLx_jR66r2nUDGMTz9FSM2-N"
                 data-callback="recaptchaCallback"></div>

            <%--Disabled input button--%>

            <button type="submit" class="btn btn-default disabled" id="btnSubmit">Submit</button>
        </form>

        <script type="text/javascript">
            function recaptchaCallback() {
                var btnSubmit = document.getElementById("btnSubmit");
                //TODO: Play with classes to get border back
                if ( btnSubmit.classList.contains("disabled") ) {
                    btnSubmit.classList.remove("disabled");
                    btnSubmitclassList.add("show");
                }
            }
        </script>

    </body>
</html>
