<%@ page contentType="text/html" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="messages" />

<html>
<head>
    <title><fmt:message key="error.page.title"/></title>
    <meta charset="utf-8">
</head>
<body>
<p>${errorMessage}</p>
<a href="index.jsp"><fmt:message key="return.to.main.page" /></a>
</body>
</html>