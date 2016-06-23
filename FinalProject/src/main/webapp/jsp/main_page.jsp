<%@ page contentType="text/html" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="mt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="messages" />

<html>
<head>
	<title><fmt:message key="page.main.title"/></title>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/language.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lib/bootstrap-theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lib/font-awesome.min.css">
</head>
<body>
<div class="outer-container">
	<div class="empty-for-header"></div>
	<header>
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container-fluid">
				<ul class="nav navbar-nav">
					<li>
						<a class="navbar-brand" href=controller><fmt:message key="header.main"/></a>
					</li>
					<li>
						<a href=controller?command=showAllTariffs><fmt:message key="header.list.of.rates"/></a>
					</li>
					<li>
						<a href=controller?command=showReviews><fmt:message key="header.reviews"/></a>
					</li>
					<li>
						<a href=controller?command=showContacts><fmt:message key="header.contacts"/></a>
					</li>
					<li>
						<a href=controller?command=showAboutUs><fmt:message key="header.about.us"/></a>
					</li>
					<li>
						<div id="dd" class="wrapper-dropdown" tabindex="1">
							<span><fmt:message key="language.title"/></span>
							<ul class="dropdown">
								<li>
									<form class="navbar-form" action="controller" method="post">
										<input type="hidden" name="command" value="language">
										<input type="hidden" name="lang" value="ru_RU">
										<input class="language" id="ru" type="submit" name="rus" value="<fmt:message key="language.russian"/>">
									</form>
								</li>
								<li>
									<form class="navbar-form"  action="controller" method="post">
										<input type="hidden" name="command" value="language">
										<input type="hidden" name="lang" value="en_EN">
										<input class="language" id="eng" type="submit" name="eng" value="<fmt:message key="language.english"/>">
									</form>
								</li>
							</ul>
						</div>
					</li>
					<li class="user-date"><ctg:today format="dd.MM.yyyy"/></li>
					<li class="user-name">
						${user.role}: ${user.name} ${user.patronymic}
					</li>
				</ul>
				<c:if test="${not empty user}">
					<div class="register-area">
						<form id="logout" class="navbar-form" action="controller" method="post">
							<input type="hidden" name="command" value="logout" />
							<input type="submit" class="btn btn-default" value="<fmt:message key="button.logout"/> " />
						</form>
						<form id="office" class="navbar-form" action="controller" method="post">
							<input type="hidden" name="command" value="privateOffice" />
							<input type="submit" class="btn btn-default" value="<fmt:message key="button.private-office"/> " />
						</form>
					</div>
				</c:if>
				<c:if test="${empty user}">
					<div class="register-area">
						<form id="sign-in" class="navbar-form" action="controller" method="post">
							<input type="hidden" name="command" value="forward" />
							<input type="hidden" name="forward" value="toLogin" />
							<input type="submit" class="btn btn-default" value="<fmt:message key="button.login"/>" />
						</form>
						<form id="sign-up" class="navbar-form" action="controller" method="post">
							<input type="hidden" name="command" value="forward" />
							<input type="hidden" name="forward" value="toRegister" />
							<input type="submit" class="btn btn-default" value="<fmt:message key="button.register"/> " />
						</form>
					</div>
				</c:if>
			</div>
		</nav>
	</header>
	<section class="container">
		<img src="${pageContext.request.contextPath}/images/logo.png">
		<p style="text-align: center">
			<img src="${pageContext.request.contextPath}/images/main-6.jpg">
		</p>
	</section>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%@ include file="/WEB-INF/jspf/scripts.jspf" %>
</body>
</html>
