<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang.toString()}"/>
<fmt:setBundle basename="bundle"/>
<script src="../../js/form/sign-in.js"></script>
<form id="sign-in-form">
    <div class="form-group">
        <fmt:message key="form.sign-in.login" var="login"/>
        <input type="text" class="form-control" placeholder="${login}" id="sign-in-login-input" required/>
        <small class="text-danger" id="sign-in-login-error-small"></small>
    </div>
    <div class="form-group">
        <fmt:message key="form.sign-in.password" var="password"/>
        <input type="password" class="form-control" placeholder="${password}" id="sign-in-password-input" required/>
        <small class="text-danger" id="sign-in-password-error-small"></small>
    </div>
    <div class="from-group">
        <button type="button" class="form-control btn btn-light" onclick="signInServerCall()">
            <fmt:message key="form.submit"/>
        </button>
    </div>
</form>