<%@ page contentType="text/html" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="mt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="messages" />


<html>
<head>
    <title><fmt:message key="page.reviews.title"/> </title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sign-in.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lib/bootstrap-theme.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

</head>
<body>
<div class="outer-container">
    <div class="empty-for-header"></div>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <section class="container reviews-container">
        <div class="reviewsScroll" id="reviews-area">
            <c:forEach items="${reviews}" var="current">
                <blockquote class="blockquote-reverse" review-id="${current.id}">
                    <c:if test="${user.role == \"ADMIN\"}">
                        <span class="delete-review" style="float: left"><i class="fa fa-times" aria-hidden="true"></i></span>
                    </c:if>
                    <p>${current.text}</p>
                    <footer>${current.user.name} ${current.user.surname}</footer>
                    <footer>${current.date}</footer>
                </blockquote>
            </c:forEach>
        </div>
        <c:if test="${user.role == \"CLIENT\"}">
            <div class="well">
                <textarea id="textReview" class="form-control" rows="2" placeholder="<fmt:message key="review.text-aria.title"/> "></textarea>
                    <div class="actions" >
                        <button id="send-review" class="btn btn-default" type="submit"><fmt:message key="review.send"/></button>
                    </div>
            </div>
        </c:if>
    </section>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%@ include file="/WEB-INF/jspf/scripts.jspf" %>
</body>
</html>