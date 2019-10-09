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
<c:set var="page" value="${page}" scope="request"/>
<jsp:include page="/jsp/header/header_librarian.jsp"/>
<div class="main">
    <c:choose>
    <c:when test="${bookEmpty == true}">
        <h2><fmt:message key="Order.empty"/></h2>
    </c:when>
    <c:otherwise>
    <table>
        <tr>
            <th><fmt:message key="Order.number"/></th>
            <th><fmt:message key="Order.name"/></th>
            <th><fmt:message key="Order.title"/></th>
            <th><fmt:message key="Order.date"/></th>
            <th><fmt:message key="Order.active"/></th>
            <th><fmt:message key="Order.action"/></th>
        </tr>
        <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.id}</td>
            <td>${order.name}</td>
            <td>${order.title}</td>
            <td> ${order.date}</td>
            <c:choose>
                <c:when test="${order.active == true}">
                    <td><fmt:message key="Order.waiting"/></td>
                </c:when>
                <c:otherwise>
                    <td><fmt:message key="Order.close"/></td>
                </c:otherwise>
            </c:choose>
                <td>
                    <form method="post" action="/">
                        <input type="hidden" name="command" value="giveBook"/>
                        <input type="hidden" name="id" value="${order.id}"/>
                        <input type="hidden" name="bookId" value="${order.bookId}"/>
                        <input class="logout" type="submit" value="<fmt:message key="Order.give"/>"/>
                    </form>

                    <form method="post" action="/">
                        <input type="hidden" name="command" value="cancelOrder"/>
                        <input type="hidden" name="id" value="${order.id}"/>
                        <input type="hidden" name="bookId" value="${order.bookId}"/>
                        <input class="logout" type="submit" value="<fmt:message key="Order.cancel"/>"/>
                    </form>
            </c:forEach>
        </tr>
    </table>

    <form method="get" action="/">
        <input type="hidden" name="command" value="showBooksHaveReader"/>
        <input class="logout" type="submit" value="Books have reader"/>
    </form>
    </c:otherwise>
    </c:choose>
    <c:forEach var="i" begin="1" end="${count}">
                <form method="post" style="display: inline-block">
                    <input type="hidden" name="command" value="pagination"/>
                    <input type="submit" name="page" value="${i}">
                </form>
            </c:forEach>
</div>
</body>
</html>



