<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="cust" uri="/WEB-INF/tld/custom.tld" %>
<html lang="${sessionScope.lang.toString()}">
<fmt:setLocale value="${sessionScope.lang.toString()}"/>
<fmt:setBundle basename="bundle"/>
<head>
    <title><fmt:message key="guest.welcome.title"/></title>
    <jsp:include page="../include/common.html"/>
</head>
<body>
<jsp:include page="../header/header.jsp"/>
<div class="container">
    <div align="center">
        <h1 id="h1-welcome">
            <fmt:message key="guest.welcome.h-message"/>
            <cust:helloTag/>
        </h1>
    </div>
</div>
</body>
</html>
