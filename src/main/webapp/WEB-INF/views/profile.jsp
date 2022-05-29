<%@ page import="org.example.entities.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<c:url value='/edit/user-password' var="changePasswordURL" />
<%String userRole=(String)session.getAttribute("current_user_role");%>
<c:set var = "userRole" value = "<%=userRole%>"/>


    <jsp:include page="header.jsp" />
    <div class="container-fluid text-center">
        <div class="row content my-c" > <!-- style="background: green;"-->
            <div class="col-xs-8 col-xs-offset-2 text-left my"> <!-- style="background: #fff; min-height: calc(100vh - 112px);" -->
        <!-- TO DO: find better way to edit function-->
                <div class="well">
                    <table class="table table-bordered tb-my " > <!-- style="margin: 30px auto; max-width: 500px;"-->
                        <tbody>
                            <tr><th class="alert alert-info">Full name</th><td class="bg-success">${user.getFullName()}</td></tr> <!-- style="width: 150px;" -->
                            <tr><th class="alert alert-info">Email</th><td class="bg-success">${user.getEmail()}</td></tr>
                            <tr><th class="alert alert-info">First name</th><td class="bg-success">${user.getFirstName()}</td></tr>
                            <tr><th class="alert alert-info">Last name</th><td class="bg-success">${user.getLastName()}</td></tr>
                            <c:if test="${user.getRole() eq 'student'}">
                                <!-- headman -->
                                <c:choose>
                                    <c:when test="${empty user.getHeadman()}">
                                        <tr><th class="alert alert-info">Headman</th><td class="bg-success">(not yet)</td></tr>
                                    </c:when>
                                    <c:otherwise>
                                        <c:url value='/redirect/profile' var="headmanURL">
                                            <c:param name="userId" value="${studentInfoSet.getHeadman().getStudentId()}"/>
                                            <c:param name="userRole" value="${studentInfoSet.getHeadman().getRole()}"/>
                                        </c:url>
                                        <tr><th class="alert alert-info">Headman</th><td class="bg-success"><a href="<c:out value="${headmanURL}"/>">${studentInfoSet.getHeadman().getFullName()}</a></td></tr>
                                    </c:otherwise>
                                </c:choose>
                                <!-- group -->
                                <c:choose>
                                    <c:when test="${empty user.getGroupId()}">
                                        <tr><th class="alert alert-info">Group</th><td class="bg-success">(not yet)</td></tr>
                                    </c:when>
                                    <c:otherwise>
                                        <c:url value='/show/group' var="studentGroupURL">
                                            <c:param name="groupId" value="${studentInfoSet.getGroup().getGroupId()}"/>
                                        </c:url>
                                        <tr><th class="alert alert-info">Group</th><td class="bg-success"><a href="<c:out value="${studentGroupURL}"/>">${studentInfoSet.getGroup().getGroupName()}</a></td></tr>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </tbody>
                    </table>
                </div>


            <!-- subject list -->
            <c:choose>
                <c:when test="${user.getRole() eq 'student'}">
                    <c:choose>
                        <c:when test="${studentSubjectMap.size() eq 0}">
                            <label>Subjects: (not yet)</label><br>
                        </c:when>
                        <c:otherwise>
                            <!--<label>Subjects:</label><br>-->
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>Subject Name</th>
                                        <th>Total Grade</th>
                                        <th>Grade(%)</th>
                                        <th>Pass(%)</th>
                                        <th>More...</th>
                                        <th>Grade details...</th>
                                        <c:if test="${userRole eq 'teacher'}">
                                            <th>Remove from subject</th>
                                        </c:if>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="e" items="${studentSubjectMap.entrySet()}">
                                        <tr>
                                            <td>${e.getKey().getSubjectName()}</td>
                                            <td>${e.getValue().getTotalGrade()}</td>
                                            <td><!--$-{e.getValue().getTotalGrade()/e.getKey().getMaxGrade()*100}%</td>-->
                                                <script>
                                                    document.write(Math.round(parseInt(${e.getValue().getTotalGrade()/e.getKey().getMaxGrade()*100})));
                                                </script>%
                                            </td>
                                            <td>${e.getKey().getPassProcGradeP()}%</td>
                                            <c:url value='/show/subject' var="subjectURL">
                                                <c:param name="subjectId" value="${e.getKey().getSubjectId()}"/>
                                            </c:url>
                                            <td><a href="<c:out value="${subjectURL}"/>">More...</a></td>
                                            <c:url value='/show/student-task-list' var="studentTaskListURL">
                                                <c:param name="studentId" value="${e.getValue().getStudentId()}"/>
                                                <c:param name="subjectId" value="${e.getKey().getSubjectId()}"/>
                                            </c:url>
                                            <td><a href="<c:out value="${studentTaskListURL}"/>">Grade details...</a></td>
                                            <c:if test="${userRole eq 'teacher'}">
                                                <td><a href="<c:url value='/delete/student-subject?studentId=${user.getStudentId()}&subjectId=${e.getKey().getSubjectId()}' />">Remove from subject</a></td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${teacherSubjectMap.size() eq 0}">
                            <label>Subjects: (not yet)</label><br>
                        </c:when>
                        <c:otherwise>
                            <!--<label>Subjects:</label><br>-->
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>Subject Name</th>
                                        <th>Pass(%)</th>
                                        <th>More...</th>
                                        <c:if test="${userRole eq 'teacher'}">
                                            <th>Remove from subject</th>
                                        </c:if>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="subject" items="${teacherSubjectMap}">
                                        <tr>
                                            <td>${subject.getSubjectName()}</td>
                                            <td>${subject.getPassProcGradeP()}%</td>
                                            <c:url value='/show/subject' var="subjectURL">
                                                <c:param name="subjectId" value="${subject.getSubjectId()}"/>
                                            </c:url>
                                            <td><a href="<c:out value="${subjectURL}"/>">More...</a></td>
                                            <c:if test="${userRole eq 'teacher'}">
                                                <td><a href="<c:url value='/delete/teacher-subject?teacherId=${user.getTeacherId()}&subjectId=${subject.getSubjectId()}' />">Remove from subject</a></td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>

            <c:if test="${isCurrentProfile eq 'Yes'}">
                <!-- add pre valid on empty field-->
            <div class="well">
                <div class="profile">
                    <form class="form-inline my" action="<c:out value="${changePasswordURL}"/>" method="post">
                        <div class="form-group my">
                            <label for="newPassword">New password: </label>
                            <input type="password" class="form-control" name="newPassword" id="newPassword" required placeholder="New password.."/>
                        </div>
                        <div class="form-group">
                            <label for="confirmPassword">Confirm password: </label>
                            <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" required placeholder="Confirm password.."/>
                        </div>
                        <button type="submit" class="btn btn-default">Change Password</button>
                    </form>
                </div>
            </div>
            </c:if>
            <!-- add&edit user-->
                <c:if test="${isCurrentProfile eq 'Yes'}">
                    <c:choose>
                        <c:when test="${user.getRole() eq 'student'}">
                            <div class="well">
                                <div class="profile">
                                    <a href="<c:url value='/redirect/edit/student?studentId=${user.getStudentId()}' />" class="btn btn-default">Change Account Information</a>
                                    <a href="<c:url value='/delete/student?studentId=${user.getStudentId()}' />" class="btn btn-default">Delete Account</a>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="well">
                                <div class="profile">
                                    <a href="<c:url value='/redirect/edit/teacher?teacherId=${user.getTeacherId()}' />" class="btn btn-default">Change Account Information</a>
                                    <a href="<c:url value='/delete/teacher?teacherId=${user.getTeacherId()}' />" class="btn btn-default">Delete Account</a>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp" />
</body>

</html>
