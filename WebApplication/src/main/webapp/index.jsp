<%@ page contentType="text/html" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="mt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="messages" />

<html>
<title><fmt:message key="index.page.title"/></title>
<meta charset="utf-8">
<link rel="stylesheet" href="css/lib/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<body>
<form action="controller" method="post">
    <input type="hidden" name="command" value="language">
    <input type="hidden" name="lang" value="en_EN">
    <input class="language btn btn-default" id="eng" type="submit" name="eng" value="<fmt:message key="index.english" />">
</form>
<form action="controller" method="post">
    <input type="hidden" name="command" value="language">
    <input type="hidden" name="lang" value="ru_RU">
    <input class="language btn btn-default" id="ru" type="submit" name="rus" value="<fmt:message key="index.russian" />">
</form>

<form class="form-inline" role="form" method="POST" action="controller">
    <input type = "hidden" name = "command" value = "login">
    <div class="form-group">
        <label class="sr-only" for="loginInput">Login</label>
        <input type="text" name="nickname"  class="form-control" id="loginInput" placeholder="<fmt:message key="index.placeholder.login"/>">
    </div>
    <div class="form-group">
        <label class="sr-only" for="passwordInput">Password</label>
        <input type="password" name="password"  class="form-control" id="passwordInput" placeholder="<fmt:message key="index.placeholder.password"/>">
    </div>
    <input type="submit" class="btn btn-default" value="<fmt:message key="button.login"/>"/>
</form>
</body>
</html>