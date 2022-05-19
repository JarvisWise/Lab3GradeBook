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
        <th>Group Description</th>
        <th>More...</th>

    </tr>
    <c:forEach var="group" items="${groups}">
    <tr>
        <td>${group.getGroupId()}</td>
        <td>${group.getGroupName()}</td>
        <td>${group.getGroupDescription()}</td>
        <c:url value='/show/group' var="groupURL">
            <c:param name="groupId" value="${group.getGroupId()}"/>
        </c:url>
        <td><a href="<c:out value="${groupURL}"/>">More...</a></td>

    </tr>
    </c:forEach>
</table>
<jsp:include page="footer.jsp" />
</body>
</html>
