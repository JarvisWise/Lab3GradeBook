<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${action}</title>
</head>

<body>
<c:url value='/show/teacher-all' var="allTeacherURL"/>
<jsp:include page="header.jsp" />
<div>
    <c:choose>
        <c:when test="${action eq 'edit'}">
            <form action="<c:url value='/${action}/teacher/${teacher.getTeacherId()}' />" method="POST">
                <label>Login Name<input type="text" name="loginName" required value="${teacher.getLoginName()}" placeholder="Enter Login Name"></label><br>
                 <label>First Name<input type="text" name="firstName" required value="${teacher.getFirstName()}" placeholder="Enter First Name"></label><br>
                <label>Last Name<input type="text" name="lastName" required value="${teacher.getLastName()}" placeholder="Enter Last Name"></label><br>
        <!-- edit can only owner of acc -->
        <!--<label>Password<input type="text" name="password" required value="$-{studentInfoSet.getStudent().getPassword()}" placeholder="Enter Password"></label><br>-->
        </c:when>
        <c:otherwise>
                <form action="<c:url value='/${action}/teacher' />" method="POST">
                    <label>Login Name<input type="text" name="loginName" required value="" placeholder="Enter Login Name"></label><br>
                    <label>First Name<input type="text" name="firstName" required value="" placeholder="Enter First Name"></label><br>
                    <label>Last Name<input type="text" name="lastName" required value="" placeholder="Enter Last Name"></label><br>
                    <label>Password<input type="text" name="password" required value="" placeholder="Enter Password"></label><br>
        </c:otherwise>
    </c:choose>
        <input type="submit" value="${action}">
            <c:choose>
                <c:when test="${action eq 'edit'}">
                    <a href="<c:url value='/show/teacher?teacherId=${teacher.getTeacherId()}'/>">Cancel</a>
                </c:when>
                <c:otherwise>
                    <a href="<c:out value="${allTeacherURL}"/>">Cancel</a>
                </c:otherwise>
            </c:choose>
        </form>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
