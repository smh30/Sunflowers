<%@ attribute name="list" type="java.util.List" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



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
                        <div class="card bg-light px-3 pt-3 pb-1">

                            <div id="username" class="media-heading">
                                <h4 class="card-title text-uppercase ßreviews">
                                    <a href="#" onclick="getAuthorInfo('${childComment.commentAuthor.username}')"><strong>${childComment.commentAuthor.username} :</strong></a></h4>


                            <div id="timestamp" class="media-date text-uppercase reviews list-inline">

                                <p class="dd">${fn:substring(childComment.timeString,0,16)}</p>
                            </div>

                            <div>

                                <div class="media-comment" id="comment${childComment.commentID}">
                                    <c:out value="${childComment.commentContent}"/>
                                </div>

                                    <%--show the reply button when the one has login--%>
                                <c:if test="${sessionScope.username != null}">
                                    <a class="btn btn-info btn-circle text-uppercase"
                                       id="reply-reply-btn-${childComment.commentID}"
                                       onclick="openForm(${childComment.commentID})"
                                       href="#commentDiv${childComment.commentID}"><span
                                            class="glyphicon glyphicon-share-alt"></span>
                                        Reply</a>

                                    <div class="form-popup" id="myForm-${childComment.commentID}" style="display: none">
                                        <form method="post" action="addNestedComment" class="form-container">
                                            <input type="hidden" name="articleID" value="${article.ID}">
                                            <input type="hidden" name="commentID" value="${childComment.commentID}">
                                            <textarea placeholder="comment here..." maxlength="1000" name="content"
                                                      class="form-control" rows="4"
                                                      id="content"></textarea>
                                            <button type="submit" id="childcommentsub" class="btn btn-circle">Submit</button>
                                            <button type="button" id="childrencommentclose" class="btn cancel btn-circle"
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

                                        <button id="deletecommetnbtn" class="btn btn-info btn-circle text-uppercase"
                                                type="submit"
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
                    </div>
                </li>
            </ul>
        </div>
    </c:forEach>
</c:if>