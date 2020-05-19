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
    <td>${order.orderApproved}  </td>
    <td><a href="deleteCar/${car.id}">Delete</a></td>
       </tr>
    </c:forEach>
    </table>
    <br/>



    <form action="/cars/viewCars">
       <button>return to all cars</button>
      </form>