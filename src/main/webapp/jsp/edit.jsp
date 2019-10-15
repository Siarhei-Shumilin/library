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
<jsp:include page="/jsp/header/header_edit.jsp"/>
<c:set var="page" value="${page}" scope="request"/>
<div class="edit">
    <h2><fmt:message key="Book.edit"/></h2>
    <form method="POST" action="/">
        <div class="col-65">
            <input name="title" type="text" placeholder="<fmt:message key="Main.title"/>" required
                   value="${book.title}"/>
            <input name="author" type="text" placeholder="<fmt:message key="Main.author"/>" maxlength="35" required
                   value="${book.author}"/>
            <input name="genre" type="text" placeholder="<fmt:message key="Main.genre"/>" maxlength="35" required
                   value="${book.genre}"/>
        </div>
        <input name="numberOfInstances" type="text" placeholder="<fmt:message key="Main.numberOfInstances"/>"
               value="${book.numberOfInstances}"/>
        <div class="col-75">
            <textarea name="description"
                      placeholder="<fmt:message key="Main.description"/>">${book.description}</textarea>
        </div>
        <input type="hidden" name="id" value="${book.id}"/>
        <input type="hidden" name="command" value="edit"/>
        <input type="submit" value="<fmt:message key="Main.add"/>" class="main-submit"/>
    </form>
</div>

</body>
</html>
