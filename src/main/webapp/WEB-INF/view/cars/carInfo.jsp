<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<html>
<head>
    <title>CAR DETAILS</title>
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
    <form:form method="get" action="/orderForm/${car.id}" modelAttribute="car">
        <table id="table">
            <tr>
                <th>
                    <spring:message code="car.characteristics"/>
                </th>
                <th>
                    <spring:message code="values"/>
                </th>
            </tr>
            <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                <tr>
                    <td>
                        <spring:message code="car.id.placeholder"/>
                    </td>
                    <td>${car.id}</td>
                </tr>
            </sec:authorize>
            <tr>
                <td>
                    <spring:message code="car.brand.label"/>
                </td>
                <td>${car.brand}</td>
            </tr>
            <tr>
                <td>
                    <spring:message code="car.model.label"/>
                </td>
                <td>${car.model}</td>
            </tr>
            <tr>
                <td>
                    <spring:message code="car.body.label"/>
                </td>
                <td>${car.typeBody}</td>
            </tr>
            <tr>
                <td>
                    <spring:message code="car.engine.label"/>
                </td>
                <td>${car.typeEngine}</td>
            </tr>
            <tr>
                <td>
                    <spring:message code="car.color.label"/>
                </td>
                <td>${car.bodyColor}</td>
            </tr>
            <tr>
                <td>
                    <spring:message code="car.transmission.label"/>
                </td>
                <td>${car.transmission}</td>
            </tr>
            <tr>
                <td>
                    <spring:message code="car.cost.label"/>
                </td>
                <td>${car.costPerOneDay}</td>
            </tr>
        </table>
        <spring:message code="button.to.rent" var="rent"/>
        <input type="submit" value="${rent}" class="notification"/>
    </form:form>
    <a href="/cars/viewCars" class="notification">
        <spring:message code="view.all.cars"/>
    </a>
</body>
<div id="footer">
<h2>Final Project For Java Web Development Course.</h2>
</div>
</html>
