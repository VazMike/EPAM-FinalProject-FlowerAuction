<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="${sessionScope.lang.toString()}">
<fmt:setLocale value="${sessionScope.lang.toString()}"/>
<fmt:setBundle basename="bundle"/>
<head>
    <title><fmt:message key="user.profile.title"/></title>
    <jsp:include page="../include/common.html"/>
    <script src="../../js/user/profile.js"></script>
</head>
<body>
<jsp:include page="../header/header.jsp"/>
<div class="container">
    <h1><fmt:message key="user.profile.header"/></h1>
    <form id="profile-form">
        <div class="form-group row">
            <label for="profile-first-name-input" class="col-2 col-form-label">
                <fmt:message key="user.profile.first-name"/>
            </label>
            <div class="col-10">
                <input type="text" class="form-control"
                       id="profile-first-name-input" name="first-name" readonly/>
            </div>
        </div>
        <div class="form-group row">
            <label for="profile-last-name-input" class="col-2 col-form-label">
                <fmt:message key="user.profile.last-name"/>
            </label>
            <div class="col-10">
                <input type="text" class="form-control" id="profile-last-name-input" name="last-name" readonly/>
            </div>
        </div>
        <div class="form-group row">
            <label for="profile-dob-input" class="col-2 col-form-label">
                <fmt:message key="user.profile.dob"/>
            </label>
            <div class="col-10">
                <input type="date" class="form-control" id="profile-dob-input" name="dob"
                       readonly/>
            </div>
        </div>
        <div class="form-group row" id="edit-profile-button-div">
            <button type="button" class="form-control btn btn-light" onclick="makeEditable()">
                <fmt:message key="user.profile.edit-button"/>
            </button>
        </div>
        <div class="form-group row" id="edit-manage-buttons-div" style="display: none">
            <div class="col-6">
                <button type="button" class="form-control btn btn-light" onclick="editProfileServerCall()">
                    <fmt:message key="user.profile.submit-button"/>
                </button>
            </div>
            <div class="col-6">
                <button type="button" class="form-control btn btn-light" id="cancel-edits" onclick="makeUneditable()">
                    <fmt:message key="user.profile.cancel-button"/>
                </button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
