<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

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
            <spring:message code="cars.page"/>
        </h1>
        </div>
    <table id="table">
        <tr>
            <th>
                <spring:message code="car.id.placeholder"/>
            </th>
            <th>
                <spring:message code="car.brand.placeholder"/>
            </th>
            <th>
                <spring:message code="car.model.placeholder"/>
            </th>
            <th>
                <spring:message code="car.cost.placeholder"/>
            </th>
            <th>
                <spring:message code="car.info.placeholder"/>
            </th>
            <sec:authorize access="hasAuthority('ROLE_USER')">
                <th>
                    <spring:message code="car.order.placeholder"/>
                </th>
            </sec:authorize>
            <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                <th>
                    <spring:message code="car.edit.placeholder"/>
                </th>
                <th>
                    <spring:message code="delete.placeholder"/>
                </th>
            </sec:authorize>
        </tr>
        <c:forEach var="car" items="${list}">
            <c:if test="${car.available}">
                <tr>
                    <td>${car.id}</td>
                    <td>${car.brand}</td>
                    <td>${car.model}</td>
                    <td>${car.costPerOneDay}</td>
                    <td>
                        <a href="carDetails/${car.id}">more info</a>
                    </td>
                    <sec:authorize access="hasAuthority('ROLE_USER')">
                        <td>
                            <a href="/orderForm/${car.id}">to rent</a>
                        </td>
                    </sec:authorize>
                    <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                        <td>
                            <a href="editCar/${car.id}">Edit</a>
                        </td>
                        <td>
                            <a href="deleteCar/${car.id}">Delete</a>
                        </td>
                    </sec:authorize>
                </tr>
            </c:if>
        </c:forEach>
    </table>
<br/>
<sec:authorize access="hasAuthority('ROLE_ADMIN')">
<a href="/cars/carForm" class="notification">
    <span>
        <spring:message code="add.new.car"/>
    </span>
</a>
<a href="/orders/viewOrders" class="notification">
    <span>
        <spring:message code="view.all.orders"/>
    </span>
    <c:if test="${uncheckedOrders > '0'}">
        <span class="badge">${uncheckedOrders}</span>
    </c:if>
</a>
<a href="/orders/completeOrders/" class="notification">
    <span><spring:message code="view.cars.return"/></span>
    <c:if test="${returnOrders > '0'}">
        <span class="badge">${returnOrders}</span>
    </c:if>
</a>
</sec:authorize>
<sec:authorize access="hasAuthority('ROLE_USER')">
<a href="/orders/viewMyOrders/" class="notification">
    <span>
        <spring:message code="view.my.orders"/>
    </span>
    <c:if test="${approvedOrders > '0'}">
        <span class="badge">${approvedOrders}</span>
    </c:if>
</a>
</sec:authorize>
</body>
<div id="footer">
    <h2>Final Project For Java Web Development Course.</h2>
</div>
</html>






