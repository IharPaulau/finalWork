        <%@ page pageEncoding="UTF-8"%>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <link href="/resources/css/style.css" rel="stylesheet"/>
    <div>
        <a href="?lang=en"><spring:message code="locale.en"/></a>
        <a href="?lang=ru"><spring:message code="locale.ru"/></a>
    </div>
	<h1><spring:message code="fill.order"/></h1>
    <form:form method="post" action="/saveOrder/${carId}" modelAttribute="order">
        <table>
            <tr>
                <td><spring:message code="passport.series.placeholder"/></td>
                <td><form:input path="passportSeries"  /></td>
            </tr>
            <tr>
                <td><spring:message code="passport.number.placeholder"/></td>
                <td><form:input path="passportNumber" /></td>
            </tr>
            <tr>
                <td><spring:message code="id.number.placeholder"/></td>
                <td><form:input path="passportId" /></td> </tr>
            <tr>
                <td><spring:message code="desired.rental.period"/></td>
                <td><form:input path="rentalPeriodInDays" /></td>
            </tr>
            <tr>
                <td></td>
                <spring:message code="button.to.confirm" var="confirmOrder"/>
                <td><input type="submit" value="${confirmOrder}" /></td>
            </tr>
        </table>
    </form:form>
    <form action="/cars/viewCars" >
       <button><spring:message code="View.all.cars"/></button>
    </form>
