<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-style.css">

<c:set var="nameCommand" value="purchases" scope="request"/>
<c:set var="sortParam" value="&sortField=id" scope="request"/>
<c:set var="filterParam" value="&searchPrice=${requestScope.searchPrice}&searchSum=${requestScope.searchSum}"
       scope="request"/>
<c:set var="pageNumber" value="${requestScope.pageable.pageNumber}" scope="request"/>
<c:set var="pageSize" value="${requestScope.pageable.pageSize}" scope="request"/>
<c:set var="lastPageNumber" value="${requestScope.pageable.lastPageNumber}" scope="request"/>
<c:set var="purchases" value="${requestScope.pageable.records}" scope="request"/>

<html>
<head>
    <title>Coffee manage project</title>
</head>
<body>
<jsp:include page="../general/head.jsp"/>
<br>
<header>
    <h3 class="h1">Список покупок</h3>
</header>

<form action="coffee-manage">
    <input type="hidden" name="prevURL" value="/coffee-manage?${pageContext.request.queryString}"/>
    <button type="submit" name="command" value="createPurchase" class="buttonClass">ДОБАВИТЬ ПОКУПКУ</button>
</form>
<br>

<form action="coffee-manage">
    <label><input type="number" step="0.1" name="searchPrice" placeholder="цена"
                  value="${requestScope.searchPrice}"></label>
    <label><input type="number" step="0.1" name="searchSum" placeholder="сумма"
                  value="${requestScope.searchSum}"></label>
    <input type="hidden" name="pageNumber" value="${pageNumber}">
    <input type="hidden" name="pageSize" value="${pageSize}">
    <button type="submit" name="command" value="purchases" formmethod="get" class="buttonClass">ПОИСК</button>
</form>

<c:choose>
    <c:when test="${purchases eq null}">
        <h2>Список пуст</h2>
    </c:when>

    <c:when test="${purchases ne null and purchases.size() eq 0}">
        <h2>Список пуст</h2>
    </c:when>

    <c:otherwise>
        <table class="main-table">
            <tr>
                <th>№</th>
                <th>Покупатель</th>
                <th>Аппарат</th>
                <th>Продукция</th>
                <th>Цена</th>
                <th>Скидка</th>
                <th>Сумма</th>
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
            <c:forEach var="purchase" items="${purchases}" varStatus="status">
                <tr>
                    <td>${lastCount+status.count}</td>
                    <td>
                        <c:choose>
                        <c:when test="${purchase.customer.name ne null and not empty purchase.customer.name}">
                            ${purchase.customer.name},
                        </c:when>
                        <c:otherwise>
                            -,
                        </c:otherwise>
                        </c:choose>
                        <c:choose>
                        <c:when test="${purchase.customer.surname ne null and not empty purchase.customer.surname}">
                            ${purchase.customer.surname},
                        </c:when>
                        <c:otherwise>
                            -,
                        </c:otherwise>
                        </c:choose>
                    <td>
                    <c:choose>
                        <c:when test="${purchase.machine ne null and not empty purchase.machine}">
                            ${purchase.machine}
                        </c:when>
                        <c:otherwise>
                            -
                        </c:otherwise>
                    </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${purchase.product ne null and not empty purchase.product}">
                                ${purchase.product.name}, ${purchase.product.price}
                            </c:when>
                            <c:otherwise>
                                -
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${purchase.price}</td>
                    <td>
                        <c:choose>
                        <c:when test="${purchase.discount ne null and not empty purchase.discount}">
                            ${purchase.discount}
                        </c:when>
                        <c:otherwise>
                            -
                        </c:otherwise>
                    </c:choose>
                    <td>${purchase.sum}</td>
                    <td>
                        <form action="coffee-manage" method="get">
                            <input type="hidden" name="id" value="${purchase.id}">
                            <input type="hidden" name="prevURL"
                                   value="/coffee-manage?command=${nameCommand}${filterParam}${sortParam}&pageSize=${pageSize}&pageNumber=${pageNumber}"/>
                            <button type="submit" name="command" value="editPurchase" class="buttonClass">
                                РЕДАКТИРОВАТЬ
                            </button>
                        </form>
                    </td>
                    <td>
                        <form action="coffee-manage">
                            <c:choose>
                                <c:when test="${purchases.size() eq 1}">
                                    <input type="hidden" name="prevURL"
                                           value="/coffee-manage?command=${nameCommand}${filterParam}${sortParam}&pageSize=${pageSize}&pageNumber=${pageNumber-1}"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="hidden" name="prevURL"
                                           value="/coffee-manage?${pageContext.request.queryString}"/>
                                </c:otherwise>
                            </c:choose>
                            <input type="hidden" name="id" value="${purchase.id}">
                            <button type="submit" name="command" value="deletePurchase" class="buttonClass">
                                УДАЛИТЬ
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
