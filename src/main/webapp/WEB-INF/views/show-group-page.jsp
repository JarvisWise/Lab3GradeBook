<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.example.dao.implementations.DAOGroupImpl" %>
<%@page import="org.example.entities.Group" %>
<%@ page import="org.example.tools.custom.exceptions.WrongEntityIdException" %>

<html>
<head>
    <title>Group Card</title>
    <!--<script src="searchMain.js"></script>-->
</head>
<%String userRole=(String)session.getAttribute("current_user_role");%>
<c:set var = "userRole" value = "<%=userRole%>"/>

<body>
<jsp:include page="header.jsp" />
<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav">
        </div>
        <div class="col-sm-8 text-left">
            <div> <!--NEED?-->
                <label>Group Name: ${group.getGroupName()}</label>
                <label>Description: ${group.getGroupDescription()}</label>
                <c:if test="${userRole eq 'teacher'}">
                    <a href="<c:url value='/redirect/edit/group?groupId=${group.getGroupId()}' />" class="btn btn-default">Edit</a>
                    <a href="<c:url value='/delete/group?groupId=${group.getGroupId()}' />" class="btn btn-default">Delete</a>
                </c:if>
            </div>
            <div> <!--NEED?-->
                <c:if test="${userRole eq 'teacher'}">
                    <!-- student selector -->
                <div class="well">
                    <form class="my" action="<c:url value='/edit/student-group?groupId=${group.getGroupId()}' />" method="POST"><!-- -->
                        <div class="form-group input-group my">
                            <select name="studentId" class="form-control">
                                <option value="" disabled selected="selected">choose student</option>
                                <c:forEach var="as" items="${availableStudents}">
                                    <option value="${as.getStudentId()}">${as.getFullNameWithEmail()}</option>
                                </c:forEach>
                            </select>
                            <span class="input-group-btn">
                                <button type="submit" class="btn btn-default">Add Student</button>
                            </span>
                        </div>
                    </form>
                </div>
                </c:if>
            </div>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>More...</th>
                        <c:if test="${userRole eq 'teacher'}">
                            <th>Remove from group</th>
                        </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="student" items="${studentList}">
                        <tr>
                            <td>${student.getFirstName()}</td>
                            <td>${student.getLastName()}</td>
                            <c:url value='/redirect/profile' var="studentURL">
                                <c:param name="userId" value="${student.getStudentId()}"/>
                                <c:param name="userRole" value="${student.getRole()}"/>
                            </c:url>
                            <td><a href="<c:out value="${studentURL}"/>">More...</a></td>
                            <c:if test="${userRole eq 'teacher'}">
                                <td><a href="<c:url value='/delete/student-group?studentId=${student.getStudentId()}&groupId=${group.getGroupId()}' />">Remove from group</a></td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-sm-2 sidenav">
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
