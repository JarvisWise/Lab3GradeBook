<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<header>
    <div>USERNAME: ${username}</div>
    <div>

        <!-- will be ajax-->
        <form name="search" method="POST">
            <label> Search Type:
                <select name="searchType">
                    <option value="byGroup">By Group</option>
                    <option value="byStudent">By Student</option>
                    <option value="bySubject">By Subject</option>
                </select>
            </label>
            <label>
                <input type="text" value="" name="search" placeholder="Search"/>
            </label>
        </form>
    </div>
    <div>
        <a href="<c:url value='/' />">Logout</a>
    </div>
</header>
<div id="MainTable">
</div>
</body>
</html>
