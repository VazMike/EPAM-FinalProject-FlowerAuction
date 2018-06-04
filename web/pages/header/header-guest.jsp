<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang.toString()}"/>
<fmt:setBundle basename="bundle"/>
<script src="../../js/header/header-guest.js"></script>
<div style="display: none">
    <form id="welcome-form" action="welcome" method="get"></form>
</div>
<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
    <div class="d-flex">
        <div class="p-2">
            <a class="nav-brand" href="/">
                <fmt:message key="header.brand-name"/> <span class="fa fa-leaf"></span>
            </a>
        </div>
    </div>
    <div class="d-flex flex-row">
        <div class="p-2">
            <a href="" data-toggle="modal" data-target="#sign-in-modal">
                <fmt:message key="form.sign-in.title"/>
            </a>
        </div>
        <div class="p-2">
            <a href="" data-toggle="modal" data-target="#sign-up-modal">
                <fmt:message key="form.sign-up.title"/>
            </a>
        </div>
    </div>
</nav>
<div class="modal fade" id="sign-in-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title">
                    <fmt:message key="form.sign-in.title"/>
                </h1>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span>&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <jsp:include page="../form/sign-in.jsp"/>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="sign-up-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title">
                    <fmt:message key="form.sign-up.title"/>
                </h1>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span>&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <jsp:include page="../form/sign-up.jsp"/>
            </div>
        </div>
    </div>
</div>