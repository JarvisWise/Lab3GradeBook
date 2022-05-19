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
    <!-- Add form [student & teacher]-->
    <div>
        <!-- TO DO: add student & add teacher-->
        <c:if test="${userRole eq 'teacher'}">
            <!-- teacher selector -->
            <form action="<c:url value='/add/teacher-subject?subjectId=${subject.getSubjectId()}' />" method="POST"><!-- -->
                <label> Add teacher
                    <select name="teacherId">
                        <option value="" selected="selected">choose teacher</option>
                        <c:forEach var="at" items="${availableTeachers}">
                            <option value="${at.getTeacherId()}">${at.getFullNameWithLogin()}</option>
                        </c:forEach>
                    </select>
                </label>
                <input type="submit" value="Add Teacher">
            </form>
            <!-- student selector -->
            <form action="<c:url value='/add/student-subject?subjectId=${subject.getSubjectId()}' />" method="POST"><!-- -->
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
        <!-- TO DO: add student & add teacher-->
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
                    <td><a href="<c:url value='/delete/teacher-subject?teacherId=${teacher.getTeacherId()}&subjectId=${subject.getSubjectId()}' />">Remove from subject</a></td>
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
            <th>Grade details...</th>
            <c:if test="${userRole eq 'teacher'}">
                <th>Remove from subject</th>
            </c:if>

        </tr>
        <c:forEach var="e" items="${studentSubjectMap.entrySet()}">
            <tr>
                <td>${e.getKey().getFirstName()}</td>
                <td>${e.getKey().getLastName()}</td>
                <td>${e.getValue().getTotalGrade()}</td>
                <td>${e.getValue().getTotalGrade()/subject.getMaxGrade()*100}%</td>
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
    </table>
    <!-- tasks -->
    <c:if test="${userRole eq 'teacher'}">
        <a href="<c:url value='/redirect/add/task?subjectId=${subject.getSubjectId()}' />">Add Task</a>
    </c:if>
    <table>
        <tr>
            <th>Task Name</th>
            <th>Max Grade</th>
            <c:if test="${userRole eq 'teacher'}">
                <th>Edit</th>
                <th>Delete</th>
            </c:if>

        </tr>
        <c:forEach var="task" items="${taskList}">
            <tr>
                <td>${task.getTaskName()}</td>
                <td>${task.getMaxGrade()}</td>
                <c:if test="${userRole eq 'teacher'}">
                    <td><a href="<c:url value='/redirect/edit/task?taskId=${task.getTaskId()}' />">Edit</a></td>
                    <td><a href="<c:url value='/delete/task?taskId=${task.getTaskId()}&subjectId=${task.getSubjectId()}' />">Delete</a></td>
                </c:if>

            </tr>
        </c:forEach>
    </table>

</div>
<jsp:include page="footer.jsp" />
</body>
</html>