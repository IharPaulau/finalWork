    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

	<h1>All cars</h1>
	<table border="2" width="100%" cellpadding="2">
	<tr><th>Id</th><th>Brand</th><th>model</th><th>Cost per one day</th><th>info</th><th>make an order</th><th>Edit</th><th>Delete</th></tr>
    <c:forEach var="car" items="${list}">
    <tr>
    <td>${car.id}</td>
    <td>${car.brand}</td>
    <td>${car.model}  </td>
    <td>${car.costPerOneDay}</td>
    <td><a href="cardetails/${car.id}">more info</a></td>
    <td><a href="makeorder/${car.id}">to rent</a></td>
    <td><a href="editcar/${car.id}">Edit</a></td>
    <td><a href="deletecar/${car.id}">Delete</a></td>
       </tr>
    </c:forEach>
    </table>
    <br/>
    <a href="carform">Add New Car</a>