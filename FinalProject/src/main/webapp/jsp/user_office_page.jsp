<%@ page contentType="text/html" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="mt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="messages" />


<html>
<head>
    <title><fmt:message key="page.user-office.title"/> </title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lib/bootstrap-theme.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
</head>
<body>
<div class="outer-container">
    <div class="empty-for-header"></div>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <section class="container office">
        <div>
            <ul class="nav nav-tabs">
                <li class="active"><a href="#home" aria-controls="home" data-toggle="tab"><i class="fa fa-info-circle" aria-hidden="true"></i> <fmt:message key="office.about-me"/> </a></li>
                <li class=""><a href="#orders" aria-controls="orders"  data-toggle="tab"><i class="fa fa-tasks" aria-hidden="true"></i> <fmt:message key="office.current-tariffs"/> </a></li>
                <li class=""><a href="#payment" aria-controls="payment"  data-toggle="tab"><i class="fa fa-money" aria-hidden="true"></i> <fmt:message key="office.replenish.balance"/> </a></li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane fade active in" id="home">
                    <h5><fmt:message key="office.info.header"/> </h5>
                    <hr>
                    <c:if test="${not empty errorMessage}">
                        <%@ include file="/WEB-INF/jspf/alert_error.jspf" %>
                    </c:if>
                    <c:if test="${not empty actionMessage}">
                        <%@ include file="/WEB-INF/jspf/alert_success.jspf" %>
                    </c:if>
                    <div class="row">
                        <div class="column col-sm-6 col-xs-6 col-md-7">
                            <div class="form-group input-group">
                                <span class="input-group-addon labelt"><i class="fa fa-user" aria-hidden="true"></i></span>
                                <label class="form-control label_box" style="width:350px;">${user.surname}</label>
                            </div>
                            <div class="form-group input-group">
                                <span class="input-group-addon labelt"><i class="fa fa-user" aria-hidden="true"></i></span>
                                <label class="form-control label_box" style="width:350px;">${user.name}</label>
                            </div>
                            <div class="form-group input-group">
                                <span class="input-group-addon labelt"><i class="fa fa-user" aria-hidden="true"></i></span>
                                <label class="form-control label_box" style="width:350px;">${user.patronymic}</label>
                            </div>
                            <div class="form-group input-group">
                                <span class="input-group-addon labelt"><i class="fa fa-envelope-o" aria-hidden="true"></i></span>
                                <label class="form-control label_box" style="width:350px;">${user.email}</label>
                            </div>
                            <div class="form-group input-group">
                                <span class="input-group-addon labelt"><i class="fa fa-mobile" aria-hidden="true"></i></span>
                                <label class="form-control label_box" style="width:350px;">${user.phoneNumber}</label>
                            </div>
                            <div class="form-group input-group">
                                <span class="input-group-addon labelt"><i class="fa fa-building-o" aria-hidden="true"></i></span>
                                <label class="form-control label_box" style="width:350px; height: 54px;">${user.address}</label>
                            </div>

                            <div class="checkbox">
                                <label>
                                    <input value="" type="checkbox" id="news_h">
                                    <fmt:message key="office.notification"/>
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input value="" type="checkbox" id="news_n">
                                    <fmt:message key="office.news"/>
                                </label>
                            </div>
                        </div>
                        <%@ include file="/WEB-INF/jspf/edit_personal_data.jspf"%>
                    </div>
                    <div>
                        <hr>
                        <p><fmt:message key="office.info.footer"/> </p>
                    </div>

                </div>
                <div class="tab-pane fade" id="orders">
                    <div class="form-inline">
                        <table class="table table-bordered" style="margin-top: 0 !important;">
                            <tr>
                                <td><fmt:message key="office.tariff.name"/> </td>
                                <td colspan="2">${usedTariff.tariff.tariffName}</td>
                            </tr>
                            <tr>
                                <td><fmt:message key="office.tariff.active"/> </td>
                                <td> <fmt:message key="office.tariff.active.from"/> ${usedTariff.creatingDate}</td>
                                <td> <fmt:message key="office.tariff.active.to"/>  ${usedTariff.endDate}</td>
                            </tr>
                            <tr>
                                <td><fmt:message key="office.tariff.description"/> </td>
                                <td colspan="2">${usedTariff.tariff.description}</td>
                            </tr>
                            <tr>
                                <td><fmt:message key="office.tariff.payment"/> </td>
                                <td colspan="2">${usedTariff.tariff.monthPayment} <fmt:message key="traffic.price.value"/></td>
                            </tr>
                            <tr>
                                <td rowspan="2"><fmt:message key="office.tariff.speed"/> </td>
                                <td><fmt:message key="office.tariff.download"/> </td>
                                <td><fmt:message key="office.tariff.upload"/> </td>
                            </tr>
                            <tr>
                                <td>${usedTariff.tariff.downloadSpeed} <fmt:message key="traffic.speed.value"/></td>
                                <td>${usedTariff.tariff.uploadSpeed} <fmt:message key="traffic.speed.value"/></td>
                            </tr>
                            <tr>
                                <td><fmt:message key="office.tariff.volume"/> </td>
                                <td colspan="2">${usedTariff.tariff.trafficVolume} <fmt:message key="traffic.volume.value"/></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="tab-pane fade" id="payment">
                    <div class="balance">
                        <div class="current-balance">
                            <h2><fmt:message key="office.current-balance"/> </h2>
                            <h2>
                                <span id="count">${balance}</span>
                                <span><fmt:message key="traffic.price.value"/></span>
                            </h2>
                        </div>
                        <div class="pay form-group">
                            <label for="money"><fmt:message key="office.enter-the-amount"/></label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-money" aria-hidden="true"></i></span>
                                <input type="text" class="form-control" id="money" name="sum"
                                       pattern="[1-9][0-9]{1,5}"
                                       placeholder=""
                                       title="">
                            </div>
                            <button type="button" id="upBalance" class="btn btn-default" ><fmt:message key="office.send.amount"/></button>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%@ include file="/WEB-INF/jspf/scripts.jspf" %>
</body>
</html>
