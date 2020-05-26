    <%@ page pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <link href="/resources/css/style.css" rel="stylesheet"/>
    <div>
        <a href="?lang=en"><spring:message code="locale.en"/></a>
        <a href="?lang=ru"><spring:message code="locale.ru"/></a>
    </div>
	<h1>All cars</h1>
	<table border="2" width="100%" cellpadding="2">
        <tr>
            <th><spring:message code="car.id.placeholder"/></th>
            <th><spring:message code="car.brand.placeholder"/></th>
            <th><spring:message code="car.model.placeholder"/></th>
            <th><spring:message code="car.cost.placeholder"/></th>
            <th><spring:message code="car.info.placeholder"/></th>
            <sec:authorize access="hasAuthority('ROLE_USER')">
                <th><spring:message code="car.order.placeholder"/></th>
            </sec:authorize>
            <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                <th><spring:message code="car.edit.placeholder"/></th>
                <th><spring:message code="delete.placeholder"/></th>
            </sec:authorize>
        </tr>
        <c:forEach var="car" items="${list}">
        <c:if test="${car.available}">
        <tr>
            <td>${car.id}</td>
            <td>${car.brand}</td>
            <td>${car.model}  </td>
            <td>${car.costPerOneDay}</td>
            <td><a href="carDetails/${car.id}">more info</a></td>
            <sec:authorize access="hasAuthority('ROLE_USER')">
                <td><a href="/orderForm/${car.id}">to rent</a></td>
            </sec:authorize>
            <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                <td><a href="editCar/${car.id}">Edit</a></td>
                <td><a href="deleteCar/${car.id}">Delete</a></td>
            </sec:authorize>
        </tr>
        </c:if>
        </c:forEach>
    </table>

    <br/>
    <sec:authorize access="hasAuthority('ROLE_ADMIN')">
    <a href action="carForm" >
        <button><spring:message code="Add.new.car"/></button>
    </a>
    <a href ="/orders/viewOrders">
        <button><spring:message code="View.all.orders"/></button>
    </a>
    </sec:authorize>
    <a href ="/orders/viewMyOrders">
        <button><spring:message code="View.my.orders"/></button>
    </a>




