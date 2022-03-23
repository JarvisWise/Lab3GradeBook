<%@ page import="org.example.entities.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<c:url value='/edit/user-password' var="changePasswordURL">

    <jsp:include page="header.jsp" />
    <div>

            <label>Login: ${user.getLoginName()}</label>
            <label>First Name: ${user.getFirstName()}</label>
            <label>Last Name: ${user.getLastName()}</label>

            <!-- TO DO -->
            <c:if test="${user.getRole() eq 'student'}">
                <label>Headman: <a href="<c:out value="${allStudentURL}"/>">${user.getHeadman()}</a></label>
                <label>My Group: <a href="<c:out value="${allStudentURL}"/>">${user.getGroupId()}</a></label>
            </c:if>

            <c:if test="${isCurrentProfile eq true}">
                <!-- add pre valid on empty field-->
                <form action="<c:out value="${changePasswordURL}"/>">
                    <label>Password: <input type="text" name="newPassword"></label>
                    <label>Confirm Password: <input type="text" name="confirmPassword"></label>
                    <button type="submit" >Change Password</button>
                </form>
            </c:if>
    </div>
    <jsp:include page="footer.jsp" />
</body>

</html>
