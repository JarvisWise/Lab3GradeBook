<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${action}</title>
</head>

<body>
    <c:url value='/show/student-all' var="allStudentURL"/>
    <jsp:include page="header.jsp" />
    <div class="container-fluid text-center">
        <div class="row content my-c">
            <div class="col-xs-8 col-xs-offset-2 text-left my">
        <c:choose>
            <c:when test="${action eq 'edit'}">
            <form action="<c:url value='/${action}/student/${studentInfoSet.getStudent().getStudentId()}' />" method="POST">
                <div class="form-group">
                    <label for="loginName">Email: </label>
                    <input type="text" name="loginName" id="loginName" class="form-control" required value="${studentInfoSet.getStudent().getEmail()}" placeholder="Enter Email"/>
                </div>
                <div class="form-group">
                    <label for="firstName">First name: </label>
                    <input type="text" name="firstName" id="firstName" class="form-control" required value="${studentInfoSet.getStudent().getFirstName()}" placeholder="Enter First Name"/>
                </div>
                <div class="form-group">
                    <label for="lastName">Last name: </label>
                    <input type="text" name="lastName" id="lastName" class="form-control" required value="${studentInfoSet.getStudent().getLastName()}" placeholder="Enter Last Name"/>
                </div>
                <!--headman-->
                <div class="form-group">
                    <label for="headman">Headman: </label>
                    <select name="headman" id="headman" class="form-control">
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
                </div>
                <!--group-->
                <div class="form-group">
                    <label for="groupId">Group: </label>
                    <select name="groupId" id="groupId" class="form-control">
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
                </div>
                <!-- edit can only owner of acc -->
                <!--<label>Password<input type="text" name="password" required value="$-{studentInfoSet.getStudent().getPassword()}" placeholder="Enter Password"></label><br>-->
            </c:when>
            <c:otherwise>
                <form action="<c:url value='/${action}/student' />" method="POST">
                    <div class="form-group">
                        <label for="loginNameA">Email: </label>
                        <input type="text" name="loginName" id="loginNameA" class="form-control" required value="" placeholder="Enter Email"/>
                    </div>
                    <div class="form-group">
                        <label for="firstNameA">First name: </label>
                        <input type="text" name="firstName" id="firstNameA" class="form-control" required value="" placeholder="Enter First Name"/>
                    </div>
                    <div class="form-group">
                        <label for="lastNameA">Last name: </label>
                        <input type="text" name="lastName" id="lastNameA" class="form-control" required value="" placeholder="Enter Last Name"/>
                    </div>
                    <!--headman-->
                    <!--group-->
                    <div class="form-group">
                        <label for="headmanA">Headman: </label>
                        <select name="headman" id="headmanA" class="form-control">
                            <option value="NotYet" selected="selected">no headman yet</option>
                            <c:forEach var="headman" items="${headmanList}">
                                <option value="${headman.getStudentId()}">${headman.getFullName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <!--group-->
                    <div class="form-group">
                        <label for="groupIdA">Group: </label>
                        <select name="groupId" id="groupIdA" class="form-control">
                            <option value="NotYet" selected="selected">no group yet</option>
                            <c:forEach var="group" items="${groupList}">
                                <option value="${group.getGroupId()}">${group.getGroupName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="password">Password: </label>
                        <input type="text" name="password" id="password" class="form-control" required value="" placeholder="Enter Password"/>
                    </div>
            </c:otherwise>
        </c:choose>
        <button type="submit" class="btn btn-default">${action}</button>
            <c:choose>
                <c:when test="${action eq 'edit'}">
                    <a href="<c:url value='/show/student?studentId=${studentInfoSet.getStudent().getStudentId()}'/>" class="btn btn-default">Cancel</a>
                </c:when>
                <c:otherwise>
                    <a href="<c:out value="${allStudentURL}"/>" class="btn btn-default">Cancel</a>
                </c:otherwise>
            </c:choose>
        </form>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp" />
</body>
</html>
