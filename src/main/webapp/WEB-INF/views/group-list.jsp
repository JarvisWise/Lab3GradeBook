<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Groups</title>
</head>
<body>
<c:if test="${userType eq 'teacher'}">
    <a href="<c:url value='/redirect/add/group' />">Add new group</a>
</c:if>
<table>
    <tr>
        <th>ID</th>
        <th>Group Name</th>
        <th>More...</th>
        <c:if test="${userType eq 'teacher'}">
            <th>Edit</th>
            <th>Delete</th>
        </c:if>
    </tr>
    <c:forEach var="group" items="${groups}">
    <tr>
        <td>${group.getGroupId()}</td>
        <td>${group.getGroupName()}</td>
        <td><a href="<c:url value='/show/group?groupId=${group.getGroupId()}' />">More...</a></td>
        <c:if test="${userType eq 'teacher'}">
            <td><a href="<c:url value='/redirect/edit/group?groupId=${group.getGroupId}' />">Edit</a></td>
            <c:choose>
                <c:when test="${userType eq 'teacher'}"> <!-- studentCount == 0 //TO DO-->
                    <td><a href="<c:url value='/delete/group?groupId=${group.getGroupId}' />">Delete</a></td>
                </c:when>
                <c:otherwise>
                    <td>Delete</td>
                </c:otherwise>
            </c:choose>
        </c:if>

    </tr>
    </c:forEach>
</body>
</html>
