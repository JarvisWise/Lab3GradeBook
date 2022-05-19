<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error</title>
</head>

<%
    String username=(String)session.getAttribute("current_username");
    String userId=(String)session.getAttribute("current_user_id");
    String userRole=(String)session.getAttribute("current_user_role");
%>
<c:url value='/redirect/profile' var="profileURL">
    <c:param name="userId" value="<%=userId%>"/>
    <c:param name="userRole" value="<%=userRole%>"/>
</c:url>

<body>
<c:choose>
    <c:when test="${empty ExceptionMessage}">
        <div>
            <p>Error: ${ExceptionMessage}</p>
            <label>Return to profile: <a href="<c:out value="${profileURL}"/>"><%=username%></a></label>
        </div>
    </c:when>
    <c:otherwise>
        <div>
            <p>Unexpected error!</p>
            <label>Return to profile: <a href="<c:out value="${profileURL}"/>"><%=username%></a></label>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>