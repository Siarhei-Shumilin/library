<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="tag" %>

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
<c:set var="page" value="${page}" scope="request"/>
<jsp:include page="/jsp/header/header_reader.jsp"/>
<h2><c:if test="${errorSearch == true}"><fmt:message key="Error.searchBook"/></c:if></h2>
<br/>
<div class="tableOfGenre">
    <p style="color: white"><fmt:message key="Book.menu"/></p>
    <a href="/?command=main"><fmt:message key="Book.main"/></a>
    <br/>
    <c:forEach items="${genre}" var="genre">
        <a href="/?command=ShowBooksByGenre&genre=${genre}">${genre}</a>
        <br/>
    </c:forEach>

</div>
<div class="main">
    <c:forEach var="book" items="${books}">
        <form method="POST" action="/">
            <ul>
                <li><fmt:message key="Main.title"/>: ${book.title}</li>
                <li><fmt:message key="Main.author"/>: ${book.author}</li>
                <li><fmt:message key="Main.genre"/>: ${book.genre} </li>
                <li><fmt:message key="Main.description"/>: ${book.description}</li>
                <c:choose>
                <c:when test="${book.numberAvailableOfInstances>0}">
                <li>
                    <input type="hidden" name="command" value="order"/>
                    <input type="hidden" name="id" value="${book.id}"/>
                    <input type="hidden" name="page" value="${page}">
                    <input type="hidden" name="date" value="<ctg:info-time/>">
                    <input class="main-submit" type="submit" value="<fmt:message key="Main.order"/>"/>
                </li>
                </c:when>
                    <c:otherwise>
                        <p><fmt:message key="Book.available"/></p>
                    </c:otherwise>
                </c:choose>
            </ul>
        </form>
    </c:forEach>
    <c:forEach var="i" begin="1" end="${count}">
        <form method="post" style="display: inline-block">
            <input type="hidden" name="command" value="main"/>
            <input type="submit" name="page" value="${i}">
        </form>
    </c:forEach>
</div>
</body>
</html>


