<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang.toString()}"/>
<fmt:setBundle basename="bundle"/>
<script src="../../js/form/add-credit-card.js"></script>
<form>
    <div class="form-group">
        <fmt:message key="form.add-credit-card.number" var="number"/>
        <input type="text" class="form-control" placeholder="${number}" id="add-credit-card-number-input"/>
        <small class="text-danger" id="add-credit-card-number-error-small"></small>
    </div>
    <div class="form-group">
        <fmt:message key="form.add-credit-card.password" var="password"/>
        <input type="password" class="form-control" placeholder="${password}" id="add-credit-card-password-input"/>
        <small class="text-danger" id="add-credit-card-password-error-small"></small>
    </div>
    <div class="form-group">
        <fmt:message key="form.add-credit-card.balance" var="balance"/>
        <input type="text" class="form-control" placeholder="${balance}" id="add-credit-card-balance-input"/>
        <small class="text-danger" id="add-credit-card-balance-error-small"></small>
    </div>
    <div class="form-group">
        <button type="button" class="form-control btn btn-light" onclick="addCreditCardServerCall()">
            <fmt:message key="form.submit"/>
        </button>
    </div>
</form>
