<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<jsp:include page="/jsp/header/header_orders.jsp"/>
<div class="order">
    <c:choose>
        <c:when test="${bookEmpty == true}">
            <h2><fmt:message key="Order.empty"/></h2>
        </c:when>
        <c:otherwise>
            <h2><fmt:message key="Order.list"/></h2>
            <table>
                <tr>
                    <th><fmt:message key="Order.number"/></th>
                    <th><fmt:message key="Order.name"/></th>
                    <th><fmt:message key="Order.title"/></th>
                    <th><fmt:message key="Order.date"/></th>
                    <c:if test="${reader == true}">
                        <th><fmt:message key="Order.action"/></th>
                    </c:if>
                </tr>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.name}</td>
                        <td>${order.title}</td>
                        <td>${order.date}</td>
                        <c:if test="${reader == true}">
                            <td>
                                <a href="/?command=cancelOrder&id=${order.id}&bookId=${order.bookId}"><fmt:message
                                        key="Order.cancel"/></a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
