<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

		<h1>Edit Employee</h1>
       <form:form method="post" action="/cars/editsave" modelAttribute="car">
      	<table >  
      	<tr>

      	 <td></td>
         <td><form:hidden path="id" /></td>
         </tr> 
         <tr>  
          <td>Brand : </td>
          <td><form:input path="brand"  /></td>
         </tr>  
         <tr>  
          <td>Model :</td>
          <td><form:input path="model" /></td>
         </tr> 
         <tr>  
          <td>Tank Fuel :</td>
          <td><form:input path="fuelTank" /></td>
         </tr> 
         
         <tr>  
          <td> </td>  
          <td><input type="submit" value="Edit Save" /></td>  
         </tr>  
        </table>  
       </form:form>  
