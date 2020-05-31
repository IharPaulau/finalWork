        <%@ page pageEncoding="UTF-8"%>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <link href="/resources/css/style.css" rel="stylesheet"/>
    <div>
        <a href="?lang=en"><spring:message code="locale.en"/></a>
        <a href="?lang=ru"><spring:message code="locale.ru"/></a>
    </div>
	<h1><spring:message code="own.orders"/></h1>
	<table border="2" width="50%" cellpadding="2" >
        <tr>
            <th><spring:message code="car.placeholder"/></th>
            <th><spring:message code="rental.placeholder"/></th>
            <th><spring:message code="status.placeholder"/></th>
            <th><spring:message code="action.placeholder"/></th>
        </tr>
        <c:forEach var="order" items="${list}">
            <tr>
                        <td>${order.car.brand} ${order.car.model}</td>
                        <td>${order.rentalPeriodInDays}</td>
                        <td>
                            <c:choose>
                                <c:when test="${order.orderStatus == 'APPROVED'}">
                                    <spring:message code="order.approved"/>
                                    <spring:message code="order.shoudPay"/>
                                </c:when>
                                <c:when test="${order.orderStatus == 'REJECTED'}"><spring:message code="order.rejected"/></c:when>
                                <c:when test="${order.orderStatus == 'NOT_VERIFIED'}"><spring:message code="order.not.verified"/></c:when>

                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${order.orderStatus == 'APPROVED'}">
                                    <a href="/order/pay/${order.id}">
                                        <button><spring:message code="pay.placeholder"/></button>
                                    </a>
                                    <a href="/orders/deleteMyOrder/${order.id}">
                                        <button><spring:message code="delete.placeholder"/></button>
                                    </a>
                                    </c:when>
                                <c:when test="${order.orderStatus == 'REJECTED'}">
                                    <a href="/orders/deleteMyOrder/${order.id}">
                                        <button><spring:message code="delete.placeholder"/></button>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="/orders/deleteMyOrder/${order.id}">
                                        <button><spring:message code="delete.placeholder"/></button>
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/cars/viewCars">
        <button><spring:message code="view.all.cars"/></button>
    </a>
