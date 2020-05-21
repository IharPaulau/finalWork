    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

	<h1>All my orders</h1>
	<table border="2" width="50%" cellpadding="2" >
	<tr><th>Car</th><th>Rental period</th><th>status</th><th>Delete</th></tr>
    <c:forEach var="order" items="${list}">
    <tr>
        <td>
         <c:forEach var="car" items="${cars}">
        <c:if test="${car.id eq order.carId}"> ${car.brand}</c:if>
        </c:forEach>
        </td>
        <td>
        ${order.rentalPeriodInDays}
        </td>
        <td>
            <c:choose>
                <c:when test="${order.orderApproved}">
                    your order is approved
                    <br />
                </c:when>
                <c:when test="${order.orderApproved == 'false'}">
                    your order is rejected
                    <br />
                </c:when>
                <c:otherwise>
                    not yet verified 
                    <br />
                </c:otherwise>
            </c:choose>
        </td>
        <td>
        <a href="deleteMyOrder/${order.id}">Delete</a>
        </td>
    </tr>
    </c:forEach>
    </table>
    <br/>

    <form action="/cars/viewCars">
       <button>return to all cars</button>
      </form>