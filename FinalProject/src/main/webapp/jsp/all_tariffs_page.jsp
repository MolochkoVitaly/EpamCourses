<%@ page contentType="text/html" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="mt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="messages" />


<html>
<head>
    <title><fmt:message key="page.list-of-rates.title"/> </title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sign-in.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lib/bootstrap-theme.css">
</head>
<body>
<div class="outer-container">
    <div class="empty-for-header"></div>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <section class="container office">
        <table class="table table-hover" style="margin-top: 0 !important;">
            <colgroup>
                <col width="10%">
                <col width="30%">
                <col width="20%">
                <col width="20%">
                <col width="20%">
            </colgroup>
            <thead>
            <tr role="row">
                <th style="text-align: center; width: 0; vertical-align: inherit;"><fmt:message key="tariff.name"/>
                </th>
                <th style="text-align: center; width: 0;"><fmt:message key="tariff.speed.upload"/> <br/>
                    <fmt:message key="traffic.speed.value"/>
                </th>
                <th style="text-align: center; width: 0;"><fmt:message key="tariff.speed.download"/> <br/>
                    <fmt:message key="traffic.speed.value"/>
                </th>
                <th style="text-align: center; width: 0;"><fmt:message key="tariff.volume"/> <br/>
                    <fmt:message key="traffic.volume.value"/>
                </th>
                <th style="text-align: center; width: 0;"><fmt:message key="tariff.month-payment"/> <br/>
                    <fmt:message key="traffic.price.value"/>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${tariffs}" var="current">
                <tr style="height:38px;" class="" data-toggle="collapse" data-target="#${current.id}" title="<fmt:message key="tariff.title"/> ">
                    <td style="text-align:center;" class=" ">
                        <c:out value="${current.tariffName}"/>
                    </td>
                    <td style="text-align:center;" class=" ">
                        <c:out value="${current.uploadSpeed}"/>
                    </td>
                    <td style="text-align:center;" class=" ">
                        <c:out value="${current.downloadSpeed}"/>
                    </td>
                    <td style="text-align:center;" class=" ">
                        <c:out value="${current.trafficVolume}"/>
                    </td>
                    <td style="text-align:center;" class=" ">
                        <c:out value="${current.monthPayment}"/>
                    </td>
                </tr>
                <tr id="${current.id}" class="collapse">
                    <td colspan="5" >
                        <div class="tariff-description">
                                ${current.description}
                        </div>
                        <c:if test="${user.role == \"CLIENT\"}">
                            <div class="subscribe">
                                <button class="btn btn-default" id="subscribe" type="button" data-toggle="modal" data-target="#subscribeModal" style="float: right; margin-right: 10px">
                                    <fmt:message key="tariff.subscribe"/>
                                </button>
                            </div>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%@ include file="/WEB-INF/jspf/scripts.jspf" %>
</body>
</html>
