<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
<%String userRole=(String)session.getAttribute("current_user_role");%>
<c:set var = "userRole" value = "<%=userRole%>"/>
<jsp:include page="header.jsp" />
<c:if test="${userRole eq 'teacher'}">
    <a href="<c:url value='/redirect/add/student' />">Add new student</a>
</c:if>
<table>
    <tr>
        <th>Login</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Headman ID</th>
        <th>Group ID</th>
        <th>More...</th>
        <c:if test="${userRole eq 'teacher'}">
            <th>Edit</th>
            <th>Delete</th>
        </c:if>
    </tr>
    <c:forEach var="student" items="${students}">
        <tr>
            <td>${student.getLoginName()}</td>
            <td>${student.getFirstName()}</td>
            <td>${student.getLastName()}</td>
            <c:choose>
                <c:when test="${empty student.getHeadman()}">
                    <td>(not yet)</td>
                </c:when>
                <c:otherwise>
                    <c:url value='/redirect/profile' var="studentHeadmanURL">
                        <!-- TO DO: userHeadman.getStudentId()-->
                        <c:param name="userId" value="${student.getHeadman()}"/>
                        <!-- TO DO: userHeadman.getRole()-->
                        <c:param name="userRole" value="${student.getRole()}"/>
                    </c:url>
                    <!-- TO DO: userHeadman.getFullName()-->
                    <td><a href="<c:out value="${studentHeadmanURL}"/>">${student.getHeadman()}</a></td>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${empty student.getGroupId()}">
                    <td>(not yet)</td>
                </c:when>
                <c:otherwise>
                    <c:url value='/show/group' var="studentGroupURL">
                        <c:param name="groupId" value="${student.getGroupId()}"/>
                    </c:url>
                    <!-- TO DO: userGroup.getGroupName()-->
                    <td>Group: <a href="<c:out value="${studentGroupURL}"/>">${student.getGroupId()}</a></td>
                </c:otherwise>
            </c:choose>

            <c:url value='/redirect/profile' var="studentURL">
                <c:param name="userId" value="${student.getStudentId()}"/>
                <c:param name="userRole" value="${student.getRole()}"/>
            </c:url>
            <td><a href="<c:out value="${studentURL}"/>">More...</a></td>

            <!-- TO DO: no need this functional-->
            <c:if test="${userRole eq 'admin'}">
                <td><a href="<c:url value='/redirect/edit/student?studentId=${student.getStudentId()}' />">Edit</a></td>
                <td><a href="<c:url value='/delete/student?studentId=${student.getStudentId()}' />">Delete</a></td>
            </c:if>
            <!-- TO DO: no need this functional-->

        </tr>
    </c:forEach>
</table>
<jsp:include page="footer.jsp" />
</body>
</html>
