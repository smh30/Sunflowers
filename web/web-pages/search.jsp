<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Search</title>
        <%@ include file="../WEB-INF/partial/_partial_header.jsp" %>
        <%--library for icon--%>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
        <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
        <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
        <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>


        <style type="text/css">
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
