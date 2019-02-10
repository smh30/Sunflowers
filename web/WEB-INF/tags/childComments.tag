<%@ attribute name="list" type="java.util.List" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>

    .container-comment-pool{
        width: 100%;
        height: auto;
    }
    .media .media-object { max-width: 120px; }
    .media-body {
        position: relative;
        width: 100% ;
    }
    #commentDiv${childComment.commentID}{
        width: 100%;
    }
    .media-date {
        position: relative;

    }
    .media-date li { padding: 0; }
    .media-date li:first-child:before { content: ''; }
    .media-date li:before {
        content: '.';
        margin-left: -2px;
        margin-right: 2px;
    }
    .media-comment { margin-bottom: 20px; }
    .media-replied {
        margin: 0 0 20px 20px;
    }
    .media-replied .media-heading { padding-left: 6px; }

.card .bg-light{
    background-color: whitesmoke;
    border: none;
}

    .btn-circle {
        font-weight: bold;
        font-size: 12px;
        padding: 6px 15px;
        border-radius: 20px;
        color: white;
    }
    .btn-circle span { padding-right: 6px; }


    input[type="file"]{
        z-index: 999;
        line-height: 0;
        font-size: 0;
        position: absolute;
        opacity: 0;
        filter: alpha(opacity = 0);-ms-filter: "alpha(opacity=0)";
        margin: 0;
        padding:0;
        left:0;
    }

    .well{
        border:none;
    }

    #deletecommetnbtn{
        margin-top: 10px;
    }



    .custom-input-file:hover .uploadPhoto { display: block; }
</style>

<c:if test="${!empty list}">
    <c:forEach var="childComment" items="${list}">
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

        <div class="container-comment-pool">



        <div class="tab-pane active" id="comments-logout">
            <ul class="media-list">

                <li class="media">

                    <a class="pull-left" href="#">
                        <img class="media-object img-circle" src="https://svgsilh.com/svg/1699635.svg" alt="profile">
                    </a>

                    <div class="media-body" id="commentDiv${childComment.commentID}">
                        <div class="card bg-light p-3">

                            <div class="media-heading">
                            <h4 class="card-title text-uppercase ßreviews"><strong>${childComment.commentAuthor.username} :</strong></h4>

                               <span>
                            <ul class="media-date text-uppercase reviews list-inline">
                                <li class="dd">${fn:substring(childComment.timeString,0,16)}</li>
                            </ul>
                               </span>
                            </div>

                            <div>

                                <div class="media-comment" id="comment${childComment.commentID}">
                                    <c:out value="${childComment.commentContent}"/>
                                </div>

                                    <%--show the reply button when the one has login--%>
                                <c:if test="${sessionScope.username != null}">
                                    <a class="btn btn-info btn-circle text-uppercase"
                                       id="reply-btn-${childComment.commentID}"
                                       onclick="openForm(${childComment.commentID})" href="#commentDiv${childComment.commentID}"><span
                                            class="glyphicon glyphicon-share-alt"></span>
                                        Reply</a>

                                    <div class="form-popup" id="myForm-${childComment.commentID}" style="display: none">
                                        <form method="post" action="addNestedComment" class="form-container">
                                            <input type="hidden" name="articleID" value="${article.ID}">
                                            <input type="hidden" name="commentID" value="${childComment.commentID}">
                                            <textarea placeholder="comment here..." name="content" class="form-control" rows="4"
                                                      id="content"></textarea>
                                            <button type="submit" class="btn btn-primary">Submit</button>
                                            <button type="button" class="btn cancel btn-warning"
                                                    onclick="closeForm(${childComment.commentID})">Close
                                            </button>
                                        </form>
                                    </div>

                                </c:if>


                                    <%--show delete button when the one can delete it--%>
                                <c:if test="${childComment.commentAuthor.username == sessionScope.username || article.author.username == sessionScope.username }">
                                    <form method="get" action="deletecomment">
                                        <input type="hidden" name="commentID" value="${childComment.commentID}">
                                        <input type="hidden" name="articleID" value="${article.ID}">

                                        <button id="deletecommetnbtn" class="btn btn-info btn-circle text-uppercase" type="submit"
                                                value="Delete Comment"><i class='fas fa-meh'></i> Delete Comment
                                        </button>
                                    </form>
                                </c:if>

                                    <%-- collapse button--%>
                                <a class="btn btn-warning btn-circle text-uppercase" data-toggle="collapse"
                                   href="#reply${childComment.commentID}"
                                   data-target="#reply${childComment.commentID}"><span
                                        class="glyphicon glyphicon-comment"></span> ${childComment.children.size()}
                                    comments</a>

                                <div class="collapse" id="reply${childComment.commentID}">
                                    <ul class="media-list">

                                        <li class="media media-replied">
                                            <myTags:childComments list="${childComment.children}"/>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>


    </c:forEach>

</c:if>