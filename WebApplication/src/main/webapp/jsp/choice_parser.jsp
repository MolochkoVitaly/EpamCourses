<%@ page contentType="text/html" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="messages" />

<html>
<head>
    <title>Result</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/lib/bootstrap.min.css">
</head>
<body>
<p><fmt:message key="index.welcome"/>, ${user}</p>
<form action="controller" method="get">
    <input type="hidden" name="command" value="parser">
    <input type="hidden" name="parser" value="dom">
    <input type="submit" class="btn btn-default" value="<fmt:message key="xml.parser.DOM" />">
</form>
<form action="controller" method="get">
    <input type="hidden" name="command" value="parser">
    <input type="hidden" name="parser" value="sax">
    <input type="submit" class="btn btn-default" value="<fmt:message key="xml.parser.SAX" />">
</form>
<form action="controller" method="get">
    <input type="hidden" name="command" value="parser">
    <input type="hidden" name="parser" value="stax">
    <input type="submit" class="btn btn-default" value="<fmt:message key="xml.parser.StAX" />">
</form>
<a href="index.jsp"><fmt:message key="return.to.main.page" /></a>
</body>
</html>
