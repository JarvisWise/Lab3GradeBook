<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${action}</title>
</head>

<body>
    <c:url value='/show/student-all' var="allStudentURL"/>
    <jsp:include page="header.jsp" />
    <div>
        <c:choose>
            <c:when test="${action eq 'edit'}">
            <form action="<c:url value='/${action}/student/${studentInfoSet.getStudent().getStudentId()}' />" method="POST">
                <label>Login Name<input type="text" name="loginName" required value="${studentInfoSet.getStudent().getLoginName()}" placeholder="Enter Login Name"></label><br>
                <label>First Name<input type="text" name="firstName" required value="${studentInfoSet.getStudent().getFirstName()}" placeholder="Enter First Name"></label><br>
                <label>Last Name<input type="text" name="lastName" required value="${studentInfoSet.getStudent().getLastName()}" placeholder="Enter Last Name"></label><br>
                <!--headman-->
                <label> Headman
                    <select name="headman">
                        <c:choose>
                            <c:when test="${empty studentInfoSet.getStudent().getHeadman()}">
                                <option value="NotYet" selected="selected">no headman yet</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${studentInfoSet.getHeadman().getStudentId()}" selected="selected">${studentInfoSet.getHeadman().getFullName()}</option>
                                <option value="NotYet">no headman yet</option>
                            </c:otherwise>
                        </c:choose>

                        <c:forEach var="headman" items="${headmanList}">
                            <option value="${headman.getStudentId()}">${headman.getFullName()}</option>
                        </c:forEach>
                    </select>
                </label><br>
                <!--group-->
                <label> Group
                    <select name="groupId">
                        <c:choose>
                            <c:when test="${empty studentInfoSet.getStudent().getGroupId()}">
                                <option value="NotYet" selected="selected">no headman yet</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${studentInfoSet.getGroup().getGroupId()}" selected="selected">${studentInfoSet.getGroup().getGroupName()}</option>
                                <option value="NotYet">no headman yet</option>
                            </c:otherwise>
                        </c:choose>

                        <c:forEach var="group" items="${groupList}">
                            <option value="${group.getGroupId()}">${group.getGroupName()}</option>
                        </c:forEach>
                    </select>
                </label><br>
                <!-- edit can only owner of acc -->
                <!--<label>Password<input type="text" name="password" required value="$-{studentInfoSet.getStudent().getPassword()}" placeholder="Enter Password"></label><br>-->
            </c:when>
            <c:otherwise>
                <form action="<c:url value='/${action}/student' />" method="POST">
                    <label>Login Name<input type="text" name="loginName" required value="" placeholder="Enter Login Name"></label><br>
                    <label>First Name<input type="text" name="firstName" required value="" placeholder="Enter First Name"></label><br>
                    <label>Last Name<input type="text" name="lastName" required value="" placeholder="Enter Last Name"></label><br>
                    <!--group-->
                    <label> Headman
                        <select name="headman">
                            <option value="NotYet" selected="selected">no headman yet</option>
                            <c:forEach var="headman" items="${headmanList}">
                                <option value="${headman.getStudentId()}">${headman.getFullName()}</option>
                            </c:forEach>
                        </select>
                    </label><br>
                    <!--headman-->
                    <label> Group
                        <select name="groupId">
                            <option value="NotYet" selected="selected">no group yet</option>
                            <c:forEach var="group" items="${groupList}">
                                <option value="${group.getGroupId()}">${group.getGroupName()}</option>
                            </c:forEach>
                        </select>
                    </label><br>
                    <label>Password<input type="text" name="password" required value="" placeholder="Enter Password"></label><br>
            </c:otherwise>
        </c:choose>
        <input type="submit" value="${action}">
            <c:choose>
                <c:when test="${action eq 'edit'}">
                    <a href="<c:url value='/show/student?studentId=${studentInfoSet.getStudent().getStudentId()}'/>">Cancel</a>
                </c:when>
                <c:otherwise>
                    <a href="<c:out value="${allStudentURL}"/>">Cancel</a>
                </c:otherwise>
            </c:choose>
        </form>
    </div>
    <jsp:include page="footer.jsp" />
</body>
</html>
