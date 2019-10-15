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
<jsp:include page="/jsp/header/header_edit.jsp"/>
<div class="order">
    <h2><fmt:message key="User.list"/></h2>
    <table>
        <tr>
            <th><fmt:message key="User.name"/></th>
            <th><fmt:message key="User.role"/></th>
            <th><fmt:message key="Order.action"/></th>
        </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.name}</td>
            <td>${user.role}</td>
            <td>
                <a href="/?command=showUserOrders&id=${user.id}"><fmt:message key="Order.orders"/></a>
            </td>
        </tr>
    </c:forEach>
    </table>
</div>
</body>
</html>

