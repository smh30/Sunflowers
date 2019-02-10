<%@ attribute name="list" type="java.util.List" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${!empty list}">
        <c:forEach var="comment" items="${list}">
            <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--<c:set var="timestamp" value="${fn:substring(comment.timeString,0,16)}"/>--%>
            <div style="margin-left: 30px;"><c:out value="At ${fn:substring(comment.timeString,0,16)}, ${comment.commentAuthor.username} wrote:"/><br>
                    <c:out value="${comment.commentContent}"/><br>




                        <c:if test="${comment.commentAuthor.username == sessionScope.username || article.author.username == sessionScope.username }">
                            <form method="get" action="deletecomment">
                                <input type="hidden" name="commentID" value="${comment.commentID}">
                                <input type="hidden" name="articleID" value="${article.ID}">
                                <button class="btn btn-primary" type="submit" value="Delete Comment"><i class='fas fa-meh'></i> Delete Comment</button>
                            </form>
                        </c:if>
                        <c:if test="${sessionScope.username != null}">


                            <button id="reply-btn-${comment.commentID}" class="open-button btn btn-primary" onclick="openForm(${comment.commentID})">Reply
                            </button>
                            <div class="form-popup" id="myForm-${comment.commentID}" style="display: none">
                                <form method="post" action="addNestedComment" class="form-container">
                                    <input type="hidden" name="articleID" value="${article.ID}">
                                    <input type="hidden" name="commentID" value="${comment.commentID}">

                                    <%--<label for="content"><b>Reply comment:</b></label>--%>
                                    <%--<input type="text" id="content" placeholder="comment here..." name="content">--%>
                                    <textarea placeholder="comment here..." class="form-control"  rows="4" id="content" name="content"></textarea>

                                    <button type="submit" class="btn btn-primary">Submit</button>
                                    <button type="button" class="btn cancel btn-warning" onclick="closeForm(${comment.commentID})">Close</button>
                                </form>
                            </div>
                        </c:if>




            <myTags:childComments list="${comment.children}"/>
            </div>
        </c:forEach>

</c:if>