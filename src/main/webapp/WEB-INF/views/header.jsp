<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
    function showResult(str) {
        if (str.length==0) {
            document.getElementById("searchResultId").innerHTML="";
            //document.getElementById("searchResultId").style.border="0px"; display: block;
            document.getElementById("searchResultId").style.display="none";
            return;
        }

        var xmlhttp=new XMLHttpRequest();
        xmlhttp.onreadystatechange=function() {
            if (this.readyState==4 && this.status==200) {
                document.getElementById("searchResultId").innerHTML=this.responseText;
                //document.getElementById("searchResultId").style.border="1px solid #A5ACB2";
                document.getElementById("searchResultId").style.display="block";
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
    /*$(document).ready(function(){
        $('[data-toggle="popover"]').popover();
    });*/
</script>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <style>
        .alert {border-radius: 0 !important;}
        .row.content.my-c {background: #f1f1f1;}
        body {background-color: #f1f1f1}
        td.desc-my {max-width: 300px;}
        td.grade-my {max-width: 150px;}
        th.alert.alert-info {width: 200px;}
        .jumbotron {margin-bottom: 0;}

        footer {
            background-color: #555;
            color: white;
            padding: 15px;
        }

        .navbar {
            margin-bottom: 0;
            border-radius: 0;
        }

        .col-xs-8.col-xs-offset-2.text-left.my {
            background: #fff;
            min-height: calc(100vh - 112px);
        }

        .row.content {
            height: available;
            min-height: 640px;
            /*padding-top: 20px;*/
            background-color: #ffffff;
        }

        div.well {
            padding: 9px 19px 9px;
            margin: 5px 0 5px;
        }

        .table-bordered.tb-my {
            background-color: #ffffff;
            padding: 0;
            margin: 0;
            /*text-align: center;*/
            border-radius: 15px !important;
        }

        .profile {
            padding: 0;
            margin: 0 auto;
            text-align: center;
        }

        .form-group.input-group.my,
        .form-group.my,
        form.my {margin-bottom: 0;}

        .sidenav {
            padding-top: 20px;
            background-color: #f1f1f1;
            max-height: 10000px; /*find way*/
            min-height: 640px;
        }

        /*.dropdown-menu.my-l {
            display: block;
        }*/

        @media screen and (max-width: 767px) {
            .sidenav {
                height: auto;
                padding: 15px;
            }
            .row.content {height:auto;}
        }
    </style>
</head>

<%
    String username=(String)session.getAttribute("current_username");
    String userId=(String)session.getAttribute("current_user_id");
    String userRole=(String)session.getAttribute("current_user_role");
%>
<c:url value='/redirect/profile' var="profileURL">
    <c:param name="userId" value="<%=userId%>"/>
    <c:param name="userRole" value="<%=userRole%>"/>
</c:url>
<c:url value='/logout' var="logoutURL"/>
<c:url value='/show/student-all' var="allStudentURL"/>
<c:url value='/show/group-all' var="allGroupURL"/>
<c:url value='/show/subject-all' var="allSubjectURL"/>
<c:url value='/show/teacher-all' var="allTeacherURL"/>

<!--<script src="searchMain.js"></script>-->
<header>
    <nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="">GradeBook</a>
        </div>

        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="<c:out value="${allStudentURL}"/>">Students</a></li>
                <li><a href="<c:out value="${allGroupURL}"/>">Groups</a></li>
                <li><a href="<c:out value="${allSubjectURL}"/>">Subjects</a></li>
                <li><a href="<c:out value="${allTeacherURL}"/>">Teachers</a></li>
            </ul>
            <div class="nav navbar-nav">
                <form name="search_form" class="navbar-form " role="search">
                    <div class="form-group">
                        <select class="form-control" name="searchType" id="searchTypeId">
                            <option value="" disabled>Choose search type</option>
                            <option value="byGroup">Search By Group</option>
                            <option value="byStudent" selected="selected">Search By Student</option>
                            <option value="bySubject">Search By Subject</option>
                        </select>
                        <div style="display: inline-block;">
                            <input type="text" class="form-control" name="search-box" id="searchBoxId" placeholder="Search.." size="30" onkeyup="showResult(this.value)"/>
                            <div class="drop-my dropdown" >
                                <ul class="dropdown-menu my-l" id="searchResultId">
                                </ul>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="<c:out value="${profileURL}"/>">
                    <span class="glyphicon glyphicon-user"></span> <%=username%></a>
                </li>
                <li>
                    <a href="<c:out value="${logoutURL}"/>">
                    <span class="glyphicon glyphicon-log-out"></span> Logout</a>
                </li>
            </ul>
        </div>
    </div>
    </nav>
</header>
