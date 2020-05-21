<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div>
    <form:form action="/registration" method="POST" modelAttribute="registrationForm">
        <div>
        <h2>Registration</h2>
        <div>
            <label>username</label>
            <form:input type="text" path="name"/>
        </div>
        <div>
            <label>password</label>
            <form:input type="text" path="password"/>
            </div>
        <div>
            <label>confirmPassword</label>
            <form:input type="text" path="confirmPassword"/>
        </div>
        <div>
            <label>email</label>
            <form:input type="text" path="email"/>
            </div>
        <div>
            <button class="button" type="submit">register</button>
        </div>
        </div>
    </form:form>
</div>