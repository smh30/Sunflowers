<%@ attribute name="list" type="java.util.List" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${!empty list}">
        <c:forEach var="childComment" items="${list}">
            <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

            <div style="margin-left: 30px;"><c:out value="At ${childComment.timestamp}, ${childComment.commentAuthor.username} wrote:"/><br>
                    <c:out value="${childComment.commentContent}"/><br>



                        <c:if test="${childComment.commentAuthor.username == sessionScope.username || article.author.username == sessionScope.username }">
                            <form method="get" action="deletecomment">
                                <input type="hidden" name="commentID" value="${childComment.commentID}">
                                <input type="hidden" name="articleID" value="${article.ID}">
                                <button class="btn btn-primary" type="submit" value="Delete Comment"><i class='fas fa-meh'></i> Delete Comment</button>
                            </form>
                        </c:if>
                        <c:if test="${sessionScope.username != null}">


                            <button id="reply-btn-${childComment.commentID}" class="open-button btn btn-primary" onclick="openForm(${childComment.commentID})">Reply
                            </button>
                            <div class="form-popup" id="myForm-${childComment.commentID}" style="display: none">
                                <form method="post" action="addNestedComment" class="form-container">
                                    <input type="hidden" name="articleID" value="${article.ID}">
                                    <input type="hidden" name="commentID" value="${childComment.commentID}">

                                    <label for="content"><b>Reply comment:</b></label>
                                    <input type="text" id="content" placeholder="comment here..." name="content">

                                    <button type="submit" class="btn btn-primary">Submit</button>
                                    <button type="button" class="btn cancel btn-warning" onclick="closeForm(${childComment.commentID})">Close</button>
                                </form>
                            </div>
                        </c:if>




            <myTags:childComments list="${childComment.children}"/>
            </div>
        </c:forEach>

</c:if>