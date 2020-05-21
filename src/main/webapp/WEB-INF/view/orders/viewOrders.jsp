    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

	<h1>All orders</h1>
	<table border="2" width="50%" cellpadding="2" >
	<tr><th>User</th><<th>Car</th><th>Rental period</th><th>status</th><th>decision </th></tr>
    <c:forEach var="order" items="${list}">
        <tr>
            <td>....</td>
            <td>....</td>
            <td>${order.rentalPeriodInDays}</td>
            <td>
                <c:choose>
                    <c:when test="${order.orderApproved}">
                        this order is approved
                        <br />
                    </c:when>
                    <c:when test="${order.orderApproved == 'false'}">
                        this order is rejected
                        <br />
                    </c:when>
                    <c:otherwise>
                        this order not yet verified
                        <br />
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <button><a href="/approve/${order.id}">approved</a></button>

                <button><a href="/reject/${order.id}">rejected</a></button>
            </td>
        </tr>
    </c:forEach>
    </table>
    <br/>



    <form action="/cars/viewCars">
       <button>return to all cars</button>
      </form>