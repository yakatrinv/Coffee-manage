<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Coffee manage project</title>
</head>
<body>

<c:choose>
    <c:when test="${sessionScope.loggedRole eq null}">
        <jsp:forward page="../../auth/error.jsp"/>
    </c:when>

    <c:when test="${sessionScope.loggedRole.roleName eq 'Administrator'}">
        <jsp:forward page="mainAdmin.jsp"/>
    </c:when>

    <c:when test="${sessionScope.loggedRole.roleName eq 'Manager'}">
        <jsp:forward page="mainManager.jsp"/>
    </c:when>

    <c:when test="${sessionScope.loggedRole.roleName eq 'Customer'}">
        <jsp:forward page="mainCustomer.jsp"/>
    </c:when>

    <c:otherwise>
        <jsp:forward page="../../auth/error.jsp"/>
    </c:otherwise>
</c:choose>

</body>
</html>
