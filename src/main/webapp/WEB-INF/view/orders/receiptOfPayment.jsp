        <%@ page pageEncoding="UTF-8"%>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    <style>
    table, th, td {
      border: 1px solid black;
      border-collapse: collapse;
    }
    table.center {
      margin-left:auto;
      margin-right:auto;
    }
    </style>
    <table class="center">
        <tr>
            <td>
                <h1><spring:message code="receipt.placeholder"/></h1>
            </td>
        </tr>
        <tr>
            <td>
                <h4>Thank you very much for using our services,<br>
                please keep this check until the end of the rental period.</br>
                --------------------------------------------------------------</br>
                Your order car: ${order.car.brand} ${order.car.model}</br>
                For a period of: ${order.rentalPeriodInDays} days. </br>
                </h4>
            </td>
        </tr>
    </table>