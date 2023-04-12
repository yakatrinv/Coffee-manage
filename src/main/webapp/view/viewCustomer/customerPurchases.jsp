<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-style.css">

<c:set var="nameCommand" value="customerPurchases" scope="request"/>
<c:set var="sortParam" value="&sortField=${requestScope.sortField}" scope="request"/>
<c:set var="filterParam"
       value="&searchStartDate=${requestScope.searchStartDate}&searchFinishDate=${requestScope.searchFinishDate}
&searchMachine=${requestScope.searchMachine.id}&searchTypePayment=${requestScope.searchTypePayment.id}
&searchCustomer=${sessionScope.loggedCustomer.id}
&searchMinSum=${requestScope.searchMinSum}&searchMaxSum=${requestScope.searchMaxSum}"
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
    <h3 class="h1">История покупок</h3>
</header>

<form action="coffee-manage">
    <label for="sortField">Сортировать по: </label>
    <select id="sortField" name="sortField">
        <option value="id">не выбрано</option>
        <c:choose>
            <c:when test="${requestScope.sortField eq 'createDate'}">
                <option value="createDate" selected>
                    по дате
                </option>
            </c:when>
            <c:otherwise>
                <option value="createDate">
                    по дате
                </option>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${requestScope.sortField eq 'machine'}">
                <option value="machine" selected>
                    по аппарату
                </option>
            </c:when>
            <c:otherwise>
                <option value="machine">
                    по аппарату
                </option>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${requestScope.sortField eq 'product'}">
                <option value="product" selected>
                    по продукции
                </option>
            </c:when>
            <c:otherwise>
                <option value="product">
                    по продукции
                </option>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${requestScope.sortField eq 'typePayment'}">
                <option value="typePayment" selected>
                    по типу оплаты
                </option>
            </c:when>
            <c:otherwise>
                <option value="typePayment">
                    по типу оплаты
                </option>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${requestScope.sortField eq 'sum'}">
                <option value="sum" selected>
                    по сумме
                </option>
            </c:when>
            <c:otherwise>
                <option value="sum">
                    по сумме
                </option>
            </c:otherwise>
        </c:choose>
    </select>

    <label>
        Начало периода:
        <input type="date" name="searchStartDate" value="${requestScope.searchStartDate}">
    </label>
    <label>
        Конец периода:
        <input type="date" name="searchFinishDate" value="${requestScope.searchFinishDate}">
    </label>

    <p>
        </label>Аппарат
        <select name="searchMachine">
            <option value="">не выбрано</option>
            <c:forEach var="machine" items="${requestScope.machines}">
                <c:choose>
                    <c:when test="${machine.id eq searchMachine.id}">
                        <option value="${machine.id}" selected>
                                ${machine.model.brand} ${machine.model.nameModel}
                            (${machine.address.city} ${machine.address.street})
                        </option>
                    </c:when>
                    <c:otherwise>
                        <option value="${machine.id}">
                                ${machine.model.brand} ${machine.model.nameModel}
                            (${machine.address.city} ${machine.address.street})
                        </option>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
        </select>
        </label>

        </label>Тип оплаты
        <select name="searchTypePayment">
            <option value="">не выбрано</option>
            <c:forEach var="typePayment" items="${requestScope.typePayments}">
                <c:choose>
                    <c:when test="${typePayment.id eq searchTypePayment.id}">
                        <option value="${typePayment.id}" selected>
                                ${typePayment.name}
                        </option>
                    </c:when>
                    <c:otherwise>
                        <option value="${typePayment.id}">
                                ${typePayment.name}
                        </option>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
        </select>
        </label>
    </p>

    <p>
        <label>
            Минимальная сумма:
            <input type="number" step="0.01" name="searchMinSum" value="${requestScope.searchMinSum}">
        </label>
        <label>
            Максимальная сумма:
            <input type="number" step="0.01" name="searchMaxSum" value="${requestScope.searchMaxSum}">
        </label>
    </p>

    <input type="hidden" name="pageNumber" value="${pageNumber}">
    <input type="hidden" name="pageSize" value="${pageSize}">
    <input type="hidden" name="searchCustomer" value="${sessionScope.loggedCustomer.id}">
    <button type="submit" name="command" value="customerPurchases" class="buttonClass">ПОИСК И СОРТИРОВКА
    </button>
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
                <th>Дата покупки</th>
                <th colspan="2">Аппарат</th>
                <th>Продукция</th>
                <th>Цена</th>
                <th>Скидка</th>
                <th>Сумма</th>
                <th>Тип оплаты</th>
                <th>Номер карты</th>
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
                            ${purchase.createDate}
                    </td>

                    <td>
                        <c:if test="${purchase.machine ne null}">
                            <c:if test="${purchase.machine.model ne null}">
                                ${purchase.machine.model.brand}
                                <br>
                                ${purchase.machine.model.nameModel}
                            </c:if>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${purchase.machine ne null}">
                            <c:if test="${purchase.machine.address ne null}">
                                ${purchase.machine.address.city}
                                <br>
                                ${purchase.machine.address.street}
                            </c:if>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${purchase.product ne null}">
                            ${purchase.product.name}
                        </c:if>
                    </td>
                    <td>${purchase.price}</td>
                    <td>
                        <c:if test="${purchase.discount ne null}">
                            ${purchase.discount.percent}%
                        </c:if>
                    <td>${purchase.sum}</td>
                    <td>${purchase.typePayment.name}</td>
                    <td>
                        <c:if test="${purchase.creditCard ne null and not empty purchase.creditCard.number}">
                            ${purchase.creditCard.number.charAt(0)}
                            ***
                            ${purchase.creditCard.number.charAt(purchase.creditCard.number.length()-1)}
                        </c:if>

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
