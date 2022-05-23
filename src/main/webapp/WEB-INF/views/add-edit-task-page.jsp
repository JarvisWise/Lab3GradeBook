<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${action} Task</title>
</head>

<body>
<jsp:include page="header.jsp" />
<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav">
        </div>
        <div class="col-sm-8 text-left">
        <c:choose>
            <c:when test="${action eq 'edit'}">
                <form action="<c:url value='/${action}/task?subjectId=${task.getSubjectId()}&taskId=${task.getTaskId()}' />" method="POST">
                    <div class="form-group">
                        <label for="taskName">Task name: </label>
                        <input type="text" class="form-control" name="taskName" id="taskName" required value="${task.getTaskName()}" placeholder="Enter Task Name"/>
                    </div>
                    <div class="form-group">
                        <label for="maxGrade">Task name: </label>
                        <input type="text" class="form-control" name="maxGrade" id="maxGrade" required value="${task.getMaxGrade()}" placeholder="Enter Max Grade">
                    </div>
                        <!--<label>Task Description<input type="text" name="taskDescription" required value="$--{task.getTaskDescription()}" placeholder="Enter Task Description"></label><br>-->
                    <div class="form-group">
                        <label for="taskDescription">Task description: </label>
                        <textarea name="taskDescription"
                            id="taskDescription"
                            class="form-control"
                            cols="30"
                            rows="5"
                            maxlength="4000"
                            placeholder="Enter Task Description" required>${task.getTaskDescription()}</textarea>
                    </div>
            </c:when>
            <c:otherwise>
                <form action="<c:url value='/${action}/task?subjectId=${subject.getSubjectId()}' />" method="POST">
                    <div class="form-group">
                        <label for="taskNameA">Task name: </label>
                        <input type="text" class="form-control" name="taskName" id="taskNameA" required value="" placeholder="Enter Task Name">
                    </div>
                    <div class="form-group">
                        <label for="maxGradeA">Task name: </label>
                        <input type="text" class="form-control" name="maxGrade" id="maxGradeA" required value="" placeholder="Enter Max Grade">
                    </div>
                        <!--<label>Task Description<input type="text" name="taskDescription" required value="" placeholder="Enter Task Description"></label><br>-->
                    <div class="form-group">
                        <label for="taskDescriptionA">Task description: </label>
                        <textarea name="taskDescription"
                        id="taskDescriptionA"
                        class="form-control"
                        cols="30"
                        rows="5"
                        maxlength="4000"
                        placeholder="Enter Task Description" required></textarea>
                    </div>
            </c:otherwise>
        </c:choose>
            <button type="submit" class="btn btn-default">${action}</button>
                <c:choose>
                    <c:when test="${action eq 'edit'}">
                        <a href="<c:url value='/show/subject?subjectId=${task.getSubjectId()}'/>" class="btn btn-default">Cancel</a>
                    </c:when>
                    <c:otherwise>
                        <a href="<c:out value="/show/subject?subjectId=${subject.getSubjectId()}"/>" class="btn btn-default">Cancel</a>
                    </c:otherwise>
                </c:choose>
            </form>
        </div>
        <div class="col-sm-2 sidenav">
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
