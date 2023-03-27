<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-style.css">

<c:set var="nameCommand" value="users" scope="request"/>
<c:set var="sortParam" value="&sortField=id" scope="request"/>
<c:set var="filterParam" value="&searchLogin=${requestScope.searchLogin}"
       scope="request"/>
<c:set var="pageNumber" value="${requestScope.pageable.pageNumber}" scope="request"/>
<c:set var="pageSize" value="${requestScope.pageable.pageSize}" scope="request"/>
<c:set var="lastPageNumber" value="${requestScope.pageable.lastPageNumber}" scope="request"/>
<c:set var="users" value="${requestScope.pageable.records}" scope="request"/>

<html>
<head>
    <title>Coffee manage project</title>
</head>
<body>
<jsp:include page="../general/head.jsp"/>
<br>
<header>
    <h3 class="h1">Список пользователей</h3>
</header>

<form action="coffee-manage">
    <input type="hidden" name="prevURL" value="/coffee-manage?${pageContext.request.queryString}"/>
    <button type="submit" name="command" value="createUser" class="buttonClass">ДОБАВИТЬ Пользователя</button>
</form>
<br>

<form action="coffee-manage">
    <label> <input name="searchLogin" placeholder="логин" value="${requestScope.searchLogin}"> </label>
    <input type="hidden" name="pageNumber" value="${pageNumber}">
    <input type="hidden" name="pageSize" value="${pageSize}">
    <button type="submit" name="command" value="users" formmethod="get" class="buttonClass">ПОИСК</button>
</form>

<c:choose>
    <c:when test="${users eq null}">
        <h2>Список пуст</h2>
    </c:when>

    <c:when test="${users ne null and users.size() eq 0}">
        <h2>Список пуст</h2>
    </c:when>

    <c:otherwise>
        <table class="main-table">
            <tr>
                <th>№</th>
                <th>Логин</th>
                <th>Роли</th>
                <th colspan="1">Действие</th>
            </tr>

            <c:choose>
                <c:when test="${pageNumber ne 0}">
                    <c:set var="lastCount" value="${(pageNumber-1)*pageSize}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="lastCount" value="0"/>
                </c:otherwise>
            </c:choose>
            <c:forEach var="user" items="${users}" varStatus="status">
                <tr>
                    <td>${lastCount+status.count}</td>
                    <td>${user.login}</td>
                    <td>
                        <c:forEach var="role" items="${user.roles}">
                            ${role.roleName}
                        </c:forEach>

                    </td>
                    <td>
                        <form action="coffee-manage" method="get">
                            <input type="hidden" name="login" value="${user.login}">
                            <input type="hidden" name="prevURL"
                                   value="/coffee-manage?command=${nameCommand}${filterParam}${sortParam}&pageSize=${pageSize}&pageNumber=${pageNumber}"/>
                            <button type="submit" name="command" value="editPassUser" class="buttonClass">
                                СМЕНИТЬ ПАРОЛЬ
                            </button>
                        </form>
                    </td>
<%--                    <td>--%>
<%--                        <form action="coffee-manage">--%>
<%--                            <c:choose>--%>
<%--                                <c:when test="${users.size() eq 1}">--%>
<%--                                    <input type="hidden" name="prevURL"--%>
<%--                                           value="/coffee-manage?command=${nameCommand}${filterParam}${sortParam}&pageSize=${pageSize}&pageNumber=${pageNumber-1}"/>--%>
<%--                                </c:when>--%>
<%--                                <c:otherwise>--%>
<%--                                    <input type="hidden" name="prevURL"--%>
<%--                                           value="/coffee-manage?${pageContext.request.queryString}"/>--%>
<%--                                </c:otherwise>--%>
<%--                            </c:choose>--%>
<%--                            <button type="submit" name="command" value="deleteUser" class="buttonClass">УДАЛИТЬ</button>--%>
<%--                        </form>--%>
<%--                    </td>--%>
                </tr>
            </c:forEach>

        </table>
    </c:otherwise>

</c:choose>

<br>

<jsp:include page="../general/pagination.jsp"/>

</body>
</html>
