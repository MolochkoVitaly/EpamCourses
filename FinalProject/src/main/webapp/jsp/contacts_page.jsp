<%@ page contentType="text/html" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="mt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="messages" />


<html>
<head>
    <title><fmt:message key="page.contacts.title"/> </title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/contacts.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lib/bootstrap-theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lib/font-awesome.min.css">
</head>
<body>
<div class="outer-container">
    <div class="empty-for-header"></div>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <section class="container contacts">
    <ul class="list-icon-items clear-fix">
        <li class="grid-3 secondary">
            <a href="<fmt:message key="vk.address"/> ">
                <h4 class="secondary"><fmt:message key="vk"/> </h4>
                <p>
                    <span class="pseudo-link"><fmt:message key="vk.write"/> </span>
                </p>
            </a>
        </li>
        <li class="grid-3 secondary">
            <a href="<fmt:message key="twitter.address"/> ">
                <h4 class="secondary"><fmt:message key="twitter"/> </h4>
                <p>
                    <span class="pseudo-link"><fmt:message key="twitter.follow.us"/> </span>
                </p>
            </a>
        </li>
        <li class="grid-3 secondary">
            <h4 class="secondary"><fmt:message key="phone"/> </h4>
            <p class="secondary"><fmt:message key="phone.number"/> </p>
        </li>
        <li class="grid-3 secondary">
            <a href="<fmt:message key="e-main.path"/> ">
                <h4 class="secondary"><fmt:message key="e-mail"/> </h4>
                <p>
                    <span class="pseudo-link"><fmt:message key="e-mail.address"/> </span>
                </p>
            </a>
        </li>
        <li class="grid-3 secondary">
            <a href="<fmt:message key="skype.address"/> ">
                <h4 class="secondary"><fmt:message key="skype"/> </h4>
                <p>
                    <span class="pseudo-link"><fmt:message key="skype.call.us"/> </span>
                </p>
            </a>
        </li>
    </ul>
    <div class="find">
        <hr class="grid-2 center">
        <h1><fmt:message key="find-us"/> </h1>
    </div>
    <div class="map">
        <script type="text/javascript" charset="utf-8" async src="https://api-maps.yandex.ru/services/constructor/1.0/js/?sid=AukJmBU3uYxzZ07DUJ4XD-OlHhkWjQkF&width=100%&height=419&lang=ru_RU&sourceType=constructor&scroll=true"></script>
    </div>
</section>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%@ include file="/WEB-INF/jspf/scripts.jspf" %>
</body>
</html>