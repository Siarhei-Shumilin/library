<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="text"/>
<html>
<style>
    <%@include file="/jsp/css/main.css" %>
    <%@include file="/jsp/css/header.css" %>
</style>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/jsp/header/header_edit.jsp"/>
<%--<div class="main">--%>
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
                    <th><fmt:message key="Order.active"/></th>
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
                        <c:choose>
                            <c:when test="${order.active == true}">
                                <td>
                                    <fmt:message key="Order.waiting"/>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td><fmt:message key="Order.close"/></td>
                            </c:otherwise>
                        </c:choose>

                        <c:if test="${order.active == true && reader == true}">
                                    <td>
                                        <form method="get" action="/">
                                            <input type="hidden" name="command" value="cancelOrder"/>
                                            <input type="hidden" name="id" value="${order.id}"/>
                                            <input type="hidden" name="bookId" value="${order.bookId}"/>
                                            <input class="logout" type="submit"
                                                   value="<fmt:message key="Order.cancel"/>"/>
                                        </form>
                                    </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
    <div class="header-right">
        <c:if test="${admin == true}">
        <form method="post" action="/">
            <input type="hidden" name="command" value="showUsers"/>
            <input type="submit" value="<fmt:message key="Main.back"/>" class="logout"/>
        </form>
        </c:if>
    </div>
</div>
</body>
</html>
