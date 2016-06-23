<%@ page contentType="text/html" language="java" isELIgnored="false" isErrorPage="true" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="mt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="messages" />
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sign-in.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lib/bootstrap-theme.css">
</head>
<body>
<body>
    <jsp:expression>(request.getAttribute("errorMessage") != null) ?
        (String) request.getAttribute("errorMessage") : "unknown error"</jsp:expression>
    <hr />
<a href="controller">
    <button type="button" class="btn btn-default">Main</button>
</a>
</body>
</body>
</html>
