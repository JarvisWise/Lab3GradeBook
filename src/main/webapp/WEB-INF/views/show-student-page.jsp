<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student Card</title>
</head>

<body>
    <div>
        <div><label>Full Name: </label>${student.getFirstName()+" "+student.getLastName()}</div>
        <div><label>Headman: </label><a href="<c:url value='/show/student?studentId=${headman.getStudentId()}' />">${headman.getFirstName()+" "+headman.getLastName()}</a></div>
        <div><label>Group: </label><a href="<c:url value='/show/group?groupId=${group.getGroupId()}' />">${group.getGroupName()}</a></div>
        <c:if test="${userType eq 'teacher'}">
            <div><a href="<c:url value='/redirect/edit/student?studentId=${student.getStudentId()}' />">Edit</a></div>
            <div><a href="<c:url value='/delete/student?studentId=${student.getStudentId()}' />">Delete</a></div>
        </c:if>
    </div>
    <div id="conList">

    </div>
</body>
</html>

<!-- old body-->
<!--
div>
<div><label>Full Name: </label>$-{student.getFirstName()+" "+student.getLastName()}</div>
<div><label>Headman: </label><a href="<c :url value='/show/student?studentId=$-{headman.getStudentId()}' />">$-{headman.getFirstName()+" "+headman.getLastName()}</a></div>
<div><label>Group: </label><a href="<c: url value='/show/group?groupId=$-{group.getGroupId()}' />">$-{group.getGroupName()}</a></div>
<c: if test="$-{userType eq 'teacher'}">
    <div><a href="<c: url value='/redirect/edit/student?studentId=$-{student.getStudentId()}' />">Edit</a></div>
    <div><a href="<c: url value='/delete/student?studentId=$-{student.getStudentId()}' />">Delete</a></div>
</c: if>
</div>
<div id="conList">

</div>
-->