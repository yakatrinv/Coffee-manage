<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-style.css">

<c:set var="nameCommand" value="chooseProduct" scope="request"/>
<c:set var="sortParam" value="&sortField=${requestScope.sortField}" scope="request"/>
<c:set var="filterParam" value="" scope="request"/>
<c:set var="pageNumber" value="${requestScope.pageable.pageNumber}" scope="request"/>
<c:set var="pageSize" value="${requestScope.pageable.pageSize}" scope="request"/>
<c:set var="lastPageNumber" value="${requestScope.pageable.lastPageNumber}" scope="request"/>
<c:set var="machineProducts" value="${requestScope.pageable.records}" scope="request"/>

<html>
<head>
    <title>Coffee manage project</title>
</head>
<body>
<jsp:include page="../general/head.jsp"/>
<br>
<header>
    <h3 class="h1">Список продукции по аппарату</h3>
</header>

<form action="coffee-manage">
    <input type="hidden" name="prevURL" value="${pageContext.request.queryString}"/>
    <input type="hidden" name="pageNumber" value="${pageNumber}">
    <input type="hidden" name="pageSize" value="${pageSize}">
    <input type="hidden" name="machine_id" value="${requestScope.machine.id}">

    <c:if test="${machine ne null}">
        <p>
                ${machine.model.brand}, ${machine.model.nameModel}
        </p>

        <p>
                ${machine.address.city}, ${machine.address.street}
        </p>
    </c:if>

    <label for="sortField">Сортровать по: </label>
    <select id="sortField" name="sortField" onchange="document.location=this.options[this.selectedIndex].value">
        <option value="coffee-manage?command=${nameCommand}&sortField=id${filterParam}&pageSize=${pageSize}&pageNumber=${pageNumber}&machine_id=${requestScope.machine.id}">не выбрано</option>
        <c:choose>
            <c:when test="${requestScope.sortField eq 'price'}">
                <option
                        value="coffee-manage?command=${nameCommand}&sortField=name${filterParam}&pageSize=${pageSize}&pageNumber=${pageNumber}&machine_id=${requestScope.machine.id}">
                    по имени
                </option>
                <option selected
                        value="coffee-manage?command=${nameCommand}&sortField=price${filterParam}&pageSize=${pageSize}&pageNumber=${pageNumber}&machine_id=${requestScope.machine.id}">
                    по цене
                </option>
            </c:when>
            <c:when test="${requestScope.sortField eq 'name'}">
                <option selected
                        value="coffee-manage?command=${nameCommand}&sortField=name${filterParam}&pageSize=${pageSize}&pageNumber=${pageNumber}&machine_id=${requestScope.machine.id}">
                    по имени
                </option>
                <option value="coffee-manage?command=${nameCommand}&sortField=price${filterParam}&pageSize=${pageSize}&pageNumber=${pageNumber}&machine_id=${requestScope.machine.id}">
                    по цене
                </option>
            </c:when>
            <c:otherwise>
                <option
                        value="coffee-manage?command=${nameCommand}&sortField=name${filterParam}&pageSize=${pageSize}&pageNumber=${pageNumber}&machine_id=${requestScope.machine.id}">
                    по имени
                </option>
                <option value="coffee-manage?command=${nameCommand}&sortField=price${filterParam}&pageSize=${pageSize}&pageNumber=${pageNumber}&machine_id=${requestScope.machine.id}">
                    по цене
                </option>
            </c:otherwise>
        </c:choose>
    </select>

    <c:choose>
        <c:when test="${machineProducts eq null}">
            <h2>Список пуст</h2>
        </c:when>

        <c:when test="${machineProducts ne null and machineProducts.size() eq 0}">
            <h2>Список пуст</h2>
        </c:when>

        <c:otherwise>
            <table class="main-table">
                <tr>
                    <th>№</th>
                    <th>Продукция</th>
                    <th>Цена</th>
                    <th>Действие</th>
                </tr>

                <c:choose>
                    <c:when test="${pageNumber gt 0}">
                        <c:set var="lastCount" value="${(pageNumber-1)*pageSize}"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="lastCount" value="0"/>
                    </c:otherwise>
                </c:choose>
                <c:forEach var="machineProduct" items="${machineProducts}" varStatus="status">
                    <tr>
                        <td>${lastCount+status.count}</td>
                        <td>${machineProduct.name}</td>
                        <td>${machineProduct.price}</td>
                        <td>
                            <form action="coffee-manage">
                                <c:choose>
                                    <c:when test="${machineProducts.size() eq 1}">
                                        <input type="hidden" name="prevURL"
                                               value="/coffee-manage?command=${nameCommand}${filterParam}${sortParam}&pageSize=${pageSize}&pageNumber=${pageNumber-1}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" name="prevURL"
                                               value="/coffee-manage?${pageContext.request.queryString}"/>
                                    </c:otherwise>
                                </c:choose>
                                <input type="hidden" name="machine_id" value="${requestScope.machine.id}">
                                <input type="hidden" name="product_id" value="${machineProduct.id}">
                                <button type="submit" name="command" value="orderPay" class="buttonClass">
                                    ВЫБРАТЬ
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </c:otherwise>

    </c:choose>
</form>
<br>

<jsp:include page="../general/pagination.jsp"/>

</body>
</html>
