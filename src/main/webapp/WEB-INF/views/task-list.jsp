<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Tasks</title>
</head>

private String taskId;
private String SubjectId;
private String studentSubjectId;
private String taskName;
private int maxGrade;
private int grade;
<!-- TO DO: remove this page-->
<body>
<c:if test="${userType eq 'teacher'}">
    <a href="<c:url value='/redirect/add/studentTask' />">Add new subject</a>
</c:if>
<table>
    <tr>
        <th>ID</th>
        <th>Task Name</th>
        <th>Subject Id</th>
        <th>Max Grade</th>
        <th>Grade</th>
        <c:if test="${userType eq 'teacher'}">
            <th>Edit</th>
            <th>Delete</th>
        </c:if>
    </tr>
    <c:forEach var="subject" items="${subjects}">
        <tr>
            <td>${studentTask.getTaskId()}</td>
            <td>${studentTask.getTaskName()}</td>
            <td>${studentTask.getSubjectId()}</td>
            <td>${studentTask.getMaxGrade()}</td>
            <td>${studentTask.getGrade()}</td>
            <c:if test="${userType eq 'teacher'}">
                <td><a href="<c:url value='/redirect/edit/studentTask?taskId=${studentTask.getTaskId()}' />">Edit</a></td>
                <td><a href="<c:url value='/delete/studentTask?taskId=${studentTask.getTaskId()}' />">Delete</a></td>
            </c:if>
        </tr>
    </c:forEach>
</table>
</body>
</html>
