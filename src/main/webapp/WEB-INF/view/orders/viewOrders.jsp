<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>ALL ORDERS</title>
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
	<h1><spring:message code="all.orders"/></h1>
	<table id="table">
        <tr>
            <th><spring:message code="user.placeholder"/></th>
            <th><spring:message code="car.placeholder"/></th>
            <th><spring:message code="rental.placeholder"/></th>
            <th><spring:message code="status.placeholder"/></th>
            <th><spring:message code="decision.placeholder"/></th>
        </tr>
        <c:forEach var="order" items="${list}">
            <tr>
                <td>${order.user.username}</td>
                <td>${order.car.brand} ${order.car.model}</td>
                <td>${order.rentalPeriodInDays}</td>
                <td>
                    <c:choose>
                        <c:when test="${order.orderStatus == 'APPROVED'}"><spring:message code="order.approved"/></c:when>
                        <c:when test="${order.orderStatus == 'REJECTED'}"><spring:message code="order.rejected"/></c:when>
                        <c:when test="${order.orderStatus == 'NOT_VERIFIED'}"><spring:message code="order.not.verified"/></c:when>
                        <c:when test="${order.orderStatus == 'IN_RENT'}"><spring:message code="order.in.rent"/></c:when>
                        <c:when test="${order.orderStatus == 'RETURN'}"><spring:message code="order.return"/></c:when>
                        <c:when test="${order.orderStatus == 'RECOVERY'}"><spring:message code="order.recovery"/></c:when>
                        <c:when test="${order.orderStatus == 'COMPLETED'}"><spring:message code="order.completed"/></c:when>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${order.orderStatus == 'APPROVED'}">
                            <a href="/reject/${order.id}"><button class="danger"><spring:message code="rejected"/></button></a>
                        </c:when>
                         <c:when test="${order.orderStatus == 'REJECTED'}">
                            <a href="/approve/${order.id}"><button class="warning"><spring:message code="approved"/></button></a>
                        </c:when>
                        <c:when test="${order.orderStatus == 'NOT_VERIFIED'}">
                            <a href="/reject/${order.id}"><button class="danger"><spring:message code="rejected"/></button></a>
                            <a href="/approve/${order.id}"><button class="info"><spring:message code="approved"/></button></a>
                        </c:when>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <a href="/cars/viewCars" class="notification">
        <spring:message code="view.all.cars"/>
    </a>