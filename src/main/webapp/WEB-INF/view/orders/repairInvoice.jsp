<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>ALL CARS</title>
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
            <div>
                <a href="/login">
                    <spring:message code="go.back"/>
                </a>
            </div>
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
            <spring:message code="fill.invoice"/>
        </h1>
        <form:form method="post" action="/repairInvoice/${order.id}" modelAttribute="order" class="form">
            <div class="form-attribute">
                <label>
                    <spring:message code="payment.amount"/>
                </label>
                <spring:message code="payment.amount.placeholder" var="paymentAmount"/>
                <form:input type="number" path="compensationAmount" placeholder="${paymentAmount}"/>
                        <c:if test="${not empty compensationError}">
                        <div class="error-message">
                                        <spring:message code="${compensationError}"/>
                        </div>
                        </c:if>
            </div>
            <div>
                 <button class="button" type="submit">
                    <spring:message code="button.to.save"/>
                 </button>
             </div>
        </form:form>

    </div>
</body>
<div id="footer">
    <h2>Final Project For Java Web Development Course.</h2>
</div>
</html>