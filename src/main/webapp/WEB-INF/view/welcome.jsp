    <%@ page pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>

    <link href="/resources/css/style.css" rel="stylesheet"/>
    <div>
        <a href="<%=request.getContextPath()%>?lang=en"><spring:message code="locale.en"/></a>
        <a href="<%=request.getContextPath()%>?lang=ru"><spring:message code="locale.ru"/></a>
    </div>
    <div>
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit"><spring:message code="logout.button"/></button>
        </form>
        <div>
            <a href="cars/carForm"><spring:message code="Add.new.car"/></a>
            <p></p>
            <a href="cars/viewCars"><spring:message code="View.all.cars"/></a>
        </div>
    </div>


${pageContext.request.userPrincipal.name}