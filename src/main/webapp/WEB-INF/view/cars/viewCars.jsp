    <%@ page pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
            <th><spring:message code="car.brand.placeholder"/></th>
            <th><spring:message code="car.cost.placeholder"/></th>
            <th><spring:message code="car.info.placeholder"/></th>
            <th><spring:message code="car.order.placeholder"/></th>
            <th><spring:message code="car.edit.placeholder"/></th>
            <th><spring:message code="delete.placeholder"/></th>
        </tr>
        <c:forEach var="car" items="${list}">
        <tr>
            <td>${car.id}</td>
            <td>${car.brand}</td>
            <td>${car.model}  </td>
            <td>${car.costPerOneDay}</td>
            <td><a href="carDetails/${car.id}">more info</a></td>
            <td><a href="/makeOrder/${car.id}">to rent</a></td>
            <td><a href="editCar/${car.id}">Edit</a></td>
            <td><a href="deleteCar/${car.id}">Delete</a></td>
        </tr>
        </c:forEach>
    </table>

    <br/>
    <form action="carForm" >
        <button><spring:message code="Add.new.car"/></button>
    </form>
    <form action="/orders/viewMyOrders">
        <button><spring:message code="View.my.cars"/></button>
    </form>
    <form action="/orders/viewOrders">
        <button><spring:message code="View.all.orders"/></button>
    </form>


