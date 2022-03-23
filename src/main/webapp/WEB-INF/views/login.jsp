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
</head>
<body>
<h1>Login page</h1>
<div>
    <!--<div><-%= new DAOStudentImpl().getStudentById("1").toString()%></div>-->
    <!-- action="./login" -->
    <form name="username" action="<c:url value='/login' />" method="POST">
        Name: <input type="text" value="" name="loginUserName" placeholder="Enter name"/> </br>
        Password: <input type="password" value="" name="loginPassword" placeholder="Enter password"/> </br>
        Role student: <input type="radio" name="loginType" value="student" checked>
        Role teacher: <input type="radio" name="loginType" value="teacher"> </br>
        <input type="submit" value="Login" /> </br>
        <!--<a href="/practiceJBDC_war_exploded/dispatcher?action=toChangePassword">Change Password</a>-->
    </form>

</div>
</body>
</html>
