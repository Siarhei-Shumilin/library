<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="text"/>
<html><head><title>Welcome</title></head>
<body>
<h3>Error page</h3>
<hr/>
${error}
<h2><c:if test="${errorLoginPassMessage == true}"><fmt:message key="Login.errorLoginPassMessage"/></c:if></h2>
<hr/>
<form method="post" action="/">
    <input type="hidden" name="command" value="main"/>
    <input type="submit" value="main" class="main-submit"/>
</form>
</body></html>