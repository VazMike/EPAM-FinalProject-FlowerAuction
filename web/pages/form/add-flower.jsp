<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang.toString()}"/>
<fmt:setBundle basename="bundle"/>
<script src="../../js/form/add-flower.js"></script>
<form>
    <div class="form-group">
        <fmt:message key="form.add-flower.name" var="name"/>
        <input type="text" class="form-control" placeholder="${name}" id="add-flower-name-input"/>
        <small class="text-danger" id="add-flower-name-error-small"></small>
    </div>
    <div class="form-group">
        <fmt:message key="form.add-flower.value" var="value"/>
        <input type="text" class="form-control" placeholder="${value}" id="add-flower-value-input"/>
        <small class="text-danger" id="add-flower-value-error-small"></small>
    </div>
    <div class="form-group">
        <button type="button" class="form-control btn btn-light" onclick="addFlowerServerCall()">
            <fmt:message key="form.submit"/>
        </button>
    </div>
</form>
