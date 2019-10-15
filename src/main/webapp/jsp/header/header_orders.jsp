<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="text"/>

<div class="header">
    <h1><fmt:message key="Header.title"/></h1>
    <div class="header-right">
        <form action="/" method="post">
            <input type="hidden" name="command" value="logout"/>
            <input class="logout" type="submit" name="logout" value="<fmt:message key="Header.logout"/>"/>
        </form>

    </div>
</div>

<div class="menu">
    <form method="post" action="/">
        <input type="hidden" name="command" value="main"/>
        <input type="submit" value="<fmt:message key="Main.main"/>" class="logout"/>
    </form>

        <c:if test="${admin == true}">
            <form method="post" action="/">
                <input type="hidden" name="command" value="showUsers"/>
                <input type="submit" value="<fmt:message key="Main.back"/>" class="logout"/>
            </form>
        </c:if>

</div>
