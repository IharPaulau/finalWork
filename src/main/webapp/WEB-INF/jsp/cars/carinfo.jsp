    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

	<form:form method="post" action="/makeorder" modelAttribute="car">
	<table border="7" width="30%" cellpadding="5">
	<tr><th> characteristics </th><th>values</th></tr>

    <tr>
    <td>id</td><td>${car.id}</td>
    </tr>
    <tr>
    <td>brand</td><td>${car.brand}</td>
    </tr>
    <tr>
    <td>model</td><td>${car.model}  </td>
    </tr>
    <tr>
    <td>cost</td><td>${car.costPerOneDay}</td>
    </tr>

    </table>
    <input type="submit" value="to rent" />
   </form:form>

    <a href="viewcars">Back to main menu</a>
