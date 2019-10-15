<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title><fmt:message key="Login.title"/></title>
</head>
<style>
    <%@include file="/jsp/css/main.css" %>
    <%@include file="/jsp/css/header.css" %>
</style>
<body>
<jsp:include page="/jsp/header/header_librarian.jsp"/>
<br/><br/><br/>
<div class="order">
<c:choose>
    <c:when test="${booksEmpty == true}">
        <h2><fmt:message key="Book.empty"/></h2>
    </c:when>
    <c:otherwise>
    <table>
        <tr>
            <th><fmt:message key="Order.number"/></th>
            <th><fmt:message key="Order.name"/></th>
            <th><fmt:message key="Order.title"/></th>
            <th><fmt:message key="Order.date"/></th>
            <th><fmt:message key="Order.action"/></th>
        </tr>
        <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.id}</td>
            <td>${order.name}</td>
            <td>${order.title}</td>
            <td>${order.date}</td>
            <form method="POST" action="/">
                <td>
                    <a href="/?command=returnBook&orderId=${order.id}&title=${order.title}"><fmt:message key="Order.return"/></a><br/>
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
