<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>

    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css"/>

    <%@ include file="../WEB-INF/partial/_partial_header.jsp" %>

    <%--Script below is for reCAPTCHA--%>
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>

    <script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit"
            async defer>
    </script>

    <%--library for icon--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css'
          integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css'
          integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css'
          integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css'
          integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>


    <%--this last jquery import is the non-slim version, need for ajax--%>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous" type="text/javascript"></script>

    <script type="text/javascript">

        function checkName(nameCheck) {

            $.ajax({
                url: "checkname",
                type: "POST",
                data: {name: nameCheck},
                success: function (msg) {
                    console.log(msg);
                    if (msg === "true") {
                        $('#nameFail').html("<div class=\"alert alert-warning alert-dismissible\" id=\"error-message\" >" +
                            "<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>Please choose a different username</div>")

                    }
                }
            })
        }
    </script>

    <style>


        .container {

            padding: 6px;
            margin: auto;
            border: 2px solid #666666;
            border-radius: 12px;
            background-color: white;

            position: center;
            margin-top: 8%;

        }

        .theRform {
            margin: 20px 15px 25px 15px;
            padding: 20px;
            border: 2px solid #666666;
            border-radius: 12px;
            background-color: whitesmoke;
        }

        .form-control:hover {
            background-color: #8a8a8a;
            color: black;
        }


    </style>

</head>

<body>
<%@ include file="../WEB-INF/partial/navbar.jsp" %>
<div class="container">

    <div id="nameFail">
    </div>

    <h1>Register</h1>


    <div id="reg-form">
        <form class="theRform" method="post" action="register">
            <%--Below line for reCAPTCHA--%>

            <div class="form-group">
                <label class="label-txt" for="username">Username: </label>
                <%--the onchange means that when the box loses focus it does the thing --%>
                <input type="text" id="username" name="username" maxlength="30" pattern="^\S{4,30}" onchange="checkName(this.value)" class="form-control">
            </div>

            <div class="form-group">
                <label for="password">Password: </label>
                <input type="password" id="password" name="password" minlength="8" pattern="{8,50}" maxlength="50" class="form-control">
                <small id="passwordInfo" class="form-text text-muted">Minimum 8 characters</small>
            </div>
            <div class="g-recaptcha" data-sitekey="6Lc52o4UAAAAAF2qwLx_jR66r2nUDGMTz9FSM2-N"
                 data-callback="recaptchaCallback"></div>

            <%--Disabled input button--%>
            <button type="submit" class="btn btn-primary d-none" id="btnSubmit">Submit</button>
        </form>

        <script type="text/javascript">
            function recaptchaCallback() {
                var btnSubmit = document.getElementById("btnSubmit");
                //TODO: Play with classes to get border back
                if (btnSubmit.classList.contains("d-none")) {
                    // btnSubmit.classList.remove("disabled");
                    btnSubmit.classList.remove("d-none");
                }
            }
        </script>
    </div>
</div>
</body>
</html>
