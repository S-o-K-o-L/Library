<%--
  Created by IntelliJ IDEA.
  User: rozhk
  Date: 19.05.2023
  Time: 1:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Books</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/profile" method="get">
<h1>Books list</h1>
    <input type="submit" value="Вернуться в профиль">
</form>
<table>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Genres</th>
        <th>Count chapters</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="book" items="${bookList}">
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
            <td>
                <form action="${pageContext.request.contextPath}/addBookToUser" method="post">
                    <input type="hidden" name="bookId" value="${book.id}">
                    <input type="submit" value="Добавить книгу">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
