<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Welcome page</title>
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
    <sec:authorize var="isAdmin" access="hasAuthority('ROLE_ADMIN')"/>
    <h1>
        <spring:message code="car.rental"/>
    </h1>
    <div class="menu-link">
        <a href="/cars/viewCars">
            <c:choose>
                <c:when test="${isAdmin}">
                    <spring:message code="car.management"/>
                </c:when>
                <c:otherwise>
                    <spring:message code="view.all.cars"/>
                </c:otherwise>
            </c:choose>
        </a>
    </div>
    <div class="menu-link">
        <c:choose>
            <c:when test="${isAdmin}">
                <a href="/orders/viewOrders/">
                    <spring:message code="order.management"/>
                </a>
            </c:when>
            <c:otherwise>
                <a href="/orders/viewMyOrders/">
                    <spring:message code="view.my.orders"/>
                </a>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>

<div id="footer">
    <h2>Final Project For Java Web Development Course.</h2>
</div>