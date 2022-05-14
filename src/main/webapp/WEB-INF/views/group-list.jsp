<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Groups</title>
</head>
<body>
<%String userRole=(String)session.getAttribute("current_user_role");%>
<c:set var = "userRole" value = "<%=userRole%>"/>
<jsp:include page="header.jsp" />

<c:if test="${userRole eq 'teacher'}">
    <a href="<c:url value='/redirect/add/group' />">Add new group</a>
</c:if>
<table>
    <tr>
        <th>ID</th>
        <th>Group Name</th>
        <th>More...</th>
        <!-- TO DO: no need this functional-->
        <c:if test="${userRole eq 'teacher'}">
            <th>Edit</th>
            <th>Delete</th>
        </c:if>
        <!-- TO DO: no need this functional-->
    </tr>
    <c:forEach var="group" items="${groups}">
    <tr>
        <td>${group.getGroupId()}</td>
        <td>${group.getGroupName()}</td>
        <c:url value='/show/group' var="groupURL">
            <c:param name="groupId" value="${group.getGroupId()}"/>
        </c:url>
        <td><a href="<c:out value="${groupURL}"/>">More...</a></td>
        <!-- TO DO: no need this functional-->
        <c:if test="${userRole eq 'admin'}">
            <td><a href="<c:url value='/redirect/edit/group?groupId=${group.getGroupId()}' />">Edit</a></td>
            <c:choose>
                <c:when test="${userRole eq 'teacher'}"> <!-- studentCount == 0 //TO DO-->
                    <td><a href="<c:url value='/delete/group?groupId=${group.getGroupId()}' />">Delete</a></td>
                </c:when>
                <c:otherwise>
                    <td>Delete</td>
                </c:otherwise>
            </c:choose>
        </c:if>
        <!-- TO DO: no need this functional-->

    </tr>
    </c:forEach>
</table>
<jsp:include page="footer.jsp" />
</body>
</html>
