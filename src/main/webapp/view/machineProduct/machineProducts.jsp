<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-style.css">

<c:set var="nameCommand" value="machineProducts" scope="request"/>
<c:set var="sortParam" value="&sortField=id" scope="request"/>
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
<%--    <input type="hidden" name="prevURL" value="/coffee-manage?${pageContext.request.queryString}"/>--%>
    <input type="hidden" name="pageNumber" value="${pageNumber}">
    <input type="hidden" name="pageSize" value="${pageSize}">

    <p>
        <select name="machine_id">
            <option value="">не выбрано</option>
            <c:forEach var="machineItem" items="${requestScope.machines}">
                <c:if test="${requestScope.machine ne null and machineItem.id eq requestScope.machine.id}">
                    <option selected value="${machineItem.id}">
                            ${machineItem.serialNumber},${machineItem.model.brand}, ${machineItem.model.nameModel},
                            ${machineItem.address.city}, ${machineItem.address.street}
                    </option>
                </c:if>
                <c:if test="${requestScope.machine eq null or machineItem.id ne requestScope.machine.id}">
                    <option value="${machineItem.id}">
                            ${machineItem.serialNumber},${machineItem.model.brand}, ${machineItem.model.nameModel},
                            ${machineItem.address.city}, ${machineItem.address.street}
                    </option>
                </c:if>
            </c:forEach>
        </select>

        <button type="submit" name="command" value="machineProducts" formmethod="get" class="buttonClass">ОБНОВИТЬ
            СПИСОК
        </button>
    </p>

    <c:if test="${machine ne null}">
        <button type="submit" name="command" value="createMachineProduct" class="buttonClass">
            ДОБАВИТЬ ПРОДУКЦИЮ
        </button>
    </c:if>
    <br>

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
                                <button type="submit" name="command" value="deleteMachineProduct" class="buttonClass">
                                    УДАЛИТЬ
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
