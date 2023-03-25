<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <c:if test="${not empty sessionScope.loggedUser}">
        Hi, ${sessionScope.loggedUser.login}

        <span></span>
        <c:if test="${sessionScope.userRoles.size() gt 0}">
            <select name="role">
                <c:forEach var="role" items="${sessionScope.userRoles}">
                    <option value="role">${role.roleName}</option>
                </c:forEach>
            </select>
        </c:if>
        <span></span>
    </c:if>

    <a href="${pageContext.request.contextPath}/coffee-manage?command=logout">Logout</a>
    <a href="${pageContext.request.contextPath}/coffee-manage?command=getLoginPage">Home page</a>
</div>