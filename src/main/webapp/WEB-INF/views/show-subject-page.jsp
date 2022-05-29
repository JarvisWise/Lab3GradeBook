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
<div class="container-fluid text-center">
    <div class="row content my-c">
        <div class="col-xs-8 col-xs-offset-2 text-left my">

            <c:if test="${userRole eq 'teacher'}">
                <div class="well">
                    <a href="<c:url value='/redirect/edit/subject?subjectId=${subject.getSubjectId()}' />" class="btn btn-default">Change Subject Info</a>
                    <a href="<c:url value='/delete/subject?subjectId=${subject.getSubjectId()}' />" class="btn btn-default">Remove Subject</a>
                </div>
            </c:if>
            <div class="well">
                <table class="table table-bordered tb-my " >
                    <tbody>
                        <tr><th class="alert alert-info">Subject name</th><td class="bg-success">${subject.getSubjectName()}</td></tr>
                        <tr><th class="alert alert-info">Max grade</th><td class="bg-success">${subject.getMaxGrade()}</td></tr>
                        <tr><th class="alert alert-info">Subject description</th><td class="bg-success">${subject.getSubjectDescription()}</td></tr>
                    </tbody>
                </table>
            </div>

            <!--<div> !--NEED?--
                <label>Subject name: $-{subject.getSubjectName()}</label>
                <label>Max grade: $-{subject.getMaxGrade()}</label>
                <label>Subject description: $-{subject.getSubjectDescription()}</label>
            </div>-->
            <!-- teachers -->
            <!--<hr>-->
            <c:if test="${userRole eq 'teacher'}">
            <!-- teacher selector -->
                <div class="well">
                    <form class="my" action="<c:url value='/add/teacher-subject?subjectId=${subject.getSubjectId()}' />" method="POST"><!-- -->
                        <div class="form-group input-group my">
                            <select name="teacherId" class="form-control">
                                <option value="" disabled selected="selected">choose teacher</option>
                                <c:forEach var="at" items="${availableTeachers}">
                                    <option value="${at.getTeacherId()}">${at.getFullNameWithEmail()}</option>
                                </c:forEach>
                            </select>
                            <span class="input-group-btn">
                                    <button type="submit" class="btn btn-default">Add Teacher</button>
                                </span>
                        </div>
                    </form>
                </div>
            </c:if>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Teacher First Name</th>
                        <th>Teacher Last Name</th>
                        <th>More...</th>
                        <c:if test="${userRole eq 'teacher'}">
                            <th>Remove from subject</th>
                        </c:if>
                    </tr>
                </thead>
                <tbody>
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
                                <td><a href="<c:url value='/delete/teacher-subject?teacherId=${teacher.getTeacherId()}&subjectId=${subject.getSubjectId()}' />">Remove from subject</a></td>
                            </c:if>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <!-- students -->
            <!-- <hr> -->
            <c:if test="${userRole eq 'teacher'}">
                <!-- student selector -->
                <div class="well">
                    <form class="my" action="<c:url value='/add/student-subject?subjectId=${subject.getSubjectId()}' />" method="POST"><!-- -->
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
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Student First Name</th>
                        <th>Student Last Name</th>
                        <!--<th>Group</th>-> //TO DO-->
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
                            <td>${e.getKey().getFirstName()}</td>
                            <td>${e.getKey().getLastName()}</td>
                            <td>${e.getValue().getTotalGrade()}</td>
                            <td><!--$-{e.getValue().getTotalGrade()/subject.getMaxGrade()*100}%-->
                                <script>
                                    document.write(Math.round(parseInt(${e.getValue().getTotalGrade()/subject.getMaxGrade()*100})));
                                </script>%
                            </td>
                            <td>${subject.getPassProcGradeP()}%</td>
                            <!-- ref to student-->
                            <c:url value='/redirect/profile' var="studentURL">
                                <c:param name="userId" value="${e.getKey().getStudentId()}"/>
                                <c:param name="userRole" value="${e.getKey().getRole()}"/>
                            </c:url>
                            <td><a href="<c:out value="${studentURL}"/>">More...</a></td>
                            <!-- ref to student-task-list -->
                            <c:url value='/show/student-task-list' var="studentTaskListURL">
                                <c:param name="studentId" value="${e.getKey().getStudentId()}"/>
                                <c:param name="subjectId" value="${subject.getSubjectId()}"/>
                            </c:url>
                            <td><a href="<c:out value="${studentTaskListURL}"/>">Grade details...</a></td>
                            <c:if test="${userRole eq 'teacher'}">
                                <td><a href="<c:url value='/delete/student-subject?studentId=${student.getStudentId()}&subjectId=${subject.getSubjectId()}' />">Remove from subject</a></td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <!-- tasks -->
            <!--<hr>-->
            <div class="well">
                <c:if test="${userRole eq 'teacher'}">
                    <a href="<c:url value='/redirect/add/task?subjectId=${subject.getSubjectId()}' />" class="btn btn-default">Add Task</a>
                </c:if>
            </div>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Task Name</th>
                        <th>Description</th>
                        <th>Max Grade</th>
                        <c:if test="${userRole eq 'teacher'}">
                            <th>Change</th>
                            <th>Remove</th>
                        </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="task" items="${taskList}">
                        <tr>
                            <td>${task.getTaskName()}</td>
                            <td class="desc-my">
                                ${task.getTaskDescription()}
                                <!--<a href="#" title="Task Description" data-toggle="popover" data-placement="right" data-content="$-{st.getKey().getTaskDescription()}">details..</a>-->
                            </td>
                            <td>${task.getMaxGrade()}</td>
                            <c:if test="${userRole eq 'teacher'}">
                                <td><a href="<c:url value='/redirect/edit/task?taskId=${task.getTaskId()}' />">Change</a></td>
                                <td><a href="<c:url value='/delete/task?taskId=${task.getTaskId()}&subjectId=${task.getSubjectId()}' />">Remove</a></td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>