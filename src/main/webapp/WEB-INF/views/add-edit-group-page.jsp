<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${action}</title>
</head>
<body>
    <c:choose>
        <c:when test="${action eq 'edit'}">
            <form action="<c:url value='/${action}/group/${group.getGroupId()}' />" method="POST">
            <label>Group Name</label><input type="text" name="firstName" required value="${group.getGroupName()}" placeholder="Enter Group Name">
        </c:when>
        <c:otherwise>
            <form action="<c:url value='/${action}/group' />" method="POST">
            <label>Group Name</label><input type="text" name="firstName" required value="" placeholder="Enter Group Name">
        </c:otherwise>
    </c:choose>
    <input type="submit" value="${action}">
</form>
</body>
</html>