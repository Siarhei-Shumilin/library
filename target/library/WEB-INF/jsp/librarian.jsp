<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="text"/>
<html>
<style>
    <%@include file="/WEB-INF/jsp/css/main.css" %>
    <%@include file="/WEB-INF/jsp/css/header.css" %>
</style>
<body>
<jsp:include page="header/header.jsp"/>
<div class="main">
    <c:forEach var="order" items="${orders}">
        <ul>
            <form method="POST" action="/">

                <li>OrderID: ${order.id}</li>
                <li>User name: ${order.name}</li>
                <li>Book title: ${order.title} </li>
                <li>Order active: ${order.active} </li>
                <li>
                    <input type="hidden" name="command" value="toGiveBook"/>
                    <input type="hidden" name="id" value="${order.id}"/>
                    <input class="main-submit" type="submit" value="give the book"/>
                </li>
            </form>
        </ul>
    </c:forEach>
</div>
</body>
</html>



