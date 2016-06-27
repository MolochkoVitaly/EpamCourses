<%@ page contentType="text/html" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="mt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="messages" />


<html>
<head>
    <title><fmt:message key="page.admin-office.title"/> </title>
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
        <ul class="nav nav-tabs">
            <li class="active"><a href="#home" aria-controls="home" data-toggle="tab"><i class="fa fa-info-circle"></i> <fmt:message key="office.about-me"/> </a></li>
            <li class=""><a href="#tariff-tools" aria-controls="tariff-tools"  data-toggle="tab"><i class="fa fa-wrench"></i> <fmt:message key="office.tariff.tools"/> </a></li>
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
                            <span class="input-group-addon"><i class="fa fa-user" aria-hidden="true"></i></span>
                            <label class="form-control label_box" style="width:350px;">${user.surname}</label>
                        </div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="fa fa-user" aria-hidden="true"></i></span>
                            <label class="form-control label_box" style="width:350px;">${user.name}</label>
                        </div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="fa fa-user" aria-hidden="true"></i></span>
                            <label class="form-control label_box" style="width:350px;">${user.patronymic}</label>
                        </div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope-o" aria-hidden="true"></i></span>
                            <label class="form-control label_box" style="width:350px;">${user.email}</label>
                        </div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="fa fa-mobile" aria-hidden="true"></i></span>
                            <label class="form-control label_box" style="width:350px;">${user.phoneNumber}</label>
                        </div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="fa fa-building-o" aria-hidden="true"></i></span>
                            <label class="form-control label_box" style="width:350px; height: 54px;">${user.address}</label>
                        </div>

                    </div>
                    <%@ include file="/WEB-INF/jspf/edit_personal_data.jspf"%>
                </div>
            </div>
            <div class="tab-pane fade" id="tariff-tools">
                <hr>
                <div class="row">
                    <div class="column col-sm-6 col-xs-6 col-md-7">
                        <div class="form-group">
                            <input type="hidden" id="id" value="">
                            <label for="name"><fmt:message key="office.tariff.name"/></label>
                            <input type="text" class="form-control" id="name"  style="width: 350px"
                                   pattern="[A-ZА-ЯЁ][A-ZА-ЯЁa-zа-яё0-9+-_\s]{2,}$">
                        </div>
                        <div class="form-group">
                            <label for="description"><fmt:message key="office.tariff.description"/></label>
                            <textarea rows="3" class="form-control" id="description" style="width: 350px"></textarea>
                        </div>
                        <div class="form-group form-inline" style="width:175px;">
                            <label for="upload">
                                <fmt:message key="office.tariff.upload"/> <br/>
                                <fmt:message key="traffic.speed.value"/>
                            </label>
                            <input type="text" class="form-control" id="upload" style="width: 70px; float: right"
                                   pattern="[1-9][0-9]{1,}">
                        </div>
                        <div class="form-group form-inline" style="width:175px;">
                            <label for="download">
                                <fmt:message key="office.tariff.download"/> <br/>
                                <fmt:message key="traffic.speed.value"/>
                            </label>
                            <input type="text" class="form-control" id="download" style="width: 70px; float: right"
                                   pattern="[1-9][0-9]{1,}">
                        </div>
                        <div class="form-group form-inline" style="width:175px;">
                            <label for="volume">
                                <fmt:message key="office.tariff.volume"/> <br/>
                                <fmt:message key="traffic.volume.value"/>
                            </label>
                            <input type="text" class="form-control" id="volume" style="width: 70px; float: right"
                                   pattern="[1-9][0-9]{1,}">
                        </div>
                        <div class="form-group form-inline" style="width:175px;">
                            <label for="price">
                                <fmt:message key="office.tariff.price"/> <br/>
                                <fmt:message key="traffic.price.value"/>
                            </label>
                            <input type="text" class="form-control" id="price" style="width: 70px; float: right;"
                                   pattern="[1-9][0-9]{1,}">
                        </div>
                        <div class="add-tariff">
                            <input type="submit" id="addTariff" class="btn btn-default add"  value="<fmt:message key="office.tariff.add"/> ">
                        </div>
                        <div class="edit-tariff">
                            <input type="hidden" id="editTariff" class="btn btn-default" value="<fmt:message key="office.tariff.edit"/> ">
                            <input type="hidden" id="archive" class="btn btn-default" value="<fmt:message key="office.tariff.in-archive"/> ">
                            <input type="hidden" id="cancel" class="btn btn-default" value="<fmt:message key="office.tariff.cancel"/> ">
                        </div>
                    </div>
                    <div class="dropdown find-tariff">
                        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown"><fmt:message key="office.tariff.list"/>
                        </button>
                        <ul class="dropdown-menu">
                        </ul>
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
