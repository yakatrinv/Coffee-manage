<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <span>
        <a href="${pageContext.request.contextPath}/coffee-manage?command=getHomePage">Главная страница</a>
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

        <p>
            <a href="${pageContext.request.contextPath}/coffee-manage?command=editProfileCustomer">Профиль</a>
        </p>

        <p>
            <a href="${pageContext.request.contextPath}/coffee-manage?command=editPassCurrentUser">Сменить пароль</a>
        </p>

        <c:if test="${sessionScope.loggedRole.roleName eq 'Customer'}">
            <p>
                <a href="${pageContext.request.contextPath}/coffee-manage?command=customerPurchases&searchCustomer=${sessionScope.loggedCustomer.id}">История покупок</a>
            </p>
        </c:if>

        <a href="${pageContext.request.contextPath}/coffee-manage?command=logout">Выйти</a>
    </c:if>
</div>