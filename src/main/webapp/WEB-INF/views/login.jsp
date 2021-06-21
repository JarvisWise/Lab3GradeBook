<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 10.06.2021
  Time: 00:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome page</title>
</head>
<body>
<h1>Login page</h1>
<div>
    <form name="username" action="/practiceJBDC_war_exploded/dispatcher?action=mainPage" method="POST">
        Name: <input type="text" value="" name="username" placeholder="Enter name"/> </br>
        Password: <input type="password" value="" name="password" placeholder="Enter password"/> </br>
        <input type="submit" value="Enter" /> </br>
        <a href="/practiceJBDC_war_exploded/dispatcher?action=toChangePassword">Change Password</a>
    </form>

</div>
</body>
</html>
