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
    <title>SignUp</title>
</head>
<body>
<h1>Registration Form New User</h1>
<form action="${pageContext.request.contextPath}/auth/signup" method="post">
    <c:if test="${requestScope.getOrDefault('error', false)==true}">
        <p style="color: red">
            Retry input please
        </p>
    </c:if>
    <table style="with: 50%">
        <tr>
            <td>First Name</td>
            <td><input type="text" name="firstName"/></td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><input type="text" name="lastName" value="lastName"/></td>
        </tr>
        <tr>
            <td>Login</td>
            <td><input type="text" name="login" value="login"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" value="password"/></td>
        </tr>
        <tr>
            <td>Contact Number</td>
            <td><input type="text" name="phone" value="phone"/></td>
        </tr></table>
    <input type="submit" value="Submit" />
</form>
</body>
</html>
