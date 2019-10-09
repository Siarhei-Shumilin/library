<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="text"/>

<ul>
    <li><fmt:message key="Main.title"/>: ${book.title}</li>
    <li><fmt:message key="Main.author"/>: ${book.author}</li>
    <li><fmt:message key="Main.genre"/>: ${book.genre} </li>
    <li><fmt:message key="Main.numberOfInstances"/>: ${book.numberOfInstances}</li>
</ul>

<form method="POST">
    <input type="hidden" name="title" value="${book.title}"/>
    <input type="hidden" name="command" value="delete"/>
    <input type="submit" value="<fmt:message key="Main.delete"/>" onclick="return confirm('<fmt:message key="Main.confirm"/>')" class="main-submit"/>
</form>
<form method="post">
    <input type="hidden" name="id" value="${book.id}"/>
    <input type="hidden" name="command" value="to_edit"/>
    <input type="submit" value="<fmt:message key="Main.edit"/>" class="main-submit"/>
</form>
<p>
