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
<!-- TO DO: add some short student&subject info-->
<div>

</div>
<table>
    <tr>
        <th>Task Name</th>
        <th>Max Grade</th>
        <th>Grade</th>
    </tr>
    <c:forEach var="st" items="${studentTaskMap.entrySet()}">
        <tr>
            <td>${st.getKey().getTaskName()}</td>
            <td>${st.getKey().getMaxGrade()}</td>
            <c:choose>
                <c:when test="${userRole eq 'teacher'}">
                    <td><form action="<c:url value='/edit/student-task?taskId=${st.getValue().getTaskId()}&studentSubjectId=${st.getValue().getStudentSubjectId()}' />" method="POST"><!-- -->
                        <input type="text" name="grade" required value="${st.getValue().getGrade()}" placeholder="Enter Grade">
                        <input type="submit" value="Edit">
                    </form></td>
                </c:when>
                <c:otherwise>
                    <td>${st.getValue().getGrade()}</td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>
<jsp:include page="footer.jsp" />
</body>
</html>