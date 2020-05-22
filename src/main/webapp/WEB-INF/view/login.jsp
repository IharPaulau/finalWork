    <%@ page pageEncoding="UTF-8" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

    <html>
    <head>
        <title>Login page</title>
        <link href="/resources/css/style.css" rel="stylesheet"/>
    </head>
    <body>
    <div>
        <a href="?lang=en"><spring:message code="locale.en"/></a>
        <a href="?lang=ru"><spring:message code="locale.ru"/></a>
    </div>
    <div>
        <form method="POST" action="/login" class="form-signin">
            <h2 class="form-heading"><spring:message code="login.form"/></h2>
            <span class="success-message">${message}</span>
            <div class="form-attributes">
                <label><spring:message code="username.label"/></label>
                <spring:message code="username.placeholder" var="usernamePlaceholder"/>
                <input name="username" type="text" placeholder="${usernamePlaceholder}"/>

                <label> <spring:message code="password.label"/></label>
                <spring:message code="password.placeholder" var="passwordPlaceholder"/>
                <input name="password" type="password" placeholder="${passwordPlaceholder}"/>
                <span class="error-message">${error}</span>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button class="button" type="submit"><spring:message code="login.button"/></button>
            <div id="create-account">
                <a href="/registration"><spring:message code="create.account"/></a>
            </div>
        </form>
    </div>
    <div id="footer">
        <h2>Final Project For Java Web Development Course.</h2>
    </div>
    </body>
    </html>