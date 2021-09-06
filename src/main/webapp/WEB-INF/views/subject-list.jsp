<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Subjects</title>
</head>
<body>
<c:if test="${userType eq 'teacher'}">
    <a href="<c:url value='/redirect/add/subject' />">Add new subject</a>
</c:if>
<table>
    <tr>
        <th>ID</th>
        <th>Subject Name</th>
        <th>Max Grade</th>
        <th>Pass Grade</th>
        <th>More...</th>
        <c:if test="${userType eq 'teacher'}">
            <th>Edit</th>
            <th>Delete</th>
        </c:if>
    </tr>
    <c:forEach var="subject" items="${subjects}">
        <tr>
            <td>${subject.getSubjectId()}</td>
            <td>${subject.getSubjectName()}</td>
            <td>${subject.getMaxGrade()}</td>
            <td>${subject.getPassProgGrade()}</td>
            <td><a href="<c:url value='/show/subject?subjectId=${subject.getSubjectId()}' />">More...</a></td>
            <c:if test="${userType eq 'teacher'}">
                <td><a href="<c:url value='/redirect/edit/subject?subjectId=${subject.getSubjectId()}' />">Edit</a></td>
                <td><a href="<c:url value='/delete/subject?subjectId=${subject.getSubjectId()}' />">Delete</a></td>
            </c:if>

        </tr>
    </c:forEach>
</table>
</body>
</html>
