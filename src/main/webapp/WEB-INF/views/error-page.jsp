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
<jsp:include page="header.jsp" />
<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav">
        </div>
        <div class="col-sm-8 text-left">
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
        </div>
        <div class="col-sm-2 sidenav">
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>