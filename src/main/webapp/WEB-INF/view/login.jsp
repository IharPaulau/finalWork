<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<div>
    <form method="POST" action="${contextPath}/login">
        <h2>login</h2>
        <div>
            <span>${message}</span>
            <div>
                <label>username</label>
                <input name="username" type="text"/>
            </div>
            <div>
                <label>password</label>
                <input name="password" type="password"/>
            </div>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div>
                <button type="submit">login</button>
            </div>
            <a href="/registration">create new account</a>
        </div>
    </form>
</div>