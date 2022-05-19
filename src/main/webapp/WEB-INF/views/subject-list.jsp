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
<c:if test="${userRole eq 'teacher'}">
    <a href="<c:url value='/redirect/add/subject' />">Add new subject</a>
</c:if>
<table>
    <tr>
        <th>ID</th>
        <th>Subject Name</th>
        <th>Max Grade</th>
        <th>Pass Grade</th>
        <th>Subject Description</th>
        <th>More...</th>
    </tr>
    <c:forEach var="subject" items="${subjects}">
        <tr>
            <td>${subject.getSubjectId()}</td>
            <td>${subject.getSubjectName()}</td>
            <td>${subject.getMaxGrade()}</td>
            <td>${subject.getPassProcGradeP()}%</td>
            <td>${subject.getSubjectDescription()}%</td>
            <c:url value='/show/subject' var="subjectURL">
                <c:param name="subjectId" value="${subject.getSubjectId()}"/>
            </c:url>
            <td><a href="<c:out value="${subjectURL}"/>">More...</a></td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="footer.jsp" />
</body>
</html>
