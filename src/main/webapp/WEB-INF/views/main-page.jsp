<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main</title>
    <script>
        function showResult(str) {
            if (str.length==0) {
                document.getElementById("searchResultId").innerHTML="";
                document.getElementById("searchResultId").style.border="0px";
                return;
            }

            var xmlhttp=new XMLHttpRequest();
            xmlhttp.onreadystatechange=function() {
                if (this.readyState==4 && this.status==200) {
                    document.getElementById("searchResultId").innerHTML=this.responseText;
                    document.getElementById("searchResultId").style.border="1px solid #A5ACB2";
                }
            }

            var e = document.getElementById("searchTypeId");
            var searchType = e.value;
            var url = "";
            if(searchType == "byStudent") { //check then ===
                url = "/Lab3GradeBook/search/by-student-full-name?studentFullName=";
            } else if(searchType == "byGroup") { //check then ===
                url = "/Lab3GradeBook/search/by-group-name?groupName=";
            } else {
                url = "/Lab3GradeBook/search/by-subject-name?subjectName=";
            }

            xmlhttp.open("GET",url+str,true);
            xmlhttp.send();
        }

        /*function showAllResult(type) {
            if (str.length==0) {
                document.getElementById("searchResultId").innerHTML="";
                document.getElementById("searchResultId").style.border="0px";
                return;
            }

            var xmlhttp=new XMLHttpRequest();
            xmlhttp.onreadystatechange=function() {
                if (this.readyState==4 && this.status==200) {
                    document.getElementById("searchResultId").innerHTML=this.responseText;
                    document.getElementById("searchResultId").style.border="1px solid #A5ACB2";
                }
            }

            var e = document.getElementById("searchTypeId");
            var searchType = e.value;
            var url = "";
            if(searchType == "byStudent") { //check then ===
                url = "./search/by-student-full-name?studentFullName=";
            } else if(searchType == "byGroup") { //check then ===
                url = "./search/by-group-name?groupName=";
            } else {
                url = "./search/by-subject-name?subjectName=";
            }

            xmlhttp.open("GET",url+str,true);
            xmlhttp.send();
        }*/
    </script>
</head>
<body>
<header>
    <%
        String username=(String)session.getAttribute("current_username");
        String userId=(String)session.getAttribute("current_user_id");
        String userRole=(String)session.getAttribute("current_user_role");
    %>
    <div>
        <!-- will be ajax-->
        <form name="search_form"> <!--method="POST"-->
            <label> Search Type:
                <select name="searchType" id="searchTypeId">
                    <option value="byGroup">By Group</option>
                    <option value="byStudent" selected="selected">By Student</option>
                    <option value="bySubject">By Subject</option>
                </select>
                <input type="text" name="search-box" id="searchBoxId" placeholder="Search" size="30" onkeyup="showResult(this.value)"/>
                <div id="searchResultId"></div>
            </label>

            <!--<input type="button" value="All Student List">
            <input type="button" value="All Group List">
            <input type="button" value="All Subject List">-->
        </form>
    </div>
    <div>
        <c:url value='/redirect/profile' var="profileURL">
            <c:param name="userId" value="<%=userId%>"/>
            <c:param name="userRole" value="<%=userRole%>"/>
        </c:url>
        <c:url value='/logout' var="logoutURL"/>
        <c:url value='/show/student-all' var="allStudentURL"/>
        <c:url value='/show/group-all' var="allGroupURL"/> <!-- group-list.jsp hasn't exist yet!-->
        <c:url value='/show/subject-all' var="allSubjectURL"/>
        <c:url value='/show/teacher-all' var="allTeacherURL"/>
        <a href="<c:out value="${allStudentURL}"/>">All Student List</a>
        <a href="<c:out value="${allGroupURL}"/>">All Group List</a>
        <a href="<c:out value="${allSubjectURL}"/>">All Subject List</a>
        <a href="<c:out value="${allTeacherURL}"/>">All Teacher List</a>
        <a href="<c:out value="${profileURL}"/>"><%=username%></a>
        <a href="<c:out value="${logoutURL}"/>">Logout</a>
    </div>
</header>
<div id="MainTable">
</div>
</body>
</html>
