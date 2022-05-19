<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${action}</title>
</head>

<body>
    <c:url value='/show/subject-all' var="allSubjectURL"/>
    <jsp:include page="header.jsp" />
    <div>
        <c:choose>
            <c:when test="${action eq 'edit'}">
                <form action="<c:url value='/${action}/subject/${subject.getSubjectId()}' />" method="POST">
                    <label>Subject Name<input type="text" name="subjectName" required value="${subject.getSubjectName()}" placeholder="Enter Subject Name"></label><br>
                    <label>Max Grade<input type="text" name="maxGrade" required value="${subject.getMaxGrade()}" placeholder="Enter Max Grade"></label><br>
                    <label>Pass Proc Grade<input type="text" name="passProcGrade" required value="${subject.getPassProcGradeP()}" placeholder="Enter Pass Proc Grade"></label><br>
                    <!--<label>Subject Description<input type="text" name="subjectDescription" required value="$--{subject.getSubjectDescription()}" placeholder="Enter Subject Description"></label><br>-->
                    <label>Subject Description<textarea name="subjectDescription"
                        cols="30"
                        rows="5"
                        maxlength="1000"
                        placeholder="Enter Subject Description" required>${subject.getSubjectDescription()}</textarea></label><br>
            </c:when>
            <c:otherwise>
                <form action="<c:url value='/${action}/subject' />" method="POST">
                    <label>Subject Name<input type="text" name="subjectName" required value="" placeholder="Enter Subject Name"></label><br>
                    <label>Max Grade<input type="text" name="maxGrade" required value="" placeholder="Enter Max Grade"></label><br>
                    <label>Pass Proc Grade<input type="text" name="passProcGrade" required value="" placeholder="Enter Pass Proc Grade"></label><br>
                    <!--<label>Subject Description<input type="text" name="subjectDescription" required value="" placeholder="Enter Subject Description"></label><br>-->
                    <label>Subject Description<textarea name="subjectDescription"
                        cols="30"
                        rows="5"
                        maxlength="1000"
                        placeholder="Enter Subject Description" required></textarea></label><br>
            </c:otherwise>
        </c:choose>
        <input type="submit" value="${action}">
            <c:choose>
                <c:when test="${action eq 'edit'}">
                    <a href="<c:url value='/show/subject?subjectId=${subject.getSubjectId()}'/>">Cancel</a>
                </c:when>
                <c:otherwise>
                    <a href="<c:out value="${allSubjectURL}"/>">Cancel</a>
                </c:otherwise>
            </c:choose>
        </form>
    </div>
    <jsp:include page="footer.jsp" />
</body>
</html>
