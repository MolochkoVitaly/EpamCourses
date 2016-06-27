<%@ page contentType="text/html" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="mt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmy" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="messages" />


<html>
<head>
	<title><fmt:message key="page.login.title"/> </title>
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
	<section class="container">
		<img src="${pageContext.request.contextPath}/images/logo.png">
		<c:if test="${not empty errorMessage}">
			<%@ include file="/WEB-INF/jspf/alert_error.jspf" %>
		</c:if>
		<c:if test="${not empty actionMessage}">
			<%@ include file="/WEB-INF/jspf/alert_success.jspf" %>
		</c:if>
		<div id="login_container">
			<div id="form_container">
				<p class="login-text"><fmt:message key="form.authorization"/> </p>
				<form method="post" action="controller">
					<input type="hidden" name="command" value="login"/>
					<input type="email" name="email" placeholder="<fmt:message key="placeholder.login"/> " class="text_input" />
					<input type="password"  name="password" placeholder="<fmt:message key="placeholder.password"/> " class="text_input" />
					<a class="forgot" data-toggle="modal" data-target="#forgotPasswordModal"><fmt:message key="forgot-password"/> </a>
					<input type="submit" class="btn btn-default" value="<fmt:message key="button.login"/> " id="login" name="login" />
				</form>
			</div>
			<div id="forgotPasswordModal" class="modal fade" tabindex="-1" >
				<div class="modal-dialog">
					<div class="modal-content" style="width: 282px; margin-left: 159px;">
						<div class="modal-header">
							<h4 class="modal-title"><fmt:message key="reset.password"/></h4>
						</div>
						<div class="modal-body">
							<form method="POST" action="controller">
								<div class="form-group input-group">
									<input type="hidden" name="command" value="restorePassword">
									<input type="hidden" name="recovery" value="email">
									<input type="email" name="email" class="form-control"
										   placeholder="<fmt:message key="reset.placeholder.password.e-mail"/> " style="width:250px;"
										   pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$">
									<input type="submit" class="btn btn-default" value="<fmt:message key="button.send"/> " style="margin-top: 5px"/>
								</div>
							</form>
							<h4><fmt:message key="or"/></h4>
							<form method="POST" action="controller">
								<div class="form-group input-group">
									<input type="hidden" name="command" value="restorePassword">
									<input type="hidden" name="recovery" value="sms">
									<input type="text" name="phone" class="form-control"
										   placeholder="<fmt:message key="reset.placeholder.password.phone"/> " value="+375" style="width:250px;"
										   pattern="((\+375)|(80))(25|29|33|44)[0-9]{7}">
									<input type="submit" class="btn btn-default" value="<fmt:message key="button.send"/> " style="margin-top: 5px"/>
								</div>
							</form>
						</div>
						<div class="modal-footer"><button class="btn btn-default" type="button" data-dismiss="modal"><fmt:message key="reset.close"/> </button></div>
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