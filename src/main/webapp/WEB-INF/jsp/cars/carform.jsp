<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

		<h1>Add New Car</h1>
       <form:form method="post" action="/save" modelAttribute="car">
      	<table>
         <tr>  
          <td>brand : </td>
          <td><form:input path="brand"  /></td>
         </tr>
         <tr>  
          <td>model :</td>
          <td><form:input path="model" /></td>
         </tr> 
         <tr>
                            <td>cost :</td>
                            <td><form:input path="costPerOneDay" /></td>
                  </tr>
         <tr>  
          <td> </td>  
          <td><input type="submit" value="+" />
          </td>
         </tr>
        </table>  
       </form:form>

        <a href="viewcars">Back to main menu</a>
