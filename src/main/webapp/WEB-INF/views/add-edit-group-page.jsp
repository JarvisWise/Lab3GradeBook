<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${action}</title>
</head>
<c:url value='/show/group-all' var="allGroupURL"/>
<body>
    <jsp:include page="header.jsp" />
    <div class="container-fluid text-center">
        <div class="row content  my-c">
            <div class="col-xs-8 col-xs-offset-2 text-left my">
                <c:choose>
                    <c:when test="${action eq 'edit'}">
                        <form action="<c:url value='/${action}/group/${group.getGroupId()}' />" method="POST">
                            <div class="form-group">
                                <label for="groupName">Group name: </label>
                                <input type="text" class="form-control" name="groupName" id="groupName" required value="${group.getGroupName()}" placeholder="Enter Group Name">
                            </div>
                                <!--<label>Group Description<input type="text" name="groupDescription" required value="$--{group.getGroupDescription()}" placeholder="Enter Group Description"></label><br>-->
                            <div class="form-group">
                                <label for="groupDescription">Group description: </label>
                                <textarea name="groupDescription"
                                id="groupDescription"
                                class="form-control"
                                cols="30"
                                rows="5"
                                maxlength="1000"
                                placeholder="Enter Group Description" required>${group.getGroupDescription()}</textarea>
                            </div>
                    </c:when>
                    <c:otherwise>
                        <form action="<c:url value='/${action}/group' />" method="POST">
                            <div class="form-group">
                                <label for="groupNameA">Group name: </label>
                                <input type="text" class="form-control" name="groupName" id="groupNameA" required value="" placeholder="Enter Group Name">
                            </div>
                                <!--<label>Group Description<input type="text" name="groupDescription" required value="" placeholder="Enter Group Description"></label><br>-->
                            <div class="form-group">
                                <label for="groupDescriptionA">Group description: </label>
                                <textarea name="groupDescription"
                                 id="groupDescriptionA"
                                 class="form-control"
                                cols="30"
                                rows="5"
                                maxlength="1000"
                                placeholder="Enter Group Description" required></textarea>
                            </div>
                    </c:otherwise>
                </c:choose>
                <button type="submit" class="btn btn-default">${action}</button>
                    <c:choose>
                        <c:when test="${action eq 'edit'}">
                            <a href="<c:url value='/show/group?groupId=${group.getGroupId()}'/>" class="btn btn-default">Cancel</a>
                        </c:when>
                        <c:otherwise>
                            <a href="<c:out value="${allGroupURL}"/>" class="btn btn-default">Cancel</a>
                        </c:otherwise>
                    </c:choose>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp" />
</body>
</html>