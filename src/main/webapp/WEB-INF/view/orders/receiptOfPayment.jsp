<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>receipt</title>
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
<table class="center">
                <tr>
                    <td>
                        <h1>
                            <spring:message code="receipt.placeholder"/>
                        </h1>
                    </td>
                </tr>
                <tr>
                    <td>
                    <h4>
                       <spring:message code="${kindOfReceipt}"/>
                       </h4>
                    </td>
                </tr>
                <tr>
                    <td>
                        <c:if test="${kindOfReceipt == 'car.rent.receipt'}">
                        <h4>
                    <spring:message code="rent.messages" arguments="${order.car.brand}, ${order.car.model}, ${order.rentalPeriodInDays}"/>
                                </h4>
                                </c:if>
                         <c:if test="${kindOfReceipt != 'car.rent.receipt'}">
                            <spring:message code="repair.messages" arguments="${order.compensationAmount}"/>
                            </c:if>
                    </td>
                   </tr>

            </table>
</body>
<a href="/orders/viewMyOrders/" class="notification">
    <span>
        <spring:message code="view.my.orders"/>
    </span>
    <c:if test="${approvedOrders > '0'}">
        <span class="badge">${approvedOrders}</span>
    </c:if>
</a>

    <div id="footer">
        <h2>Final Project For Java Web Development Course.</h2>
    </div>
    </html>