<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Car form page</title>
    <link href="/resources/css/style.css" rel="stylesheet"/>
</head>
<body>
<link href="/resources/css/style.css" rel="stylesheet"/>
<div id="header">
    <div id="left-section">
        <a href="?lang=en">
            <spring:message code="locale.en"/>
        </a>
        <a href="?lang=ru">
            <spring:message code="locale.ru"/>
        </a>
        <div>
            <a href="/cars/viewCars">
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
        <spring:message code="${pageLabel}"/>
    </h1>
    <form:form method="post" action="${pageAction}" modelAttribute="car" class="form">
        <div class="form-attributes">
            <div class="form-attribute">
                <label>
                    <spring:message code="car.brand.label"/>
                </label>
                <spring:message code="car.brand.placeholder" var="carBrandPlaceholder"/>
                <form:input type="text" path="brand" placeholder="${carBrandPlaceholder}"/>
                <form:errors path="brand" class="error-message"/>
            </div>
            <div class="form-attribute">
                <label>
                    <spring:message code="car.model.label"/>
                </label>
                <spring:message code="car.model.placeholder" var="carModelPlaceholder"/>
                <form:input type="text" path="model" placeholder="${carModelPlaceholder}"/>
                <form:errors path="model" class="error-message"/>
            </div>
            <div class="form-attribute">
                <label>
                    <spring:message code="car.body.label"/>
                </label>
                <spring:message code="car.body.placeholder" var="carBodyPlaceholder"/>
                <form:input type="text" path="typeBody" placeholder="${carBodyPlaceholder}"/>
                <form:errors path="typeBody" class="error-message"/>
            </div>
            <div class="form-attribute">
                <label>
                    <spring:message code="car.engine.label"/>
                </label>
                <spring:message code="car.engine.placeholder" var="cartEnginePlaceholder"/>
                <form:input type="text" path="typeEngine" placeholder="${cartEnginePlaceholder}"/>
                <form:errors path="typeEngine" class="error-message"/>
            </div>
            <div class="form-attribute">
                <label>
                    <spring:message code="car.color.label"/>
                </label>
                <spring:message code="car.color.placeholder" var="cartColorPlaceholder"/>
                <form:input type="text" path="bodyColor" placeholder="${cartColorPlaceholder}"/>
                <form:errors path="bodyColor" class="error-message"/>
            </div>
            <div class="form-attribute">
                <label>
                    <spring:message code="car.transmission.label"/>
                </label>
                <spring:message code="car.transmission.placeholder" var="cartTransmissionPlaceholder"/>
                <form:input type="text" path="transmission" placeholder="${cartTransmissionPlaceholder}"/>
                <form:errors path="transmission" class="error-message"/>
            </div>
            <div class="form-attribute">
                <label>
                    <spring:message code="car.cost.label"/>
                </label>
                <spring:message code="car.cost.placeholder" var="cartCostPlaceholder"/>
                <form:input type="text" path="costPerOneDay" placeholder="${cartCostPlaceholder}"/>
                <form:errors path="costPerOneDay" class="error-message"/>
            </div>
        </div>
        <div>
            <button class="button" type="submit">
                <spring:message code="button.to.save"/>
            </button>
        </div>
    </form:form>
</div>
</body>
</html>

<div id="footer" style="position: relative;">
    <h2>Final Project For Java Web Development Course.</h2>
</div>