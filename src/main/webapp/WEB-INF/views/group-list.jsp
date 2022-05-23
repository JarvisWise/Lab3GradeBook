<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Groups</title>
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
                    <a href="<c:url value='/redirect/add/group' />" class="btn btn-default">Add new group</a>
                </c:if>
            </div>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <!--<th>ID</th>-->
                        <th>Group Name</th>
                        <th>Group Description</th>
                        <th>More...</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="group" items="${groups}">
                    <tr>
                        <!--<td>$--{group.getGroupId()}</td>-->
                        <td>${group.getGroupName()}</td>
                        <td>${group.getGroupDescription()}</td>
                        <c:url value='/show/group' var="groupURL">
                            <c:param name="groupId" value="${group.getGroupId()}"/>
                        </c:url>
                        <td><a href="<c:out value="${groupURL}"/>">More...</a></td>
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
