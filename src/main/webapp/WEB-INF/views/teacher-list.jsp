<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Teachers</title>
</head>
<body>
    <%String userRole=(String)session.getAttribute("current_user_role");%>
    <c:set var = "userRole" value = "<%=userRole%>"/>
    <jsp:include page="header.jsp" />

    <c:if test="${userRole eq 'teacher'}">
        <!--<a href="">Add new student</a>-->
    </c:if>
    <table>
        <tr>
            <th>Login</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>More...</th>
        </tr>
        <c:forEach var="teacher" items="${teachers}">
            <tr>
                <td>${teacher.getLoginName()}</td>
                <td>${teacher.getFirstName()}</td>
                <td>${teacher.getLastName()}</td>

                <c:url value='/redirect/profile' var="teacherURL">
                    <c:param name="userId" value="${teacher.getTeacherId()}"/>
                    <c:param name="userRole" value="${teacher.getRole()}"/>
                </c:url>
                <td><a href="<c:out value="${teacherURL}"/>">More...</a></td>
            </tr>
        </c:forEach>
    </table>
    <jsp:include page="footer.jsp" />
</body>
</html>