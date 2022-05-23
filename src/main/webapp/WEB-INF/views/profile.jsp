<%@ page import="org.example.entities.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profile</title>
    <!--<script src="searchMain.js"></script>-->
    <!--<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <style>
        /* Set black background color, white text and some padding */
        footer {
            background-color: #555;
            color: white;
            padding: 15px;
        }
        /* Remove the navbar's default rounded borders and increase the bottom margin */
        .navbar {
            margin-bottom: 50px;
            border-radius: 0;
        }

        /* Remove the jumbotron's default bottom margin */
        .jumbotron {
            margin-bottom: 0;
        }
    </style>-->
</head>
<body>
<c:url value='/edit/user-password' var="changePasswordURL" />
<%String userRole=(String)session.getAttribute("current_user_role");%>
<c:set var = "userRole" value = "<%=userRole%>"/>

    <jsp:include page="header.jsp" />
    <div class="container-fluid text-center">
        <div class="row content">
            <div class="col-sm-2 sidenav">
            </div>
            <div class="col-sm-8 text-left">
        <!-- TO DO: find better way to edit function-->
            <div class="row well">
                <div class="col-sm-6">
                    <div class="well">
                        <img src="bird.jpg" class="img-circle" height="200" width="200" alt="Avatar">
                        <p>Email: ${user.getEmail()}</p>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="well">
                        <p>Email: ${user.getEmail()}</p>
                    </div>
                </div>
            </div>


            <p>Email: ${user.getEmail()}</p>
            <p>First Name: ${user.getFirstName()}</p>
            <p>Last Name: ${user.getLastName()}</p>
            <c:if test="${user.getRole() eq 'student'}">
                <!-- headman -->
                    <c:choose>
                        <c:when test="${empty user.getHeadman()}">
                            <p>Headman: (not yet)</p>
                        </c:when>
                        <c:otherwise>
                            <c:url value='/redirect/profile' var="headmanURL">
                                <c:param name="userId" value="${studentInfoSet.getHeadman().getStudentId()}"/>
                                <c:param name="userRole" value="${studentInfoSet.getHeadman().getRole()}"/>
                            </c:url>
                            <p>Headman: <a href="<c:out value="${headmanURL}"/>">${studentInfoSet.getHeadman().getFullName()}</a></p>
                        </c:otherwise>
                    </c:choose>
                <!-- group -->
                    <c:choose>
                        <c:when test="${empty user.getGroupId()}">
                            <p>Group: (not yet)</p>
                        </c:when>
                        <c:otherwise>
                            <c:url value='/show/group' var="studentGroupURL">
                                <c:param name="groupId" value="${studentInfoSet.getGroup().getGroupId()}"/>
                            </c:url>
                            <p>Group: <a href="<c:out value="${studentGroupURL}"/>">${studentInfoSet.getGroup().getGroupName()}</a></p>
                        </c:otherwise>
                    </c:choose>
            </c:if>

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
                                            <td>${e.getValue().getTotalGrade()/e.getKey().getMaxGrade()*100}%</td>
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
            <div class="col-sm-2 sidenav">
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp" />
</body>

</html>
