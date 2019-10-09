<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="text"/>
<html>
<style>
    <%@include file="/jsp/css/main.css" %>
    <%@include file="/jsp/css/header.css" %>
</style>
<body>
<jsp:include page="/jsp/header/header_librarian.jsp"/>

<div class="main">
<c:choose>
    <c:when test="${booksEmpty == true}">
        <h2><fmt:message key="Book.empty"/></h2>
    </c:when>
    <c:otherwise>
    <table>
        <tr>
            <th><fmt:message key="Main.title"/></th>
            <th><fmt:message key="Main.author"/></th>
            <th><fmt:message key="Main.genre"/></th>
            <th><fmt:message key="Order.action"/></th>
        </tr>
        <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.genre}</td>
            <form method="POST" action="/">
                <td>
                    <input type="hidden" name="command" value="returnBook">
                    <input type="hidden" name="bookId" value="${book.id}">
                    <input class="main-submit" type="submit" value="<fmt:message key="Order.return"/>"/>
                </td>
            </form>
            </c:forEach>
        </tr>
    </table>
    </c:otherwise>
</c:choose>
</div>
</body>
</html>
