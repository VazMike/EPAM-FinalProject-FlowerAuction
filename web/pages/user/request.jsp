<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="${sessionScope.lang.toString()}">
<fmt:setLocale value="${sessionScope.lang.toString()}"/>
<fmt:setBundle basename="bundle"/>
<head>
    <title><fmt:message key="user.request.title"/></title>
    <jsp:include page="../include/common.html"/>
    <jsp:include page="../include/i18n.html"/>
    <script src="../../js/quick-view.js"></script>
    <script src="../../js/user/request.js"></script>
</head>
<body>
<jsp:include page="../header/header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-6">
            <table class="table table-bordered" id="request-auction-table">
                <thead>
                <tr>
                    <th><fmt:message key="th.id.auction"/></th>
                    <th><fmt:message key="th.action"/></th>
                </tr>
                </thead>
            </table>
        </div>
        <div class="col-6">
            <table class="table table-bordered" id="request-flower-table">
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
</body>
</html>
