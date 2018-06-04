<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="${sessionScope.lang.toString()}">
<fmt:setLocale value="${sessionScope.lang.toString()}"/>
<fmt:setBundle basename="bundle"/>
<head>
    <title><fmt:message key="admin.manage-auctions.title"/></title>
    <jsp:include page="../include/common.html"/>
    <jsp:include page="../include/i18n.html"/>
    <script src="../../js/quick-view.js"></script>
    <script src="../../js/admin/manage-auctions.js"></script>
</head>
<body>
<jsp:include page="../header/header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-6">
            <table class="table table-bordered" id="auction-table">
                <thead>
                <tr>
                    <th><fmt:message key="th.id.auction"/></th>
                    <th><fmt:message key="admin.manage-auctions.auction-th.name"/></th>
                    <th><fmt:message key="admin.manage-auctions.auction-th.event-date"/></th>
                    <th><fmt:message key="th.action"/></th>
                </tr>
                </thead>
            </table>
        </div>
        <div class="col-3">
            <table class="table table-bordered" id="buyer-table">
                <thead>
                <tr>
                    <th><fmt:message key="th.id.buyer"/></th>
                    <th><fmt:message key="th.action"/></th>
                </tr>
                </thead>
            </table>
        </div>
        <div class="col-3">
            <table class="table table-bordered" id="flower-table">
                <thead>
                <tr>
                    <th><fmt:message key="th.id.flower"/></th>
                    <th><fmt:message key="th.action"/></th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>

<div class="modal fade" id="add-auction-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title"><fmt:message key="modal.add.auction.title"/></h1>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span>&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <jsp:include page="../form/add-auction.jsp"/>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="edit-auction-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title"><fmt:message key="modal.edit.auction.title"/></h1>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span>&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <jsp:include page="../form/edit-auction.jsp"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>