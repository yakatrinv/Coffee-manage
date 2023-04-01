<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <span>
        <a href="${pageContext.request.contextPath}/coffee-manage?command=getLoginPage">Главная страница</a>
    </span>

    <c:if test="${not empty sessionScope.loggedUser}">
        Hi, ${sessionScope.loggedUser.login}

        <span></span>
        <c:if test="${sessionScope.userRoles.size() gt 1}">
            <select name="role" onchange="document.location=this.options[this.selectedIndex].value">
                <c:forEach var="roleItem" items="${sessionScope.userRoles}">
                    <c:choose>
                        <c:when test="${sessionScope.loggedRole eq null}">
                            <option value="coffee-manage?command=changeRole&role=${roleItem.roleName}">${roleItem.roleName}</option>
                        </c:when>
                        <c:when test="${roleItem.roleName eq sessionScope.loggedRole.roleName}">
                            <option selected value="coffee-manage?command=changeRole&role=${roleItem.roleName}" >${roleItem.roleName}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="coffee-manage?command=changeRole&role=${roleItem.roleName}">${roleItem.roleName}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </c:if>
        <span></span>
        <a href="${pageContext.request.contextPath}/coffee-manage?command=logout">Выйти</a>
    </c:if>
</div>