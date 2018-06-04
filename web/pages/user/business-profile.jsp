<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="${sessionScope.lang.toString()}">
<fmt:setLocale value="${sessionScope.lang.toString()}"/>
<fmt:setBundle basename="bundle"/>
<head>
    <title><fmt:message key="user.business-profile.title"/></title>
    <jsp:include page="../include/common.html"/>
    <jsp:include page="../include/i18n.html"/>
    <script src="../../js/quick-view.js"></script>
    <script src="../../js/user/business-profile.js"></script>
</head>
<body>
<jsp:include page="../header/header.jsp"/>
<div class="container">
    <div class="row">
        <table class="table table-bordered" id="flower-table">
            <thead>
            <tr>
                <th><fmt:message key="th.id.flower"/></th>
                <th><fmt:message key="user.business-profile.flower-th.name"/></th>
                <th><fmt:message key="user.business-profile.flower-th.value"/></th>
                <th><fmt:message key="th.id.auction"/></th>
                <th><fmt:message key="th.action"/></th>
            </tr>
            </thead>
        </table>
    </div>
    <div class="row">
        <table class="table table-bordered" id="credit-card-table">
            <thead>
            <tr>
                <th><fmt:message key="th.id.credit-card"/></th>
                <th><fmt:message key="user.business-profile.credit-card-th.number"/></th>
                <th><fmt:message key="user.business-profile.credit-card-th.balance"/></th>
                <th><fmt:message key="th.action"/></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<div class="modal fade" id="add-flower-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title"><fmt:message key="modal.add.flower.title"/></h1>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span>&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <jsp:include page="../form/add-flower.jsp"/>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="edit-flower-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title"><fmt:message key="modal.edit.flower.title"/></h1>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span>&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <jsp:include page="../form/edit-flower.jsp"/>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="add-credit-card-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title"><fmt:message key="modal.add.credit-card.title"/></h1>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span>&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <jsp:include page="../form/add-credit-card.jsp"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>
