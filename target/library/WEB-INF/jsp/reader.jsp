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
<h2><c:if test="${errorSearch == true}"><fmt:message key="Error.searchBook"/></c:if></h2>
<div class="main">
    <c:forEach var="book" items="${books}">
        <form method="POST" action="/">
            <ul>
                <li><fmt:message key="Main.title"/>: ${book.title}</li>
                <li><fmt:message key="Main.author"/>: ${book.author}</li>
                <li><fmt:message key="Main.genre"/>: ${book.genre} </li>
                <li><fmt:message key="Main.description"/>: ${book.description}</li>
                <li>
                    <input type="hidden" name="command" value="order"/>
                    <input type="hidden" name="id" value="${book.id}"/>
                    <input class="main-submit" type="submit" value="<fmt:message key="Main.order"/>"/>
                </li>
            </ul>
        </form>
    </c:forEach>

</div>
</body>
</html>


