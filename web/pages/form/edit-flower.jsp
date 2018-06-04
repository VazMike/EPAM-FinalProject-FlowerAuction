<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang.toString()}"/>
<fmt:setBundle basename="bundle"/>
<script src="../../js/form/edit-flower.js"></script>
<form>
    <div class="form-group">
        <fmt:message key="form.edit-flower.name" var="name"/>
        <input type="text" class="form-control" placeholder="${name}" id="edit-flower-name-input"/>
        <small class="text-danger" id="edit-flower-name-error-small"></small>
    </div>
    <div class="form-group">
        <fmt:message key="form.edit-flower.name" var="value"/>
        <input type="text" class="form-control" placeholder="${value}" id="edit-flower-value-input"/>
        <small class="text-danger" id="edit-flower-value-error-small"></small>
    </div>
    <div class="form-group">
        <button type="button" class="form-control btn btn-light" onclick="editFlowerServerCall()">
            <fmt:message key="form.submit"/>
        </button>
    </div>
</form>
