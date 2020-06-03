<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="/resources/css/style.css" rel="stylesheet"/>

     <div>
        <a href="?lang=en"><spring:message code="locale.en"/></a>
        <a href="?lang=ru"><spring:message code="locale.ru"/></a>
    </div>

    <div class="container">
	<h1><spring:message code="fill.order"/></h1>
    <form:form method="post" action="/repairInvoice/${id}" modelAttribute="order">
    <div class="form-attribute">
        <label>
        amount of damage to return
        </label>
        <form:input type="text" path="compensationAmount" />
    </div>
        <button type="submit">
            <spring:message code="button.to.save"/>
        </button>
    </form:form>
    </div>






    <form action="/cars/viewCars" >
       <button><spring:message code="view.all.cars"/></button>
    </form>
