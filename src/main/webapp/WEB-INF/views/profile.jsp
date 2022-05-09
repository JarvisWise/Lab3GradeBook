<%@ page import="org.example.entities.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profile</title>
    <!--<script src="searchMain.js"></script>-->
</head>
<body>
<c:url value='/edit/user-password' var="changePasswordURL" />
<%String userRole=(String)session.getAttribute("current_user_role");%>
<c:set var = "userRole" value = "<%=userRole%>"/>

    <jsp:include page="header.jsp" />
    <div>
            <c:if test="${isCurrentProfile eq 'Yes'}">
                <label>Login: ${user.getLoginName()}</label><br>
            </c:if>
            <label>First Name: ${user.getFirstName()}</label><br>
            <label>Last Name: ${user.getLastName()}</label><br>

            <!-- TO DO -->
            <c:if test="${user.getRole() eq 'student'}">
                    <c:choose>
                        <c:when test="${empty user.getHeadman()}">
                            <label>Headman: (not yet)</label><br>
                        </c:when>
                        <c:otherwise>
                            <c:url value='/redirect/profile' var="headmanURL">
                                <c:param name="userId" value="${userHeadman.getStudentId()}"/>
                                <c:param name="userRole" value="${userHeadman.getRole()}"/>
                            </c:url>
                            <label>Headman: <a href="<c:out value="${headmanURL}"/>">${userHeadman.getFullName()}</a></label><br>
                        </c:otherwise>
                    </c:choose>
                <c:choose>
                    <c:when test="${empty user.getGroupId()}">
                        <label>Group: (not yet)</label><br>
                    </c:when>
                    <c:otherwise>
                        <c:url value='/show/group' var="studentGroupURL">
                            <c:param name="groupId" value="${userGroup.getGroupId()}"/>
                        </c:url>
                        <label>Group: <a href="<c:out value="${studentGroupURL}"/>">${userGroup.getGroupName()}</a></label><br>
                    </c:otherwise>
                </c:choose>
                <!-- forEach for subjects-->
            </c:if>

            <!-- subject list -->
            <c:choose>
                <c:when test="${user.getRole() eq 'student'}">
                    <c:choose>
                        <c:when test="${studentSubjectMap.size() eq 0}">
                            <label>Subjects: (not yet)</label><br>
                        </c:when>
                        <c:otherwise>
                            <label>Subjects:</label><br>
                            <table>
                                <tr>
                                    <th>Subject Name</th>
                                    <th>Total Grade</th>
                                    <th>Grade(%)</th>
                                    <th>Pass(%)</th>
                                    <th>More...</th>
                                    <!-- TO DO: no need this functional-->
                                    <c:if test="${userRole eq 'teacher'}">
                                        <th>Edit</th>
                                        <th>Delete</th>
                                    </c:if>
                                    <!-- TO DO: no need this functional-->
                                </tr>
                                <c:forEach var="e" items="${studentSubjectMap.entrySet()}">
                                    <tr>
                                        <td>${e.getKey().getSubjectName()}</td>
                                        <td>${e.getValue().getTotalGrade()}</td>
                                        <td>${e.getValue().getTotalGrade()/e.getKey().getMaxGrade()*100}%</td>
                                        <td>${e.getKey().getPassProcGradeP()}%</td>
                                        <c:url value='/show/subject' var="subjectURL">
                                            <c:param name="subjectId" value="${e.getKey().getSubjectId()}"/>
                                        </c:url>
                                        <td><a href="<c:out value="${subjectURL}"/>">More...</a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <!-- TO DO: subject list for teacher-->
                    <c:choose>
                        <c:when test="${teacherSubjectMap.size() eq 0}">
                            <label>Subjects: (not yet)</label><br>
                        </c:when>
                        <c:otherwise>
                            <label>Subjects:</label><br>
                            <table>
                                <tr>
                                    <th>Subject Name</th>
                                    <th>Pass(%)</th>
                                    <th>More...</th>
                                </tr>
                                <c:forEach var="subject" items="${teacherSubjectMap}">
                                    <tr>
                                        <td>${subject.getSubjectName()}</td>
                                        <td>${subject.getPassProcGradeP()}%</td>
                                        <c:url value='/show/subject' var="subjectURL">
                                            <c:param name="subjectId" value="${subject.getSubjectId()}"/>
                                        </c:url>
                                        <td><a href="<c:out value="${subjectURL}"/>">More...</a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>

            <c:if test="${isCurrentProfile eq 'Yes'}">
                <!-- add pre valid on empty field-->
                <form action="<c:out value="${changePasswordURL}"/>" method="post">
                    <label>Password: <input type="password" name="newPassword"></label><br>
                    <label>Confirm Password: <input type="password" name="confirmPassword"></label><br>
                    <button type="submit" >Change Password</button>
                </form>
            </c:if>
    </div>
    <jsp:include page="footer.jsp" />
</body>

</html>
