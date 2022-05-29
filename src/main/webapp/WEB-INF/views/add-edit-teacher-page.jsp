<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${action}</title>
</head>

<body>
<c:url value='/show/teacher-all' var="allTeacherURL"/>
<jsp:include page="header.jsp" />
<div class="container-fluid text-center">
    <div class="row content my-c">
        <div class="col-xs-8 col-xs-offset-2 text-left my">
            <c:choose>
                <c:when test="${action eq 'edit'}">
                    <form action="<c:url value='/${action}/teacher/${teacher.getTeacherId()}' />" method="POST">
                        <div class="form-group">
                            <label for="loginName">Email: </label>
                            <input type="text" name="loginName" id="loginName" class="form-control" required value="${teacher.getEmail()}" placeholder="Enter Email"/>
                        </div>
                        <div class="form-group">
                            <label for="firstName">First name: </label>
                            <input type="text" name="firstName" id="firstName" class="form-control" required value="${teacher.getFirstName()}" placeholder="Enter First Name"/>
                        </div>
                        <div class="form-group">
                            <label for="lastName">Last name: </label>
                            <input type="text" name="lastName" id="lastName" class="form-control" required value="${teacher.getLastName()}" placeholder="Enter Last Name"/>
                        </div>
                <!-- edit can only owner of acc -->
                <!--<label>Password<input type="text" name="password" required value="$-{studentInfoSet.getStudent().getPassword()}" placeholder="Enter Password"></label><br>-->
                </c:when>
                <c:otherwise>
                        <form action="<c:url value='/${action}/teacher' />" method="POST">
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
                            <div class="form-group">
                                <label for="password">Password: </label>
                                <input type="text" name="password" id="password" class="form-control" required value="" placeholder="Enter Password"/>
                            </div>
                </c:otherwise>
            </c:choose>
            <button type="submit" class="btn btn-default">${action}</button>
                <c:choose>
                    <c:when test="${action eq 'edit'}">
                        <a href="<c:url value='/show/teacher?teacherId=${teacher.getTeacherId()}'/>" class="btn btn-default">Cancel</a>
                    </c:when>
                    <c:otherwise>
                        <a href="<c:out value="${allTeacherURL}"/>" class="btn btn-default">Cancel</a>
                    </c:otherwise>
                </c:choose>
            </form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
