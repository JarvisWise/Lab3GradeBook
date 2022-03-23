<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${action}</title>
</head>
<body>
    <c:choose>
        <c:when test="${action eq 'edit'}">
        <form action="<c:url value='/${action}/student/${student.getStudentId()}' />" method="POST">
            <label>First Name</label><input type="text" name="firstName" required value="${student.getFirstName()}" placeholder="Enter First Name">
            <label>Login Name</label><input type="text" name="loginName" required value="${student.getLoginName()}" placeholder="Enter Login Name">
            <label>Last Name</label><input type="text" name="lastName" required value="${student.getLastName()}" placeholder="Enter Last Name">
            <label>Headman</label><input type="text" name="headman" required value="${student.getHeadman()}" placeholder="Enter Headman">
            <label>Group Id</label><input type="text" name="groupId" required value="${student.getGroupId()}" placeholder="Enter Group Id">
            <label>Password</label><input type="text" name="password" required value="${student.getPassword()}" placeholder="Enter Password">
        </c:when>
        <c:otherwise>
            <form action="<c:url value='/${action}/student' />" method="POST">
            <label>First Name</label><input type="text" name="firstName" required value="" placeholder="Enter First Name">
            <label>Login Name</label><input type="text" name="loginName" required value="" placeholder="Enter Login Name">
            <label>Last Name</label><input type="text" name="lastName" required value="" placeholder="Enter Last Name">
            <label>Headman</label><input type="text" name="headman" required value="" placeholder="Enter Headman">
            <label>Group Id</label><input type="text" name="groupId" required value="" placeholder="Enter Group Id">
            <label>Password</label><input type="text" name="password" required value="" placeholder="Enter Password">
        </c:otherwise>
    </c:choose>
    <input type="submit" value="${action}">
</form>
</body>
</html>
