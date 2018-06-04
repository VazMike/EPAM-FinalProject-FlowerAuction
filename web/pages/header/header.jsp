<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${sessionScope.role == 'ADMIN'}">
        <jsp:include page="header-admin.jsp"/>
    </c:when>
    <c:when test="${sessionScope.role == 'USER'}">
        <jsp:include page="header-user.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="header-guest.jsp"/>
    </c:otherwise>
</c:choose>