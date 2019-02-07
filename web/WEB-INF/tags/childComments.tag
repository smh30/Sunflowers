<%@ attribute name="list" type="java.util.List" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${!empty list}">
    <ul>
        <c:forEach var="childComment" items="${list}">
            <li><c:out value="${childComment.commentContent}"/></li>
            <myTags:childComments list="${childComment.children}"/>
        </c:forEach>
    </ul>
</c:if>