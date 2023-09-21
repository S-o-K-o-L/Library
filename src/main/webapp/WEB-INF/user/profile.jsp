<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: rozhk
  Date: 12.05.2023
  Time: 2:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<h1>Welcome!</h1>
<h2>User Info:</h2>
<table style="with: 50%">
    <tr>
        <td>First Name:</td>
        <td>${user.firstName}</td>
    </tr>
    <tr>
        <td>Last Name:</td>
        <td>${user.lastName}</td>
    </tr>
    <tr>
        <td>Login:</td>
        <td>${user.login}</td>
    </tr>
    <tr>
        <td>Contact Number:</td>
        <td>${user.telephoneNumber}</td>
    </tr>
</table>
<form action="${pageContext.request.contextPath}/data/books" method="get">
    <button>Посмотреть все книги</button>
</form>
<h1>Books list</h1>
<table>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Genres</th>
        <th>Count chapters</th>
    </tr>
    <c:forEach var="book" items="${userBooks}">
        <tr>
            <td>${book.name}</td>
            <td>${book.description}</td>
            <td>
                <c:forEach var="genre" items="${book.genres}">
                    ${genre}
                    <br>
                </c:forEach>
            </td>
            <td>
                    ${book.bookChapters.size()}
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
