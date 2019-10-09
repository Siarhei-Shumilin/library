
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="text"/>
<html>
<style>
    <%@include file="/WEB-INF/jsp/css/main.css" %>
    <%@include file="/WEB-INF/jsp/css/header.css" %>
</style>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header/header_edit.jsp"/>
<div class="main">
<c:forEach var="order" items="${orders}">
    <ul>
        <li><fmt:message key="Orders.name"/>: ${order.name}</li>
        <li><fmt:message key="Orders.title"/>: ${order.title}</li>
        <li><fmt:message key="Orders.active"/>: ${order.active} </li>
        <li><form method="get" action="/">
            <input type="hidden" name="command" value="cancelOrder"/>
            <input type="hidden" name="id" value="${order.id}"/>
            <input class="logout" type="submit" value="<fmt:message key="Orders.cancel"/>"/>
        </form></li>
    </ul>
</c:forEach>
</div>
</body>
</html>
