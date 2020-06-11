<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>RETURN CARS PAGE</title>
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
        <spring:message code="return.cars"/>
    </h1>
    </div>
    <table id="table">

            <tr>
                <th>
                    <spring:message code="user.placeholder"/>
                </th>
                <th>
                    <spring:message code="car.placeholder"/>
                </th>
                <th>
                    <spring:message code="rental.placeholder"/>
                </th>
                <th>
                    <spring:message code="status.placeholder"/>
                </th>
                <th>
                    <spring:message code="decision.placeholder"/>
                </th>
            </tr>
            <tr>
            <c:forEach var="order" items="${list}">
                <td>${order.user.username}</td>
                <td>${order.car.brand} ${order.car.model}</td>
                <td>${order.rentalPeriodInDays}</td>
                <td>
                    <spring:message code="order.status.${order.orderStatus.name.toLowerCase()}"/>
                </td>
                <td>
                    <c:choose>
                       <c:when test="${order.orderStatus == 'RETURN'}">
                                <a href="/returnCar/${order.id}">
                            <button class="info">
                                    <spring:message code="close.order"/>
                            </button>
                                </a>
                                <a href="/repairInvoice/${order.id}">
                            <button class="danger">
                                    <spring:message code="repair.invoice"/>
                            </button>
                                </a>
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
</body>
<div id="footer">
    <h2>Final Project For Java Web Development Course.</h2>
</div>
</html>