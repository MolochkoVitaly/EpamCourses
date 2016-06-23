<%@ page contentType="text/html; charset=utf-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="mt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="messages" />

<html>
<head>
	<title><fmt:message key="page.registration.title"/> </title>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/registration.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lib/bootstrap-theme.css">
</head>
<body>
<div class="outer-container">
	<div class="empty-for-header"></div>
	<%@ include file="/WEB-INF/jspf/header.jspf" %>
	<section class="container">
		<c:if test="${not empty errorMessage}">
			<%@ include file="/WEB-INF/jspf/alert_error.jspf" %>
		</c:if>
		<c:if test="${not empty actionMessage}">
			<%@ include file="/WEB-INF/jspf/alert_success.jspf" %>
		</c:if>
		<div id="register_container">
			<div id="form_container">
				<p class="login-text"><fmt:message key="form.registration"/></p>
				<form action="controller" method="post">
					<fieldset>
						<div class="field">
							<label for="lastName"><span><span style="color:red">*</span> <fmt:message key="register.placeholder.surname"/></span></label>
							<input id="lastName" class="text_input" type="text" autocomplete="off" required name="lastName"
								   pattern="^[A-ZА-ЯЁ][a-zа-яё]{4,19}$"
								   placeholder="<fmt:message key="register.placeholder.surname"/>"
								   title="<fmt:message key="register.title.surname"/>">
						</div>

						<div class="field">
							<label for="firstName"><span><span style="color:red">*</span> <fmt:message key="register.placeholder.name"/></span></label>
							<input id="firstName" class="text_input" type="text" autocomplete="off" required name="firstName"
								   pattern="^[A-ZА-ЯЁ][a-zа-яё]{2,19}$"
								   placeholder="<fmt:message key="register.placeholder.name"/>"
								   title="<fmt:message key="register.title.name"/>">
						</div>

						<div class="field">
							<label for="patronymic"><span><span style="color:red">*</span> <fmt:message key="register.placeholder.patronymic"/></span></label>
							<input id="patronymic" class="text_input" type="text" autocomplete="off" required name="patronymic"
								   pattern="^[A-ZА-ЯЁ][a-zа-яё]{4,19}$"
								   placeholder="<fmt:message key="register.placeholder.patronymic"/>"
								   title="<fmt:message key="register.title.patronymic"/>">
						</div>


						<div class="field">
							<label for="password"><span><span style="color:red">*</span> <fmt:message key="register.placeholder.password"/></span></label>
							<input id="password" class="text_input" type="password" autocomplete="off" required name="password"
								   pattern="^[а-яА-ЯёЁa-zA-Z][а-яА-ЯёЁa-zA-Z0-9-_\.]{4,19}$"
								   placeholder="<fmt:message key="register.placeholder.password"/>"
								   title="<fmt:message key="register.title.password"/>">
						</div>

						<div class="field">
							<label for="passwordAgain"><span><span style="color:red">*</span> <fmt:message key="register.placeholder.password"/></span></label>
							<input id="passwordAgain" class="text_input" type="password" autocomplete="off" required name="passwordAgain"
								   pattern="^[а-яА-ЯёЁa-zA-Z][а-яА-ЯёЁa-zA-Z0-9-_\.]{4,19}$"
								   placeholder="<fmt:message key="register.placeholder.password"/>"
								   title="<fmt:message key="register.title.password.again"/>">
						</div>

						<div class="field">
							<label for="email"><span><span style="color:red">*</span> <fmt:message key="register.placeholder.e-mail"/></span></label>
							<input id="email" class="text_input" type="email" autocomplete="off" required name="email"
								   pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$"
								   placeholder="<fmt:message key="register.placeholder.e-mail"/>"
								   title="<fmt:message key="register.title.e-mail"/>">
						</div>

						<div class="field">
							<label for="mobilePhone"><span><span style="color:red">*</span> <fmt:message key="register.placeholder.mobilephone"/></span></label>
							<input id="mobilePhone" class="text_input" type="text" autocomplete="off" required name="mobilePhone"
								   pattern="((\+375)|(80))(17|25|29|33|44)[0-9]{7}"
								   placeholder="<fmt:message key="register.placeholder.mobilephone"/>"
								   title="<fmt:message key="register.title.mobilephone"/>"
								   data-tooltip="<fmt:message key="register.help.phone"/> ">
						</div>

						<div class="field">
							<label for="address"><span><span style="color:red">*</span> <fmt:message key="register.placeholder.address"/></span></label>
							<input id="address" class="text_input" type="text" autocomplete="off" required name="address"
								   pattern="((Брестская|Витебская|Гомельская|Гродненская|Минская|Могилевская)\s(область,))(\s)(г.\s)([А-ЯЁ][а-яё]{2,})(,\s)(ул.\s)([А-ЯЁ][А-ЯЁа-яё\s]{2,})(,\s)(дом\s)([0-9]{1,})((,\s)(кв.\s)[0-9]{1,}){0,}"
								   placeholder="<fmt:message key="register.placeholder.address"/>"
								   data-tooltip="<fmt:message key="register.help.address"/> ">
						</div>

						<input type="hidden" name="command" value="register" />
						<input type="submit" class="btn btn-default" name="registration"
							   value="<fmt:message key="button.register"/>">
						<div id="tooltip"></div>
					</fieldset>
				</form>
			</div>
		</div>
	</section>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%@ include file="/WEB-INF/jspf/scripts.jspf" %>
</body>
</html>