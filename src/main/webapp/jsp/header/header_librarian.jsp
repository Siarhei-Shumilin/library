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

    <form method="get" action="/">
        <input type="hidden" name="command" value="showBooksHaveReader"/>
        <input class="logout" type="submit" value="<fmt:message key="Book.notReturned"/>"/>
    </form>

    <form method="POST" action="/">
        <input type="hidden" name="command" value="searchOrder"/>
        <input type="text" name="name" value="${user.name}" placeholder="<fmt:message key="User.name"/>" required/>
        <input class="logout" type="submit" value="<fmt:message key="Header.search"/>"/>
    </form>
</div>