<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <span>
        <a href="${pageContext.request.contextPath}/coffee-manage?command=getLoginPage">Home page</a>
    </span>

    <c:if test="${not empty sessionScope.loggedUser}">
        Hi, ${sessionScope.loggedUser.login}

        <span></span>
        <c:if test="${sessionScope.userRoles.size() gt 0}">
            <select name="role">
                <c:forEach var="role" items="${sessionScope.userRoles}">
                    <c:choose>
                        <c:when test="${sessionScope.loggedRoles eq null}">
                            <option value="role">${role.roleName}</option>
                        </c:when>
                        <c:when test="${role.roleName eq sessionScope.loggedRoles.roleName}">
                            <option value="role" selected>${role.roleName}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="role">${role.roleName}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </c:if>
        <span></span>
        <a href="${pageContext.request.contextPath}/coffee-manage?command=logout">Logout</a>
    </c:if>
</div>