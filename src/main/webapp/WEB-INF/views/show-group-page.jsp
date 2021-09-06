<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Group Card</title>
</head>

<body>
<div>
    <div><label>Group: </label>${group.getGroupName()}</div>
    <c:if test="${userType eq 'teacher'}">
        <div><a href="<c:url value='/redirect/edit/group?groupId=${group.getGroupId()}' />">Edit</a></div>
        <div><a href="<c:url value='/delete/group?groupId=${group.getGroupId()}' />">Delete</a></div>
    </c:if>
</div>
<div id="conList">

</div>
</body>
</html>
