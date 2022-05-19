<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${action}</title>
</head>

<body>
    <c:url value='/show/group-all' var="allGroupURL"/>
    <jsp:include page="header.jsp" />
    <div>
        <c:choose>
            <c:when test="${action eq 'edit'}">
                <form action="<c:url value='/${action}/group/${group.getGroupId()}' />" method="POST">
                <label>Group Name<input type="text" name="groupName" required value="${group.getGroupName()}" placeholder="Enter Group Name"></label><br>
                <!--<label>Group Description<input type="text" name="groupDescription" required value="$--{group.getGroupDescription()}" placeholder="Enter Group Description"></label><br>-->
                <label>Group Description<textarea name="groupDescription"
                    cols="30"
                    rows="5"
                    maxlength="1000"
                    placeholder="Enter Group Description" required>${group.getGroupDescription()}</textarea></label><br>
            </c:when>
            <c:otherwise>
                <form action="<c:url value='/${action}/group' />" method="POST">
                <label>Group Name<input type="text" name="groupName" required value="" placeholder="Enter Group Name"></label><br>
                <!--<label>Group Description<input type="text" name="groupDescription" required value="" placeholder="Enter Group Description"></label><br>-->
                <label>Group Description<textarea name="groupDescription"
                    cols="30"
                    rows="5"
                    maxlength="1000"
                    placeholder="Enter Group Description" required></textarea></label><br>
            </c:otherwise>
        </c:choose>
        <input type="submit" value="${action}">
            <c:choose>
                <c:when test="${action eq 'edit'}">
                    <a href="<c:url value='/show/group?groupId=${group.getGroupId()}'/>">Cancel</a>
                </c:when>
                <c:otherwise>
                    <a href="<c:out value="${allGroupURL}"/>">Cancel</a>
                </c:otherwise>
            </c:choose>
        </form>
    </div>
    <jsp:include page="footer.jsp" />
</body>
</html>