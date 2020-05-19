
<h1>Order Form</h1>
<h3> To get started, please read our car rental terms. </h3>
<form name="order form" form method="post" action="/saveOrder" modelAttribute="order">

	<p>enter your passport series  <input maxlength="2" path="passportSeries" size="2" type="text"></p>
                        <form:input path="brand"  />

	<p>enter your&nbsp;passport number <input maxlength="6" name="passport number" size="6" type="text"></p>
	<p>enter your&nbsp;personal id number  <input maxlength="14" name="id citizen" size="14" type="text"></p>
	<p>enter the desired rental period in days  <select name="rental period"><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option>   <option value="7">7</option></select></p>
	<p>If you have read and agree to our car rental rules, check the box here.<input name="YES or NO" type="checkbox" value="YES"></p>

<p> <input name="confirm order" type="submit" value="confirm order"> </form> <form action="cars/viewCars" ><button>refuse order</button>  </p>


