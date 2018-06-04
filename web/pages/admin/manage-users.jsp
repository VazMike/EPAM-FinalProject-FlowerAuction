<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="${sessionScope.lang.toString()}">
<fmt:setLocale value="${sessionScope.lang.toString()}"/>
<fmt:setBundle basename="bundle"/>
<head>
    <title><fmt:message key="admin.manage-users.title"/></title>
    <jsp:include page="../include/common.html"/>
    <jsp:include page="../include/i18n.html"/>
    <script src="../../js/quick-view.js"></script>
    <script src="../../js/admin/manage-users.js"></script>
</head>
<body>
<jsp:include page="../header/header.jsp"/>
<div class="container">
    <div class="row">
        <table class="table table-bordered" id="user-table">
            <thead>
            <tr>
                <th><fmt:message key="th.id.user"/></th>
                <th><fmt:message key="admin.manage-users.user-th.login"/></th>
                <th><fmt:message key="th.action"/></th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
