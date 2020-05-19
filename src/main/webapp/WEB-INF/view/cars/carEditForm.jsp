<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

		<h1>Edit Car</h1>
       <form:form method="post" action="/cars/editSave" modelAttribute="car">
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
          <td>cost :</td>
          <td><form:input path="costPerOneDay" /></td>
         </tr> 
         
         <tr>  
          <td> </td>  
          <td><input type="submit" value="Edit Save" /></td>  
         </tr>  
        </table>  
       </form:form>  
