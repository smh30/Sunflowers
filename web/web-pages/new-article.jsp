<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>

        <title>Add New Article</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <%@ include file="../WEB-INF/partial/_partial_header.jsp" %>
        <script src="ckeditor/ckeditor.js"></script>
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css'
              integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ'
              crossorigin='anonymous'>


        <style type="text/css">


            #submitBtn {
                margin: 10px;
            }

            #choose-date {
                margin-top: 6px;
                border: 2px solid #666666;
                border-radius: 12px;
                background-color: whitesmoke;
            }

            .cke_reset {
                padding: 8px;
            }


        </style>


    </head>
    <body>
        <%@ include file="../WEB-INF/partial/navbar.jsp" %>

        <%--a message will appear here if creating an article failed for some reason such as too long title/body--%>
        <c:if test="${message != null}">
            <div class="alert alert-warning alert-dismissible" id="error-message">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a> ${message}
            </div>
        </c:if>
        <div class="container">
            <%--submits to a different servlet depending on whether it's a new article or an edit--%>
            <c:choose>
            <c:when test="${new_article != null}">
            <form action="new-article" method="post">
                </c:when>
                <c:otherwise>
                <form action="edit-article" method="post">
                    <input type="hidden" name="articleID" value="${article.ID}">
                    </c:otherwise>
                    </c:choose>

                    <div class="container text-center py-2" id="choose-date">

                        <label for="date">Optionally, select a publication date for your post:</label> <input
                            type="date" id="date" name="pub_date">
                    </div>

                    <div class="container text-center">
                        <br> <label id="titleID" for="title">Article Title Here:</label> <c:choose> <c:when
                            test="${title !=null}"> <input type="text" name="article_title" class="form-control"
                                                           id="title" maxlength="200" value='${title}'> </c:when>
                        <c:otherwise> <input type="text" name="article_title" class="form-control" id="title"
                                             maxlength="200" placeholder="Your new article title..."> </c:otherwise>
                    </c:choose>
                    </div>

                    <div class="container text-center">
                        <br> <label id="label2" for="content">Article Content Here:</label> <c:choose> <c:when
                            test="${content != null}">
                <textarea class="form-control" name="article_content" id="content" maxlength="10000"
                          rows="30">${content}</textarea> </c:when> <c:when test="${'article-content' != null}">
                    <textarea class="form-control" name="article_content" id="content" maxlength="10000"
                              rows="30">${article_content}</textarea> </c:when> <c:otherwise>
                    <textarea class="form-control" name="article_content" id="content" rows="30" maxlength="10000"
                              placeholder="Content here..."></textarea> </c:otherwise> </c:choose>
                    </div>


                    <div class="container text-right">
                        <button id="submitBtn" class="btn-primary" type="submit">Submit</button>
                    </div>
                </form>

                <script>
                    CKEDITOR.replace('content');
                </script>
        </div>
    </body>
</html>

