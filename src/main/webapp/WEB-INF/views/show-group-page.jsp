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
<div>
    <div>
        <label>Group Name: ${group.getGroupName()}</label>
        <c:if test="${userRole eq 'teacher'}">
            <a href="<c:url value='/redirect/edit/group?groupId=${group.getGroupId()}' />">Edit</a>
            <a href="<c:url value='/delete/group?groupId=${group.getGroupId()}' />">Delete</a>
        </c:if>
    </div>
    <div>
        <c:if test="${userRole eq 'teacher'}">
            <!-- student selector -->
            <form action="<c:url value='/edit/student-group?groupId=${group.getGroupId()}' />" method="POST"><!-- -->
                <label> Add student
                    <select name="studentId">
                        <option value="" selected="selected">choose student</option>
                        <c:forEach var="as" items="${availableStudents}">
                            <option value="${as.getStudentId()}">${as.getFullNameWithLogin()}</option>
                        </c:forEach>
                    </select>
                </label>
                <input type="submit" value="Add Student">
            </form>
        </c:if>
    </div>
    <table>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>More...</th>
            <c:if test="${userRole eq 'teacher'}">
                <th>Remove from group</th>
            </c:if>

        </tr>
    <c:forEach var="student" items="${studentList}">
        <tr>
            <!--<c :set var = "groupId" value = "$-{student.getGroupId()}"/>
            <-%
                DAOGroupImpl daoGroup = new DAOGroupImpl();
                try {
                    Group group = daoGroup.getGroupById((String)pageContext.getAttribute("groupId"));
                } catch (WrongEntityIdException e) {
                    e.printStackTrace();
                }
            %>
            <c :set var = "userRole" value = "<-%=userRole%>"/>-->
            <!--<td>$-{student.getStudentId()}</td>-->
            <td>${student.getFirstName()}</td>
            <td>${student.getLastName()}</td>
            <!--<td>$-{student.getHeadman()}</td>-->
            <!--<td>$-{group.getGroupName()}</td> student.getGroupId() time solution-->
            <c:url value='/redirect/profile' var="studentURL">
                <c:param name="userId" value="${student.getStudentId()}"/>
                <c:param name="userRole" value="${student.getRole()}"/>
            </c:url>
            <!--url value='/show/student?studentId=$-{student.getStudentId()}' />-->
            <td><a href="<c:out value="${studentURL}"/>">More...</a></td>
            <c:if test="${userRole eq 'teacher'}">
                <td><a href="<c:url value='/delete/student-group?studentId=${student.getStudentId()}&groupId=${group.getGroupId()}' />">Remove from group</a></td>
            </c:if>

        </tr>
    </c:forEach>
    </table>
</div>
<jsp:include page="footer.jsp" />
<!--<div id="conList">
</div>-->
</body>
</html>
