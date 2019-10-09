<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <title><fmt:message key="Login.title"/></title>
</head>
<style>
    <%@include file="/WEB-INF/jsp/css/main.css" %>
    <%@include file="/WEB-INF/jsp/css/header.css" %>
</style>
<body>
<jsp:include page="/WEB-INF/jsp/header/header_login.jsp"/>
<div class="login">
    <h2><fmt:message key="Login.title"/></h2>
    <form method="POST" action="/">
        <input type="hidden" name="command" value="login"/>
        <input type="text" name="login" placeholder="<fmt:message key="Login.login"/>"/>
        <input type="password" name="password" placeholder="<fmt:message key="Login.password"/>">

        <input type="submit" value="<fmt:message key = "Login.enter"/>"/>
    </form>
    <h2><c:if test="${errorLoginPassMessage == true}"><fmt:message key="Registration.errorLoginPassMessage"/></c:if></h2>
</div>
</body>
</html>