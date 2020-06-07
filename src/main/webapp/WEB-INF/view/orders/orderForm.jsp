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
    <div class="container">
        <h1>
            <spring:message code="fill.order"/>
        </h1>
        <form:form method="post" action="/orderForm/${id}" modelAttribute="order" class="form">
            <form:hidden path="user.username" value="${pageContext.request.userPrincipal.name}"/>
            <form:hidden path="car.id" value="${carId}"/>
            <div class="form-attributes">
                <div class="form-attribute">
                    <label>
                        <spring:message code="passport.series"/>
                    </label>
                    <spring:message code="passport.series.placeholder" var="passportSeries"/>
                    <form:input type="text" path="passportSeries" placeholder="${passportSeries}" maxlength="2"/>
                    <form:errors path="passportSeries" class="error-message"/>
                </div>
                <div class="form-attribute">
                    <label>
                        <spring:message code="passport.number"/>
                    </label>
                    <spring:message code="passport.number.placeholder" var="passportNumbers"/>
                    <form:input type="text" path="passportNumber" placeholder="${passportNumbers}" maxlength="7"/>
                    <form:errors path="passportNumber" class="error-message"/>
                </div>
                <div class="form-attribute">
                    <label>
                        <spring:message code="id.number"/>
                    </label>
                    <spring:message code="id.number.placeholder" var="idNumber"/>
                    <form:input type="text" path="passportId" placeholder="${idNumber}" maxlength="14"/>
                    <form:errors path="passportId" class="error-message"/>
                </div>
                <div class="form-attribute">
                    <label>
                        <spring:message code="rental.period"/>
                    </label>
                    <spring:message code="desired.rental.period" var="period"/>
                    <form:input type="text" path="rentalPeriodInDays" placeholder="${period}" maxlength="2"/>
                    <form:errors path="rentalPeriodInDays" class="error-message"/>
                </div>
            </div>
            <div>
                <button class="button" type="submit">
                    <spring:message code="button.to.confirm"/>
                </button>
            </div>
        </form:form>
    </div>
</body>
<div id="footer" style="position: relative;">
<h2>Final Project For Java Web Development Course.</h2>
</div>
</html>
