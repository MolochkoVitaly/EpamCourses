<%@ page contentType="text/html" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="mt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="messages" />


<html>
<head>
    <title><fmt:message key="page.about-us.title"/> </title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sign-in.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lib/bootstrap-theme.css">
</head>
<body>
<div class="outer-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="empty-for-header"></div>
    <section class="container">
        <img src="${pageContext.request.contextPath}/images/logo.png">
    </section>
    <section class="container">
        <div class="layout__content">
            <div class="">
                <h1><fmt:message key="h1"/> </h1>
            </div>
            <div class="">
                <p><fmt:message key="block.first.p.first"/> </p>
                <p><fmt:message key="block.first.p.second"/></p>
                <p><fmt:message key="block.first.p.third"/></p>
                <p style="text-align: center">
                    <img src="${pageContext.request.contextPath}/images/main-2.jpg">
                </p>
                <h2><fmt:message key="h2.first"/></h2>
                <p><fmt:message key="block.second.p.first"/></p>
                <p><fmt:message key="block.second.p.second"/></p>
                <p style="text-align: center">
                    <img src="${pageContext.request.contextPath}/images/main-1.jpg">
                </p>
                <h2><fmt:message key="h2.second"/></h2>
                <ul><li><fmt:message key="ul.first.li.first"/></li>
                    <li><fmt:message key="ul.first.li.second"/></li>
                    <li><fmt:message key="ul.first.li.third"/></li>
                    <li><fmt:message key="ul.first.li.fourth"/></li>
                </ul>
                <h2><fmt:message key="h2.third"/></h2>
                <ul><li><fmt:message key="ul.second.li.first"/></li>
                    <li><fmt:message key="ul.second.li.second"/></li>
                    <li><fmt:message key="ul.second.li.third"/></li>
                    <li><fmt:message key="ul.second.li.fourth"/></li>
                </ul>
                <p style="text-align: center">
                    <img src="${pageContext.request.contextPath}/images/main-4.jpg">
                </p>
            </div>
        </div>
    </section>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%@ include file="/WEB-INF/jspf/scripts.jspf" %>
</body>
</html>
