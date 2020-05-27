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
	<table border="2" width="50%" cellpadding="2" >
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
                        <c:when test="${order.orderApproved}">
                            <spring:message code="order.approved"/>
                            <!-- TODO only one active button-->
                        </c:when>
                        <c:when test="${order.orderApproved == 'false'}">
                            <spring:message code="order.rejected"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="order.not.verified"/>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${order.orderApproved}">
                            <button><a href="/reject/${order.id}"><spring:message code="rejected"/></a></button>
                        </c:when>
                        <c:when test="${order.orderApproved == 'false'}">
                            <button><a href="/approve/${order.id}"><spring:message code="approved"/></a></button>
                        </c:when>
                        <c:otherwise>
                            <button><a href="/reject/${order.id}"><spring:message code="rejected"/></a></button>
                            <button><a href="/approve/${order.id}"><spring:message code="approved"/></a></button>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br/>



    <form action="/cars/viewCars">
       <button><spring:message code="View.all.cars"/></button>
      </form>