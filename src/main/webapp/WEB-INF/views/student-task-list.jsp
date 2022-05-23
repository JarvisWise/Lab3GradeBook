<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Subjects</title>
</head>
<body>
<%String userRole=(String)session.getAttribute("current_user_role");%>
<c:set var = "userRole" value = "<%=userRole%>"/>
<jsp:include page="header.jsp" />

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav">
        </div>
        <div class="col-sm-8 text-left">
            <!-- some short student&subject info-->
            <div> <!--NEED?-->
                <p>Student: <a href="<c:url value='/show/student?studentId=${student.getStudentId()}'/>" class="btn btn-default">${student.getFullNameWithEmail()}</a></p>
                <p>Subject: <a href="<c:url value='/show/subject?subjectId=${subject.getSubjectId()}'/>" class="btn btn-default">${subject.getSubjectName()}</a></p>
            </div>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Task Name</th>
                        <th>Description</th>
                        <th>Max Grade</th>
                        <th>Grade</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="st" items="${studentTaskMap.entrySet()}">
                        <tr>
                            <td>${st.getKey().getTaskName()}</td>
                            <td><a href="#" title="Task Description" data-toggle="popover" data-placement="right" data-content="${st.getKey().getTaskDescription()}">details..</a></td>
                            <td>${st.getKey().getMaxGrade()}</td>
                            <c:choose>
                                <c:when test="${userRole eq 'teacher'}">
                                    <td>
                                        <form action="<c:url value='/edit/student-task?taskId=${st.getValue().getTaskId()}&studentSubjectId=${st.getValue().getStudentSubjectId()}' />" method="POST"><!-- -->
                                            <div class="form-group input-group">
                                                <input type="text" class="form-control" name="grade" required value="${st.getValue().getGrade()}" placeholder="Enter Grade">
                                                <span class="input-group-btn">
                                                    <button type="submit" class="btn btn-default">Save</button>
                                                </span>
                                            </div>
                                        </form>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>${st.getValue().getGrade()}</td>
                                </c:otherwise>
                            </c:choose>
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