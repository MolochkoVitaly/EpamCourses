<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="messages" />

<html>
<head>
    <title><fmt:message key="xml.parse.result"/></title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h1><c:out value="${parserType}"/></h1>
<div class="deposit-name"><fmt:message key="xml.deposit.type.multi"/></div>
<table class="table table-bordered">
    <thead>
    <tr>
        <th rowspan="2">Depoit id</th>
        <th rowspan="2">Type of currency</th>
        <th colspan="2">Bank</th>
        <th rowspan="2">Depositor</th>
        <th rowspan="2">Amount on deposit</th>
        <th rowspan="2">Profitability</th>
        <th rowspan="2">Time constraints</th>
    </tr>
    <tr>
        <th>Name</th>
        <th>Country</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${multiDepositsList}" var="current">
            <tr>
                <td><c:out value="${current.id}"/></td>
                <td><c:out value="${current.typeOfCurrency}"/></td>
                <td><c:out value="${current.bank.name}"/></td>
                <td><c:out value="${current.bank.country}"/></td>
                <td><c:out value="${current.depositor}"/></td>
                <td><c:out value="${current.amountOnDeposit}"/></td>
                <td><c:out value="${current.profitability}"/></td>
                <td><c:out value="${current.timeConstraints}"/></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<div class="deposit-name"><fmt:message key="xml.deposit.type.estimated"/></div>
<table class="table table-bordered">
    <thead>
    <tr>
        <th rowspan="2">Depoit id</th>
        <th colspan="2">Bank</th>
        <th rowspan="2">Depositor</th>
        <th rowspan="2">Amount on deposit</th>
        <th rowspan="2">Profitability</th>
        <th rowspan="2">Time constraints</th>
        <th rowspan="2">Minimal balance</th>
    </tr>
    <tr>
        <th>Name</th>
        <th>Country</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${estimatedDepositsList}" var="current">
        <tr>
            <td><c:out value="${current.id}"/></td>
            <td><c:out value="${current.bank.name}"/></td>
            <td><c:out value="${current.bank.country}"/></td>
            <td><c:out value="${current.depositor}"/></td>
            <td><c:out value="${current.amountOnDeposit}"/></td>
            <td><c:out value="${current.profitability}"/></td>
            <td><c:out value="${current.timeConstraints}"/></td>
            <td><c:out value="${current.minBalance}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <button type="button" name="back" onclick="history.back()" class="btn bth-info"><fmt:message key="return.to.choice.parser.page" /></button>
</div>
</body>
</html>
