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

</head>
<body>
<div class="outer-container">
    <div class="empty-for-header"></div>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <section class="container reviews-container">
        <div class="reviewsScroll" id="reviews-area">
            <c:forEach items="${reviews}" var="current">
                <blockquote class="blockquote-reverse" review-id="${current.id}">
                    <p>${current.text}</p>
                    <footer>${current.user.name} ${current.user.surname}</footer>
                    <footer>${current.date}</footer>
                </blockquote>
            </c:forEach>
        </div>
        <div class="well">
            <textarea id="textReview" class="form-control" rows="2" placeholder="Введите Ваше сообщение"></textarea>
            <c:if test="${user.role == \"CLIENT\"}">
                <div class="actions" >
                    <button id="send-review" class="btn btn-default" type="submit"><fmt:message key="review.send"/></button>
                </div>
            </c:if>
        </div>
    </section>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%@ include file="/WEB-INF/jspf/scripts.jspf" %>
<c:if test="${user.role == \"ADMIN\"}">
    <script>
        $("#reviews-area").find("blockquote").dblclick(function () {
            var data = function (id) {
                return {
                    command: "deleteReview",
                    id:id
                };
            };

            var id = $(this).attr("review-id");

            $.ajax({
                type: "POST",
                url: "ajaxController",
                data: JSON.stringify(data(id)),
                dataType: "json",
                async: false,
                headers: {"Access-Control-Allow-Origin": "*"},
                contentType: "application/json; charset=utf-8",
                success: function (responseText) {
                    alert("was deleted");
                },
                error: function (responseText) {
                    alert("was not deleted");
                }
            });
        });
    </script>
</c:if>
</body>
</html>