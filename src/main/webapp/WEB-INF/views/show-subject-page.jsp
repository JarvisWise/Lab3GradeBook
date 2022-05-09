<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.example.dao.implementations.DAOGroupImpl" %>
<%@page import="org.example.entities.Group" %>
<%@ page import="org.example.tools.custom.exceptions.WrongEntityIdException" %>

<html>
<head>
    <title>Subject Card</title>
    <!--<script src="searchMain.js"></script>-->
</head>
<%String userRole=(String)session.getAttribute("current_user_role");%>
<c:set var = "userRole" value = "<%=userRole%>"/>

<body>
<jsp:include page="header.jsp" />
<div>
    <div>
        <label>Subject Name: ${subject.getSubjectName()}</label>
        <c:if test="${userRole eq 'teacher'}">
            <a href="<c:url value='/redirect/edit/subject?subjectId=${subject.getSubjectId()}' />">Edit</a>
            <a href="<c:url value='/delete/subject?subjectId=${subject.getSubjectId()}' />">Delete</a>
        </c:if>
    </div>
    <!-- teachers -->
    <table>
        <tr>
            <th>Teacher First Name</th>
            <th>Teacher Last Name</th>
            <th>More...</th>
            <c:if test="${userRole eq 'teacher'}">
                <th>Edit</th>
                <th>Delete</th>
            </c:if>

        </tr>
        <c:forEach var="teacher" items="${teacherList}">
            <tr>
                <td>${teacher.getFirstName()}</td>
                <td>${teacher.getLastName()}</td>
                <c:url value='/redirect/profile' var="teacherURL">
                    <c:param name="userId" value="${teacher.getTeacherId()}"/>
                    <c:param name="userRole" value="${teacher.getRole()}"/>
                </c:url>
                <td><a href="<c:out value="${teacherURL}"/>">More...</a></td>
                <c:if test="${userRole eq 'teacher'}">
                    <!-- no need -->
                    <td><a href="<c:url value='/redirect/edit/student?studentId=${student.getStudentId()}' />">Edit</a></td>
                    <!-- TO DO delete form subject -->
                    <td><a href="<c:url value='/delete/student?studentId=${student.getStudentId()}' />">Delete</a></td>
                </c:if>

            </tr>
        </c:forEach>
    </table>
    <!-- students -->
    <table>
        <tr>
            <th>Student First Name</th>
            <th>Student Last Name</th>
            <!--<th>Group</th>-> //TO DO-->
            <th>Total Grade</th>
            <th>Grade(%)</th>
            <th>Pass(%)</th>
            <th>More...</th>
            <c:if test="${userRole eq 'teacher'}">
                <th>Edit</th>
                <th>Delete</th>
            </c:if>

        </tr>
        <c:forEach var="e" items="${studentSubjectMap.entrySet()}">
            <tr>
                <td>${e.getKey().getFirstName()}</td>
                <td>${e.getKey().getLastName()}</td>
                <td>${e.getValue().getTotalGrade()}</td>
                <td>${e.getValue().getTotalGrade()/subject.getMaxGrade()*100}%</td>
                <td>${subject.getPassProcGradeP()}%</td>
                <c:url value='/redirect/profile' var="studentURL">
                    <c:param name="userId" value="${e.getKey().getStudentId()}"/>
                    <c:param name="userRole" value="${e.getKey().getRole()}"/>
                </c:url>
                <td><a href="<c:out value="${studentURL}"/>">More...</a></td>
                <c:if test="${userRole eq 'teacher'}">
                    <!-- no need -->
                    <td><a href="<c:url value='/redirect/edit/student?studentId=${student.getStudentId()}' />">Edit</a></td>
                    <!-- TO DO delete form subject -->
                    <td><a href="<c:url value='/delete/student?studentId=${student.getStudentId()}' />">Delete</a></td>
                </c:if>

            </tr>
        </c:forEach>
    </table>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>