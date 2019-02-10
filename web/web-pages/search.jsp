<%--
  Created by IntelliJ IDEA.
  User: smh30
  Date: 29/01/2019
  Time: 12:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Search</title>
        <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">--%>

        <%--<!-- jQuery library -->--%>
        <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>--%>

        <%--<!-- Latest compiled JavaScript -->--%>
        <%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>--%>
        <%@ include file="../WEB-INF/partial/_partial_header.jsp" %>
        <%--library for icon--%>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
        <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
        <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
        <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>


        <style type="text/css">
            /*body{*/
                /*background-image: url("/web-pages/images/final333333.jpg") ;*/
                /*background-repeat: repeat,repeat;*/
                /*background-color: #cccccc;*/
            /*}*/

            /*body{*/
                /*background-image: url("/images/sf-lesssky.jpg") ;*/
                /*background-repeat: no-repeat;*/
                /*background-color: #cccccc;*/
                /*height: 500px;*/
                /*background-position: center;*/
                /*background-size: cover;*/
                /*background-attachment: fixed;*/
            /*}*/

            .container{
                margin-top: 12%;
                padding: 15px;
               border: 2px solid #666666;
                border-radius: 12px;
                background-color: white;
            }

        </style>
    </head>
    <body>
        <%@ include file="../WEB-INF/partial/navbar.jsp" %>


        <div class="container">



            <form method="get" action="home">
                <legend>Search the Articles Database</legend>

                <div class="form-group row">
                <label for="author" class="col-sm-4 col-md-2 col-form-label">Search by User: </label>
                <div class="col"><input type="text" id="author" name="author" class="form-control">
                </div></div>

                    <div class="form-group row">
                <label for="title" class="col-sm-4 col-md-2 col-form-label">Search by Title: </label>
                        <div class="col"><input type="text" id="title" name="title" class="form-control">
                        </div></div>

                        <div class="form-group row">
                <label for="date" class="col-sm-4 col-md-2 col-form-label">Search by Date: </label>
                            <div class="col"><input type="date" id="date" name="date" class="form-control">
                            </div></div>

                            <button class="btn btn-primary" type="submit" value="Search">Search</button>
            </form>

        </div>
        
    </body>
</html>
