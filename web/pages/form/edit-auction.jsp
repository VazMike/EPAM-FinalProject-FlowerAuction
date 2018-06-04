<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang.toString()}"/>
<fmt:setBundle basename="bundle"/>
<script src="../../js/form/edit-auction.js"></script>
<form>
    <div class="form-group">
        <fmt:message key="form.edit-auction.name" var="name"/>
        <input type="text" class="form-control" placeholder="${name}" id="edit-auction-name-input"/>
        <small class="text-danger" id="edit-auction-name-error-small"></small>
    </div>
    <div class="form-group">
        <fmt:message key="form.edit-auction.event-date" var="eventDate"/>
        <input type="datetime-local" class="form-control" placeholder="${eventDate}" id="edit-auction-event-date-input"/>
        <small class="text-danger" id="edit-auction-event-date-error-small"></small>
    </div>
    <div class="form-group">
        <button type="button" class="form-control btn btn-light" onclick="editAuctionServerCall()">
            <fmt:message key="form.submit"/>
        </button>
    </div>
</form>
