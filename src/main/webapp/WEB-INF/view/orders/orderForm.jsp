<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

		<h1>fill out the order form:</h1>
       <form:form method="post" action="/saveOrder" modelAttribute="order">
      	<table>
         <tr>      <td> enter your passport series: </td>     <td><form:input path="passportSeries"  /></td> </tr>

         <tr>       <td>enter your passport number: </td>      <td><form:input path="passportNumber" /></td> </tr>

         <tr>       <td>enter your personal id number: </td>      <td><form:input path="passportId" /></td> </tr>

         <tr>       <td>enter the desired rental period in days:  </td>      <td><form:input path="rentalPeriodInDays" /></td> </tr>
        <form:hidden path="carId" value="${id}"/>
         <tr>      <td> </td>    <td><input type="submit" value="confirm order" /> </td></tr>

                </table>
       </form:form>

       <form action="/cars/viewCars" >
                  <button>view all car</button>
                 </form>
