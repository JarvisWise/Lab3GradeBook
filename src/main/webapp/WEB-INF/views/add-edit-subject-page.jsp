<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${action}</title>
</head>
<c:url value='/show/subject-all' var="allSubjectURL"/>
<body>
    <jsp:include page="header.jsp" />
    <div class="container-fluid text-center">
        <div class="row content my-c">
            <div class="col-xs-8 col-xs-offset-2 text-left my">
                <c:choose>
                    <c:when test="${action eq 'edit'}">
                        <form action="<c:url value='/${action}/subject/${subject.getSubjectId()}' />" method="POST">
                            <div class="form-group">
                                <label for="subjectName">Subject name: </label>
                                <input type="text" name="subjectName" id="subjectName" class="form-control" required value="${subject.getSubjectName()}" placeholder="Enter Subject Name">
                            </div>
                            <div class="form-group">
                                <label for="maxGrade">Max grade: </label>
                                <input type="text" name="maxGrade" id="maxGrade" class="form-control" required value="${subject.getMaxGrade()}" placeholder="Enter Max Grade">
                            </div>
                            <div class="form-group">
                                <label for="passProcGrade">Pass grade(%): </label>
                                <input type="text" name="passProcGrade" id="passProcGrade" class="form-control" required value="${subject.getPassProcGradeP()}" placeholder="Enter Pass Proc Grade">
                            </div>
                                <!--<label>Subject Description<input type="text" name="subjectDescription" required value="$--{subject.getSubjectDescription()}" placeholder="Enter Subject Description"></label><br>-->
                            <div class="form-group">
                                <label for="subjectDescription">Subject description: </label>
                                <textarea name="subjectDescription"
                                    id="subjectDescription"
                                    class="form-control"
                                    cols="30"
                                    rows="5"
                                    maxlength="1000"
                                    placeholder="Enter Subject Description" required>${subject.getSubjectDescription()}</textarea>
                            </div>
                    </c:when>
                    <c:otherwise>
                        <form action="<c:url value='/${action}/subject' />" method="POST">
                            <div class="form-group">
                                <label for="subjectNameA">Subject name: </label>
                                <input type="text" name="subjectName" id="subjectNameA" class="form-control" required value="" placeholder="Enter Subject Name">
                            </div>
                            <div class="form-group">
                                <label for="maxGradeA">Max grade: </label>
                                <input type="text" name="maxGrade" id="maxGradeA" class="form-control" required value="" placeholder="Enter Max Grade">
                            </div>
                            <div class="form-group">
                                <label for="passProcGradeA">Pass grade(%): </label>
                                <input type="text" name="passProcGrade" id="passProcGradeA" class="form-control" required value="" placeholder="Enter Pass Proc Grade">
                            </div>
                            <!--<label>Subject Description<input type="text" name="subjectDescription" required value="$--{subject.getSubjectDescription()}" placeholder="Enter Subject Description"></label><br>-->
                            <div class="form-group">
                                <label for="subjectDescriptionA">Subject description: </label>
                                <textarea name="subjectDescription"
                                          id="subjectDescriptionA"
                                          class="form-control"
                                          cols="30"
                                          rows="5"
                                          maxlength="1000"
                                          placeholder="Enter Subject Description" required></textarea>
                            </div>
                    </c:otherwise>
                </c:choose>
                <button type="submit" class="btn btn-default">${action}</button>
                    <c:choose>
                        <c:when test="${action eq 'edit'}">
                            <a href="<c:url value='/show/subject?subjectId=${subject.getSubjectId()}'/>" class="btn btn-default">Cancel</a>
                        </c:when>
                        <c:otherwise>
                            <a href="<c:out value="${allSubjectURL}"/>" class="btn btn-default">Cancel</a>
                        </c:otherwise>
                    </c:choose>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp" />
</body>
</html>
