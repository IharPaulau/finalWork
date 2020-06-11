<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>REJECT REASON</title>
    <link href="/resources/css/style.css" rel="stylesheet"/>
</head>
<body>
    <div id="header">
        <div id="left-section">
            <a href="?lang=en">
                <spring:message code="locale.en"/>
            </a>
            <a href="?lang=ru">
                <spring:message code="locale.ru"/>
            </a>
        </div>
        <div id="right-section">
            <form id="logoutForm" method="POST" action="/logout">
                <h2>
                    <spring:message code="current.user"/>
                </h2>
                <h2>${pageContext.request.userPrincipal.name}</h2>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button class="button" type="submit">
                    <spring:message code="logout.button"/>
                </button>
            </form>
        </div>
    </div>
    <div id="content">
        <h1>
            <spring:message code="fill.reject.reason"/>
        </h1>
    </div>
    <form:form method="post" action="/rejectReason/${orderId}" modelAttribute="rejectReasonForm" class="form">
        <form:hidden path="orderId" value="${orderId}"/>
        <form:input type="text" path="rejectReason"/>
        <button class="button" type="submit">
            <spring:message code="send.reject.reason"/>
        </button>
    </form:form>
</body>
<div id="footer">
    <h2>Final Project For Java Web Development Course.</h2>
</div>
</html>