<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Teachers</title>
</head>
<body>
    <%String userRole=(String)session.getAttribute("current_user_role");%>
    <c:set var = "userRole" value = "<%=userRole%>"/>
    <jsp:include page="header.jsp" />
    <div class="container-fluid text-center">
        <div class="row content my-c">
            <div class="col-xs-8 col-xs-offset-2 text-left my">
                <div class="well">
                    <c:if test="${userRole eq 'teacher'}">
                        <a href="<c:url value='/redirect/add/teacher' />" class="btn btn-default">Add new teacher</a>
                    </c:if>
                </div>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Email</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>More...</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="teacher" items="${teachers}">
                            <tr>
                                <td>${teacher.getEmail()}</td>
                                <td>${teacher.getFirstName()}</td>
                                <td>${teacher.getLastName()}</td>

                                <c:url value='/redirect/profile' var="teacherURL">
                                    <c:param name="userId" value="${teacher.getTeacherId()}"/>
                                    <c:param name="userRole" value="${teacher.getRole()}"/>
                                </c:url>
                                <td><a href="<c:out value="${teacherURL}"/>">More...</a></td>
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