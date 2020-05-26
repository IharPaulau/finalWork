        <%@ page pageEncoding="UTF-8"%>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <link href="/resources/css/style.css" rel="stylesheet"/>
    <div>
        <a href="?lang=en"><spring:message code="locale.en"/></a>
        <a href="?lang=ru"><spring:message code="locale.ru"/></a>
    </div>
    <h1><spring:message code="add.new.car"/></h1>
    <form:form method="post" action="/car/save" modelAttribute="car">
        <table>
            <tr>
                <th><spring:message code="car.characteristics"/></th>
                <th><spring:message code="values"/></th>
            </tr>
            <tr>
                <td><spring:message code="car.brand.placeholder"/></td>
                <td><form:input path="brand"/></td>
                <td><form:errors path="brand" class="error-message"/></td>
            </tr>
            <tr>
                <td><spring:message code="car.model.placeholder"/></td>
                <td><form:input path="model"/></td>
                <td><form:errors path="model" class="error-message"/></td>
            </tr>
            <tr>
                <td><spring:message code="car.body.placeholder"/></td>
                <td><form:input path="typeBody"/></td>
                <td><form:errors path="typeBody" class="error-message"/></td>
            </tr>
            <tr>
                <td><spring:message code="car.engine.placeholder"/></td>
                <td><form:input path="typeEngine"/></td>
                <td><form:errors path="typeBody" class="error-message"/></td>
            </tr>
            <tr>
                <td><spring:message code="car.color.placeholder"/></td>
                <td><form:input path="bodyColor"/></td>
                <td><form:errors path="bodyColor" class="error-message"/></td>
            </tr>
            <tr>
                <td><spring:message code="car.transmission.placeholder"/></td>
                <td><form:input path="transmission"/></td>
                <td><form:errors path="transmission" class="error-message"/></td>
            </tr>
            <tr>
                <td><spring:message code="car.cost.placeholder"/></td>
                <td><form:input path="costPerOneDay"/></td>
                <td><form:errors path="costPerOneDay" class="error-message"/></td>
            </tr>
                <spring:message code="button.to.add" var="add"/>
                <td><input type="submit" value="${add}" /></td>
            </tr>
        </table>  
    </form:form>
    <form action="/cars/viewCars" >
        <button><spring:message code="View.all.cars"/></button>
    </form>