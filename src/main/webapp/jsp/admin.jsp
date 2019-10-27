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
<c:set var="page" value="${page}" scope="request"/>
<jsp:include page="/jsp/header/header_admin.jsp"/>
<div>
    <h2><c:if test="${error == true}"><fmt:message key="Error.addBook"/></c:if></h2>
    <h2><c:if test="${errorSearch == true}"><fmt:message key="Error.searchBook"/></c:if></h2>
    <h2><c:if test="${invalid == true}"><fmt:message key="Error.invalid"/></c:if></h2>
    <br/>
    <div class="main">
        <div class="addBook">
        <p><fmt:message key="Main.addBook"/></p>
        <form method="POST" action="/">
            <input type="hidden" name="command" value="addBook"/>
            <div class="col-65">
                <input name="title" type="text" placeholder="<fmt:message key="Main.title"/>" pattern="[A-Za-zА-Яа-яЁё0-9\s.,!?;:]{1,30}" required/>
            </div>
            <div class="col-65">
                <input name="author" type="text" placeholder="<fmt:message key="Main.author"/>" pattern="[A-Za-zА-Яа-яЁё0-9\s.,!?;:]{1,30}" required/>
            </div>
            <div class="col-65">
                <input name="genre" type="text" placeholder="<fmt:message key="Main.genre"/>" pattern="[A-Za-zА-Яа-яЁё0-9\s.,!?;:]{1,30}" required/>
            </div>
            <div class="col-65">
                <input name="numberOfInstances" type="text" placeholder="<fmt:message key="Main.numberOfInstances"/>" pattern="[0-9]{1,}" required/>
            </div>
            <div class="col-75">
                <textarea name="description" placeholder="<fmt:message key="Main.description"/>"></textarea>
            </div>
            <input type="submit" value="<fmt:message key="Main.add"/>" class="main-submit"/>
        </form>
        </div>

        <table>
            <tr>
                <th><fmt:message key="Main.title"/></th>
                <th><fmt:message key="Main.author"/></th>
                <th><fmt:message key="Main.genre"/></th>
                <th><fmt:message key="Main.numberOfInstances"/></th>
                <th><fmt:message key="Order.action"/></th>
            </tr>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.title}</td>
                    <td>${book.author}</td>
                    <td>${book.genre}</td>
                    <td>${book.numberOfInstances}</td>

                    <td>
                        <div>
                            <a href="/?command=to_edit&id=${book.id}"><fmt:message key="Main.edit"/></a><br/>
                            <a href="/?command=delete&id=${book.id}" onclick="return confirm('<fmt:message key="Main.confirm"/>')"><fmt:message key="Main.delete"/></a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <c:forEach var="i" begin="1" end="${count}">
            <form method="post" style="display: inline-block">
                <input type="hidden" name="command" value="main"/>
                <input type="submit" name="page" value="${i}">
            </form>
        </c:forEach>
    </div>
</div>
</body>
</html>

