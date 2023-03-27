<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-style.css">

<c:set var="nameCommand" value="machines" scope="request"/>
<c:set var="sortParam" value="&sortField=id" scope="request"/>
<c:set var="filterParam" value="&searchSerialNumber=${requestScope.searchSerialNumber}"
       scope="request"/>
<c:set var="pageNumber" value="${requestScope.pageable.pageNumber}" scope="request"/>
<c:set var="pageSize" value="${requestScope.pageable.pageSize}" scope="request"/>
<c:set var="lastPageNumber" value="${requestScope.pageable.lastPageNumber}" scope="request"/>
<c:set var="machines" value="${requestScope.pageable.records}" scope="request"/>

<html>
<head>
    <title>Coffee manage project</title>
</head>
<body>
<jsp:include page="../general/head.jsp"/>
<br>
<header>
    <h3 class="h1">Список аппаратов</h3>
</header>

<form action="coffee-manage">
    <input type="hidden" name="prevURL" value="/coffee-manage?${pageContext.request.queryString}"/>
    <button type="submit" name="command" value="createMachine" class="buttonClass">ДОБАВИТЬ АППАРАТ</button>
</form>
<br>

<form action="coffee-manage">
    <label> <input name="searchSerialNumber" placeholder="серийный номер" value="${requestScope.searchSerialNumber}"> </label>
    <input type="hidden" name="pageNumber" value="${pageNumber}">
    <input type="hidden" name="pageSize" value="${pageSize}">
    <button type="submit" name="command" value="machines" formmethod="get" class="buttonClass">ПОИСК</button>
</form>

<c:choose>
    <c:when test="${machines eq null}">
        <h2>Список пуст</h2>
    </c:when>

    <c:when test="${machine ne null and machines.size() eq 0}">
        <h2>Список пуст</h2>
    </c:when>

    <c:otherwise>
        <table class="main-table">
            <tr>
                <th>№</th>
                <th>Серийный номер</th>
                <th>Модель</th>
                <th>Адрес</th>
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
            <c:forEach var="machine" items="${machines}" varStatus="status">
                <tr>
                    <td>${lastCount+status.count}</td>
                    <td>${machine.serialNumber}</td>
                    <td>
                        <c:choose>
                            <c:when test="${machine.address.city ne null and not empty machine.address.city}">
                                ${machine.address.city},
                            </c:when>
                            <c:otherwise>
                                -,
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${machine.address.street ne null and not empty machine.address.street}">
                                ${machine.address.street}
                            </c:when>
                            <c:otherwise>
                                -
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${machine.model.nameModel ne null and not empty machine.model.nameModel}">
                                ${machine.model.nameModel}
                            </c:when>
                            <c:otherwise>
                                -
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${machine.model.brand ne null and not empty machine.model.brand}">
                                ${machine.model.brand},
                            </c:when>
                            <c:otherwise>
                                -,
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <form action="coffee-manage" method="get">
                            <input type="hidden" name="id" value="${machine.id}">
                            <input type="hidden" name="prevURL"
                                   value="/coffee-manage?command=${nameCommand}${filterParam}${sortParam}&pageSize=${pageSize}&pageNumber=${pageNumber}"/>
                            <button type="submit" name="command" value="editMachine" class="buttonClass">
                                РЕДАКТИРОВАТЬ
                            </button>
                        </form>
                    </td>
                    <td>
                        <form action="coffee-manage">
                            <c:choose>
                                <c:when test="${machines.size() eq 1}">
                                    <input type="hidden" name="prevURL"
                                           value="/coffee-manage?command=${nameCommand}${filterParam}${sortParam}&pageSize=${pageSize}&pageNumber=${pageNumber-1}"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="hidden" name="prevURL"
                                           value="/coffee-manage?${pageContext.request.queryString}"/>
                                </c:otherwise>
                            </c:choose>
                            <input type="hidden" name="id" value="${machine.id}">
                            <button type="submit" name="command" value="deleteMachine" class="buttonClass">
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
