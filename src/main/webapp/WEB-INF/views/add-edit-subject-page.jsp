<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${action}</title>
</head>
<body>
<c:choose>
<c:when test="${action eq 'edit'}">
<form action="<c:url value='/${action}/subject/${subject.getSubjectId()}' />" method="POST">
    <label>Subject Name</label><input type="text" name="subjectName" required value="${subject.getSubjectName()}" placeholder="Enter Subject Name">
    <label>Max Grade</label><input type="text" name="maxGrade" required value="${subject.getMaxGrade()}" placeholder="Enter Max Grade">
    <label>Pass Proc Grade</label><input type="text" name="passProcGrade" required value="${subject.getPassProcGrade()}" placeholder="Enter Pass Proc Grade">
    </c:when>
    <c:otherwise>
    <form action="<c:url value='/${action}/subject' />" method="POST">
        <label>Subject Name</label><input type="text" name="subjectName" required value="" placeholder="Enter Subject Name">
        <label>Max Grade</label><input type="text" name="maxGrade" required value="" placeholder="Enter Max Grade">
        <label>Pass Proc Grade</label><input type="text" name="passProcGrade" required value="" placeholder="Enter Pass Proc Grade">
    </c:otherwise>
        </c:choose>
        <input type="submit" value="${action}">
    </form>
</body>
</html>
