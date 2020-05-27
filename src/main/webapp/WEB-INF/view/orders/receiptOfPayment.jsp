        <%@ page pageEncoding="UTF-8"%>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>








    <html>
    <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">



   <html>
   <head>
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <!-- Add icon library -->
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
   <style>

    table, th, td {
      border: 1px solid black;
      border-collapse: collapse;
    }
    table.center {
      margin-left:auto;
      margin-right:auto;
    }

   .btn {
     background-color: DodgerBlue;
     border: none;
     color: white;
     padding: 12px 30px;
     cursor: pointer;
     font-size: 20px;
   }

   /* Darker background on mouse-over */
   .btn:hover {
     background-color: green;
   }
   </style>
   </head>
   <body>
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
                   </h4>
                </td>
           </tr>
           <tr>
               <td>
                   <h4>Your order car: ${order.car.brand} ${order.car.model}</br>
                   For a period of: ${order.rentalPeriodInDays} days. </br>
                   </h4>
               </td>
           </tr>
           <tr>
           <td>
                 <button class="btn" style="width:100%"><i class="fa fa-download"></i> Download</button>
           </td>
           </tr>
           </table>
           </body>
   </html>

   <a href="/cars/viewCars">back to all cars</a>