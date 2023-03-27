<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-style.css">

<c:set var="nameCommand" value="models" scope="request"/>
<c:set var="sortParam" value="&sortField=id" scope="request"/>
<c:set var="filterParam" value="&searchBtand=${requestScope.searchBrand}&searchNameModel=${requestScope.searchNameModel}"
       scope="request"/>
<c:set var="pageNumber" value="${requestScope.pageable.pageNumber}" scope="request"/>
<c:set var="pageSize" value="${requestScope.pageable.pageSize}" scope="request"/>
<c:set var="lastPageNumber" value="${requestScope.pageable.lastPageNumber}" scope="request"/>
<c:set var="models" value="${requestScope.pageable.records}" scope="request"/>

<html>
<head>
    <title>Coffee manage project</title>
</head>
<body>
<jsp:include page="../general/head.jsp"/>
<br>
<header>
    <h3 class="h1">Список моделей</h3>
</header>

<form action="coffee-manage">
    <input type="hidden" name="prevURL" value="/coffee-manage?${pageContext.request.queryString}"/>
    <button type="submit" name="command" value="createModel" class="buttonClass">ДОБАВИТЬ МОДЕЛЬ</button>
</form>
<br>

<form action="coffee-manage">
    <label> <input name="searchBrand" placeholder="брэнд" value="${requestScope.searchBrand}"> </label>
    <label> <input name="searchNameModel" placeholder="модель" value="${requestScope.searchNameModel}"> </label>
    <input type="hidden" name="pageNumber" value="${pageNumber}">
    <input type="hidden" name="pageSize" value="${pageSize}">
    <button type="submit" name="command" value="models" formmethod="get" class="buttonClass">ПОИСК</button>
</form>

<c:choose>
    <c:when test="${models eq null}">
        <h2>Список пуст</h2>
    </c:when>

    <c:when test="${models ne null and models.size() eq 0}">
        <h2>Список пуст</h2>
    </c:when>

    <c:otherwise>
        <table class="main-table">
            <tr>
                <th>№</th>
                <th>Брэнд</th>
                <th>Модель</th>
                <th colspan="2">Действие</th>
            </tr>

            <c:choose>
                <c:when test="${pageNumber ne 0}">
                    <c:set var="lastCount" value="${(pageNumber-1)*pageSize}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="lastCount" value="0"/>
                </c:otherwise>
            </c:choose>
            <c:forEach var="model" items="${models}" varStatus="status">
                <tr>
                    <td>${lastCount+status.count}</td>
                    <td>${model.brand}</td>
                    <td>${model.nameModel}</td>
                    <td>
                        <form action="coffee-manage" method="get">
                            <input type="hidden" name="id" value="${model.id}">
                            <input type="hidden" name="prevURL"
                                   value="/coffee-manage?command=${nameCommand}${filterParam}${sortParam}&pageSize=${pageSize}&pageNumber=${pageNumber}"/>
                            <button type="submit" name="command" value="editModel" class="buttonClass">
                                РЕДАКТИРОВАТЬ
                            </button>
                        </form>
                    </td>
                    <td>
                        <form action="coffee-manage">
                            <c:choose>
                                <c:when test="${models.size() eq 1}">
                                    <input type="hidden" name="prevURL"
                                           value="/coffee-manage?command=${nameCommand}${filterParam}${sortParam}&pageSize=${pageSize}&pageNumber=${pageNumber-1}"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="hidden" name="prevURL"
                                           value="/coffee-manage?${pageContext.request.queryString}"/>
                                </c:otherwise>
                            </c:choose>
                            <input type="hidden" name="id" value="${model.id}">
                            <button type="submit" name="command" value="deleteModel" class="buttonClass">УДАЛИТЬ
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </c:otherwise>

</c:choose>

<br>

<jsp:include page="../general/pagination.jsp"/>

</body>
</html>
