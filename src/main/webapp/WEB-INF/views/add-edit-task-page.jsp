<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${action} Task</title>
</head>

<body>
<jsp:include page="header.jsp" />
<div>
    <c:choose>
        <c:when test="${action eq 'edit'}">
            <form action="<c:url value='/${action}/task?subjectId=${task.getSubjectId()}&taskId=${task.getTaskId()}' />" method="POST">
                <label>Task Name<input type="text" name="taskName" required value="${task.getTaskName()}" placeholder="Enter Task Name"></label><br>
                <label>Max Grade<input type="text" name="maxGrade" required value="${task.getMaxGrade()}" placeholder="Enter Max Grade"></label><br>
                <!--<label>Task Description<input type="text" name="taskDescription" required value="$--{task.getTaskDescription()}" placeholder="Enter Task Description"></label><br>-->
                <label>Task Description<textarea name="taskDescription"
                    cols="30"
                    rows="5"
                    maxlength="4000"
                    placeholder="Enter Task Description" required>${task.getTaskDescription()}</textarea></label><br>
        </c:when>
        <c:otherwise>
            <form action="<c:url value='/${action}/task?subjectId=${subject.getSubjectId()}' />" method="POST">
                <label>Task Name<input type="text" name="taskName" required value="" placeholder="Enter Task Name"></label><br>
                <label>Max Grade<input type="text" name="maxGrade" required value="" placeholder="Enter Max Grade"></label><br>
                <!--<label>Task Description<input type="text" name="taskDescription" required value="" placeholder="Enter Task Description"></label><br>-->
                <label>Task Description<textarea name="taskDescription"
                    cols="30"
                    rows="5"
                    maxlength="4000"
                    placeholder="Enter Task Description" required></textarea></label><br>
        </c:otherwise>
    </c:choose>
        <input type="submit" value="${action}">
            <c:choose>
                <c:when test="${action eq 'edit'}">
                    <a href="<c:url value='/show/subject?subjectId=${task.getSubjectId()}'/>">Cancel</a>
                </c:when>
                <c:otherwise>
                    <a href="<c:out value="/show/subject?subjectId=${subject.getSubjectId()}"/>">Cancel</a>
                </c:otherwise>
            </c:choose>
        </form>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
