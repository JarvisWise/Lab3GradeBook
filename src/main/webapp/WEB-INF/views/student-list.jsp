<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Students</title>
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
            <div class="well">
                <c:if test="${userRole eq 'teacher'}">
                    <a href="<c:url value='/redirect/add/student' />" class="btn btn-default">Add new student</a>
                </c:if>
            </div>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Email</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Headman</th>
                        <th>Group</th>
                        <th>More...</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="studentInfoSet" items="${studentInfoSetList}">
                        <tr>
                            <td>${studentInfoSet.getStudent().getEmail()}</td>
                            <td>${studentInfoSet.getStudent().getFirstName()}</td>
                            <td>${studentInfoSet.getStudent().getLastName()}</td>
                            <c:choose>
                                <c:when test="${empty studentInfoSet.getStudent().getHeadman()}">
                                    <td>(not yet)</td>
                                </c:when>
                                <c:otherwise>
                                    <c:url value='/redirect/profile' var="studentHeadmanURL">
                                        <c:param name="userId" value="${studentInfoSet.getHeadman().getStudentId()}"/>
                                        <c:param name="userRole" value="${studentInfoSet.getHeadman().getRole()}"/>
                                    </c:url>
                                    <td><a href="<c:out value="${studentHeadmanURL}"/>">${studentInfoSet.getHeadman().getFullName()}</a></td>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${empty studentInfoSet.getStudent().getGroupId()}">
                                    <td>(not yet)</td>
                                </c:when>
                                <c:otherwise>
                                    <c:url value='/show/group' var="studentGroupURL">
                                        <c:param name="groupId" value="${studentInfoSet.getGroup().getGroupId()}"/>
                                    </c:url>
                                    <td><a href="<c:out value="${studentGroupURL}"/>">${studentInfoSet.getGroup().getGroupName()}</a></td>
                                </c:otherwise>
                            </c:choose>

                            <c:url value='/redirect/profile' var="studentURL">
                                <c:param name="userId" value="${studentInfoSet.getStudent().getStudentId()}"/>
                                <c:param name="userRole" value="${studentInfoSet.getStudent().getRole()}"/>
                            </c:url>
                            <td><a href="<c:out value="${studentURL}"/>">More...</a></td>
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
