<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
<c:if test="${userType eq 'teacher'}">
    <a href="<c:url value='/redirect//add/student' />">Add new student</a>
</c:if>
<table>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Headman ID</th>
        <th>Group ID</th>
        <th>More...</th>
        <c:if test="${userType eq 'teacher'}">
            <th>Edit</th>
            <th>Delete</th>
        </c:if>
    </tr>
    <c:forEach var="student" items="${students}">
        <tr>
            <td>${student.getStudentId()}</td>
            <td>${student.getFirstName()}</td>
            <td>${student.getLastName()}</td>
            <td>${student.getHeadman()}</td>
            <td>${student.getGroupId()}</td>
            <td><a href="<c:url value='/show/student?studentId=${student.getStudentId()}' />">More...</a></td>
            <c:if test="${userType eq 'teacher'}">
                <td><a href="<c:url value='/redirect/edit/student?studentId=${student.getStudentId()}' />">Edit</a></td>
                <td><a href="<c:url value='/delete/student?studentId=${student.getStudentId()}' />">Delete</a></td>
            </c:if>

        </tr>
    </c:forEach>
</table>
</body>
</html>
