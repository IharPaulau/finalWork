        <%@ page pageEncoding="UTF-8"%>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <link href="/resources/css/style.css" rel="stylesheet"/>
    <div>
        <a href="?lang=en"><spring:message code="locale.en"/></a>
        <a href="?lang=ru"><spring:message code="locale.ru"/></a>
    </div>
	<form:form method="get" action="/makeOrder/${car.id}" modelAttribute="car">
        <table border="7" width="30%" cellpadding="5">
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
                <td><spring:message code="car.cost.placeholder"/></td>
                <td>${car.costPerOneDay}</td>
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
                <td><spring:message code="car.fuel.placeholder"/></td>
                <td>${car.fuelConsumption}</td>
            </tr>
        </table>
        <spring:message code="button.to.rent" var="rent"/>
        <input type="submit" value="${rent}" />
    </form:form>
    <form action="/cars/viewCars" >
        <button><spring:message code="View.all.cars"/></button>
    </form>