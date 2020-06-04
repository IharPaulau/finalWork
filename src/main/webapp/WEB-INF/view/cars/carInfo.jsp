<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
table {
  border-collapse: collapse;
  border-spacing: 0;
    border: 1px solid #ddd;
}

table td, table th {
border: 1px solid #ddd;
padding: 25px;
}
table tr:nth-child(even){background-color: #f2f2f2;}
table tr:hover {background-color: #00BFFF;}

table th {
padding-top: 22px;
padding-bottom: 22px;
text-align: center;
background-color: #008fc3;
color: white;
}
table.center {
  margin-left:auto;
  margin-right:auto;
}
</style>

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

        <table class="center">
            <tr>
                <th><spring:message code="car.characteristics"/></th>
                <th><spring:message code="values"/></th>
            </tr>
            <tr>
                <td><spring:message code="car.id.placeholder"/></td>
                <td>${car.id}</td>
            </tr>
            <tr>
                <td><spring:message code="car.brand.placeholder"/></td>
                <td>${car.brand}</td>
            </tr>
            <tr>
                <td><spring:message code="car.model.placeholder"/></td>
                <td>${car.model}</td>
            </tr>
            <tr>
                <td><spring:message code="car.body.placeholder"/></td>
                <td>${car.typeBody}</td>
            </tr>
            <tr>
                <td><spring:message code="car.engine.placeholder"/></td>
                <td>${car.typeEngine}</td>
            </tr>
            <tr>
                <td><spring:message code="car.color.placeholder"/></td>
                <td>${car.bodyColor}</td>
            </tr>
            <tr>
                <td><spring:message code="car.transmission.placeholder"/></td>
                <td>${car.transmission}</td>
            </tr>
            <tr>
                <td><spring:message code="car.cost.placeholder"/></td>
                <td>${car.costPerOneDay}</td>
            </tr>
            </table>
        <spring:message code="button.to.rent" var="rent"/>
        <input type="submit" value="${rent}" class="notification"/>
    </form:form>
<a href="/cars/viewCars" class="notification">
     <spring:message code="view.all.cars"/>
</a>