<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="/resources/css/style.css" rel="stylesheet"/>
    <div>
        <a href="?lang=en"><spring:message code="locale.en"/></a>
        <a href="?lang=ru"><spring:message code="locale.ru"/></a>
        <br/>
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
                        <c:when test="${order.orderStatus == 'APPROVED'}">
                            <spring:message code="order.approved"/>
                            <!-- TODO only one active button-->
                        </c:when>
                        <c:when test="${order.orderStatus == 'REJECTED'}">
                            <spring:message code="order.rejected"/>
                        </c:when>
                        <c:when test="${order.orderStatus == 'NOT_VERIFIED'}">
                            <spring:message code="order.not.verified"/>
                        </c:when>
                        <c:when test="${order.orderStatus == 'RETURN'}">
                            <spring:message code="car.checking.damage"/>
                        </c:when>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${order.orderStatus == 'APPROVED'}">
                            <button><a href="/reject/${order.id}"><spring:message code="rejected"/></a></button>
                        </c:when>
                        <c:when test="${order.orderStatus == 'REJECTED'}">
                            <button><a href="/approve/${order.id}"><spring:message code="approved"/></a></button>
                        </c:when>
                        <c:when test="${order.orderStatus == 'NOT_VERIFIED'}">
                            <button><a href="/reject/${order.id}"><spring:message code="rejected"/></a></button>
                            <button><a href="/approve/${order.id}"><spring:message code="approved"/></a></button>
                        </c:when>
                        <c:when test="${order.orderStatus == 'RETURN'}">
                            <button><a href="/returnCar/${order.id}"><spring:message code="close.order"/></a></button>
                            <button><a href="/repairInvoice/${order.id}"><spring:message code="repair.invoice"/></a></button>
                        </c:when>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br/>



    <form action="/cars/viewCars">
       <button><spring:message code="view.all.cars"/></button>
      </form>