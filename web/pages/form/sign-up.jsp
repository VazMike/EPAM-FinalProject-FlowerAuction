<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang.toString()}"/>
<fmt:setBundle basename="bundle"/>
<script src="../../js/form/sign-up.js"></script>
<form id="sign-up-form">
    <div class="form-group">
        <fmt:message key="form.sign-up.login" var="login"/>
        <input type="text" class="form-control" placeholder="${login}" id="sign-up-login-input" required/>
        <small class="text-danger" id="sign-up-login-error-small"></small>
    </div>
    <div class="form-group">
        <fmt:message key="form.sign-up.password" var="password"/>
        <input type="password" class="form-control" placeholder="${password}" id="sign-up-password-input" required/>
        <small class="text-danger" id="sign-up-password-error-small"></small>
    </div>
    <div class="form-group">
        <fmt:message key="form.sign-up.confirm-password" var="confirmPassword"/>
        <input type="password" class="form-control" placeholder="${confirmPassword}" id="sign-up-confirm-password-input" required/>
        <small class="text-danger" id="sign-up-confirm-password-error-small"></small>
    </div>
    <div class="form-group">
        <button type="button" class="form-control btn btn-light" onclick="signUpServerCall()">
            <fmt:message key="form.submit"/>
        </button>
    </div>
</form>
