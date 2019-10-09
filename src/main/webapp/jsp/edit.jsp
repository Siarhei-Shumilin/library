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
<head>
    <title>Sport shop</title>
</head>
<body>
<jsp:include page="/jsp/header/header_edit.jsp"/>
<c:set var="page" value="${page}" scope="request"/>
<div class="edit">
<form method="POST" action="/">
    <div class="col-65">
        <input name="title" type="text" placeholder="<fmt:message key="Main.title"/>" required value="${book.title}"/>
        <input name="author" type="text" placeholder="<fmt:message key="Main.author"/>" maxlength="35" required value="${book.author}"/>
        <input name="genre" type="text" placeholder="<fmt:message key="Main.genre"/>" maxlength="35" required value="${book.genre}"/>
    </div>
    <input name = "numberOfInstances" type = "text" placeholder="<fmt:message key="Main.numberOfInstances"/>" value="${book.numberOfInstances}"/>
    <div class="col-75">
        <textarea name="description" placeholder="<fmt:message key="Main.description"/>">${book.description}</textarea>
    </div>

    <c:choose>
        <c:when test="${book.id > 0}">
            <input type="hidden" name="id" value="${book.id}"/>
            <input type="hidden" name="command" value="edit"/>
        </c:when>
        <c:otherwise>
            <input type="hidden" name="command" value="addBook"/>
        </c:otherwise>
    </c:choose>
    <input type="hidden" name="page" value="${page}">
    <input type="submit" value="<fmt:message key="Main.add"/>" class="main-submit"/>
</form>
</div>
<%--<form method="post" action="/">--%>
<%--    <input type="hidden" name="command" value="main"/>--%>
<%--    <input type="submit" value="main" class="main-submit"/>--%>
<%--</form>--%>
</body>
</html>
