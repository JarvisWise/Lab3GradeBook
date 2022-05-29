<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Subjects</title>

</head>
<body>
<%
    String userRole=(String)session.getAttribute("current_user_role");
    String userId=(String)session.getAttribute("current_user_id");
%>
<c:set var = "userRole" value = "<%=userRole%>"/>
<c:set var = "userId" value = "<%=userId%>"/>
<jsp:include page="header.jsp" />

<div class="container-fluid text-center">
    <div class="row content my-c">
        <div class="col-xs-8 col-xs-offset-2 text-left my">
            <!-- some short student&subject info-->
            <!--<div> !--NEED?--
                <p>Student: <a href="c:url value='/show/student?studentId=$-{student.getStudentId()}'/>" class="btn btn-default">$-{student.getFullNameWithEmail()}</a></p>
                <p>Subject: <a href="c:url value='/show/subject?subjectId=$-{subject.getSubjectId()}'/>" class="btn btn-default">$-{subject.getSubjectName()}</a></p>
            </div>-->

            <div class="well">
                <table class="table table-bordered tb-my " >
                    <tbody>
                    <tr><th class="alert alert-info">Student</th><td class="bg-success"><a href="<c:url value='/show/student?studentId=${student.getStudentId()}'/>">${student.getFullNameWithEmail()}</a></td></tr>
                    <tr><th class="alert alert-info">Subject</th><td class="bg-success"><a href="<c:url value='/show/subject?subjectId=${subject.getSubjectId()}'/>">${subject.getSubjectName()}</a></td></tr>
                    </tbody>
                </table>
            </div>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Task Name</th>
                        <th>Description</th>
                        <th>Max Grade</th>
                        <th>Checked by</th>
                        <th>Grade</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="st" items="${studentTaskInfoMap}">
                        <tr>
                            <td>${st.getTask().getTaskName()}</td>
                            <td class="desc-my">
                                ${st.getTask().getTaskDescription()}
                                <!--<a href="#" title="Task Description" data-toggle="popover" data-placement="right" data-content="$-{st.getTask().getTaskDescription()}" data-original-title="Popover Header">details..</a>-->
                            </td>
                            <td>${st.getTask().getMaxGrade()}</td>
                            <!-- checked by teacher -->
                            <c:choose>
                                <c:when test="${empty st.getStudentTask().getByTeacherId()}">
                                    <td>(not yet)</td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="<c:url value='/show/teacher?teacherId=${st.getStudentTask().getByTeacherId()}'/>" >${st.getTeacher().getFullName()}</a></td>
                                </c:otherwise>
                            </c:choose>
                            <!-- checked by teacher -->
                            <c:choose>
                                <c:when test="${userRole eq 'teacher'}">
                                    <td class="grade-my">
                                        <form action="<c:url value='/edit/student-task/${userId}?taskId=${st.getTask().getTaskId()}&studentSubjectId=${st.getStudentTask().getStudentSubjectId()}' />" method="POST"><!-- -->
                                            <div class="form-group input-group">
                                                <input type="text" class="form-control" name="grade" required value="${st.getStudentTask().getGrade()}" placeholder="Enter Grade">
                                                <span class="input-group-btn">
                                                    <button type="submit" class="btn btn-default">Save</button>
                                                </span>
                                            </div>
                                        </form>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td class="grade-my">${st.getStudentTask().getGrade()}</td>
                                </c:otherwise>
                            </c:choose>
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