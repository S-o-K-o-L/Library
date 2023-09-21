
<%--
  Created by IntelliJ IDEA.
  User: rozhk
  Date: 12.05.2023
  Time: 0:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Log In</h2>
<form method="post" action="${pageContext.request.contextPath}/auth/login" >
    <label>Login:</label>
    <input type="text" name="login">
    <br>
    <label>Password:</label>
    <input type="password" name="password">
    <br>
    <c:if test="${requestScope.getOrDefault('error',false)==true}">
        <p style="color: red">Login or password is invalid</p>
    </c:if>
    <input type="submit" value="Submit">
</form>
<p>Doesn't have account? <a href="signup">Sign Up</a></p>
</body>
</html>
