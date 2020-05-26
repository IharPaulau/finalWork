<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Registration page</title>
    <link href="/resources/css/style.css" rel="stylesheet"/>
</head>
<body>
<div>
    <a href="?lang=en"><spring:message code="locale.en"/></a>
    <a href="?lang=ru"><spring:message code="locale.ru"/></a>
</div>
<div class="container">
    <form:form action="/registration" method="POST" class="form-signin" modelAttribute="registrationForm">
        <h2 class="form-heading"><spring:message code="registration.form"/></h2>
        <div class="form-attributes">
            <div class="form-attribute">
                <label><spring:message code="username.label"/></label>
                <spring:message code="username.placeholder" var="usernamePlaceholder"/>
                <form:input type="text" path="name" placeholder="${usernamePlaceholder}"/>
                <form:errors path="name" class="error-message"/>
            </div>
            <div class="form-attribute">
                <label><spring:message code="password.label"/></label>
                <spring:message code="password.placeholder" var="passwordPlaceholder"/>
                <form:input type="text" path="password" placeholder="${passwordPlaceholder}"/>
                <form:errors path="password" class="error-message"/>
            </div>
            <div class="form-attribute">
                <label><spring:message code="confirmPassword.label"/></label>
                <spring:message code="confirmPassword.placeholder" var="confirmPasswordPlaceholder"/>
                <form:input type="text" path="confirmPassword" placeholder="${confirmPasswordPlaceholder}"/>
                <form:errors path="confirmPassword" class="error-message"/>
            </div>
            <div class="form-attribute">
                <label><spring:message code="email.label"/></label>
                <spring:message code="email.placeholder" var="emailPlaceholder"/>
                <form:input type="text" path="email" placeholder="${emailPlaceholder}"/>
                <form:errors path="email" class="error-message"/>
            </div>
        </div>
        <div>
            <button class="button" type="submit"><spring:message code="registration.button"/></button>
        </div>
    </form:form>
    <div id="footer">
        <h2>Final Project For Java Web Development Course.</h2>
    </div>
</div>
</body>
</html>