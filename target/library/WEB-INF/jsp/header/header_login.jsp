<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="text"/>
<div class="header">
    <h1><fmt:message key="Header.title"/></h1>
    <div class="header-right">
        <form action="/" method="POST">
            <input type="hidden" name="command" value="language"/>
            <input name="language" type="submit" value="EN" class="logout"/>
            <input name="language" type="submit" value="RU" class="logout"/>
        </form>
    </div>
</div>
