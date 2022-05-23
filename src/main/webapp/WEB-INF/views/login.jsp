<%@ page import="org.example.dao.implementations.DAOStudentImpl" %><%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 10.06.2021
  Time: 00:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
    <!-- -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <style>
        /* Set black background color, white text and some padding */
        footer {
            background-color: #555;
            color: white;
            padding: 15px;
        }
        /* Remove the navbar's default rounded borders and increase the bottom margin */
        .navbar {
            margin-bottom: 0;
            border-radius: 0;
        }

        /* Remove the jumbotron's default bottom margin */
        .jumbotron {
            margin-bottom: 0;
        }

        .btn-block.my {
            background-color: #222222;
            color: #f1f1f1;
        }

        .text-center.my-login {
            padding: 100px 140px 0 140px;
        }

        #login-check {
            padding: 0;
        }

        .justify-content-center.my-login {
            height: 40px;
            vertical-align: middle;
            text-align: center;
            padding-bottom: 10px;
            padding-top: 10px;
        }

        .text-center.my-login-r {
            margin-top: 15px;
        }

        /* Set height of the grid so .sidenav can be 100% (adjust as needed) 600px*/
        .row.content {height: 640px}

        /* Set gray background color and 100% height */
        .sidenav {
            padding-top: 20px;
            background-color: #f1f1f1;
            height: 100%;
        }

        /* On small screens, set height to 'auto' for sidenav and grid */
        @media screen and (max-width: 767px) {
            .sidenav {
                height: auto;
                padding: 15px;
            }
            .row.content {height:auto;}
        }
    </style>
    <!-- -->
</head>
<c:url value='/redirect/login' var="loginURL"/>
<body>
<header>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="">GradeBook</a>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="<c:out value="${loginURL}"/>">
                            <span class="glyphicon glyphicon-log-in"></span> Login</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-3 sidenav">
        </div>
        <div class="col-sm-6 text-center my-login">
            <h1>Sign In</h1>
            <c:if test="${not empty ExceptionMessage}">
            <div>
                <label>${ExceptionMessage}</label>
            </div>
            </c:if>
            <form name="username" action="<c:url value='/login' />" method="POST">
                <div class="form-group text-left">
                    <label for="loginUserName">Email: </label>
                    <input type="text" value="" class="form-control" name="loginUserName" required placeholder="Enter email" id="loginUserName"/>
                </div>
                <div class="form-group  text-left">
                    <label for="loginPassword">Password: </label>
                    <input type="password" value="" class="form-control" name="loginPassword" required placeholder="Enter password" id="loginPassword"/>
                </div>
                <!--<div class="checkbox">
                <label><input type="checkbox"> Remember me</label>
            </div>-->
                <!-- checkbox&PassLink -->
                <div class="row mb-4">
                    <div class="col-md-6 d-flex justify-content-center">
                        <!-- Checkbox -->
                        <div class="checkbox">
                            <input   type="checkbox" value="" id="loginCheck"/> <!-- class="form-check-input" -->
                            <label id="login-check" for="loginCheck">Remember me</label> <!-- class="form-check-label"-->
                        </div>
                    </div>

                    <div class="col-md-6 d-flex justify-content-center my-login">
                        <!-- Simple link -->
                        <a href="#">Forgot password?</a>
                    </div>
                </div>

                <!-- button -->
                <button type="submit" class="btn btn-block my text-left">Login</button> <!--btn-default-->

                <!-- register -->
                <div class="text-center my-login-r">
                    <p>Not a member? <a href="#!">Register</a></p>
                </div>
            </form>

        </div>
        <div class="col-sm-3 sidenav">
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>

<!--
<h1>Login page</h1>
c:if test="$-{not empty ExceptionMessage}">
    <div>
    <label>$-{ExceptionMessage}</label>
    </div>
/c:if>
<form name="username" action="c:url value='/login' />" method="POST">
<div class="form-group">
<label for="loginUserName">Email: </label>
<input type="text" value="" class="form-control" name="loginUserName" required placeholder="Enter email" id="loginUserName"/>
</div>
<div class="form-group">
<label for="loginPassword">Password: </label>
<input type="password" value="" class="form-control" name="loginPassword" required placeholder="Enter password" id="loginPassword"/>
</div>
!--<div class="checkbox">
<label><input type="checkbox"> Remember me</label>
</div>--
<button type="submit" class="btn btn-default">Login</button>
</form>
-->
