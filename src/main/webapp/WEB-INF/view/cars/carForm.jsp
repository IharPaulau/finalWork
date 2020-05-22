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
    <form:form method="post" action="/save" modelAttribute="car">
        <table>
            <tr>
                <td><spring:message code="car.brand.placeholder"/>:</td>
                <td><form:input path="brand"  /></td>
            </tr>
            <tr>
                <td><spring:message code="car.model.placeholder"/>:</td>
                <td><form:input path="model" /></td>
            </tr>
            <tr>
                <td><spring:message code="car.cost.placeholder"/>:</td>
                <td><form:input path="costPerOneDay" /></td>
            </tr>
            <tr>
                <td></td>
                <spring:message code="button.to.add" var="add"/>
                <td><input type="submit" value="${add}" /></td>
            </tr>
        </table>  
    </form:form>
    <form action="viewCars" >
        <button><spring:message code="View.all.cars"/></button>
    </form>