<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang.toString()}"/>
<fmt:setBundle basename="bundle"/>
<script src="../../js/header/header-user.js"></script>
<nav class="navbar navbar-expand-lg" style="background-color: #e3f2fd;">
    <a class="nav-brand" href="/"><fmt:message key="header.brand-name"/> <span class="fa fa-leaf"></span></a>
    <ul class="navbar-nav mr-auto">
        <li class="nav-item">
            <a class="nav-link" href="javascript:goToBusinessProfile()">
                <fmt:message key="header.admin.business-profile"/>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="javascript:goToRequestAuction()">
                <fmt:message key="header.admin.request"/>
            </a>
        </li>
    </ul>
    <ul class="navbar-nav">
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <c:out value="${sessionScope.login}"/>
            </a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="javascript:goToProfile()">
                    <fmt:message key="header.admin.profile"/>
                </a>
                <a class="dropdown-item" href="javascript:goToSettings()">
                    <fmt:message key="header.admin.settings"/>
                </a>
                <a class="dropdown-item" href="javascript:signOut()">
                    <fmt:message key="header.admin.sign-out"/>
                </a>
            </div>
        </li>
    </ul>
</nav>
<form id="profile-form" action="profile" method="get"></form>
<form id="business-profile-form" action="business-profile" method="get"></form>
<form id="settings-form" action="settings" method="get"></form>
<form id="welcome-form" action="welcome" method="get"></form>
<form id="request-auction-form" action="request" method="get"></form>
