<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-style.css">

<c:set var="nameCommand" value="chooseMachine" scope="request"/>
<c:set var="sortParam" value="&sortField=id" scope="request"/>
<c:set var="filterParam" value="${requestScope.searchCities}"
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
    <h3 class="h1">Выбор аппарата</h3>
</header>
<br>

<div style="display: flex;" class="div-container">
    <div style="width: 70%;">
        <c:choose>
            <c:when test="${machines eq null}">
                <h2>Список пуст</h2>
            </c:when>

            <c:when test="${requestScope.machine ne null and machines.size() eq 0}">
                <h2>Список пуст</h2>
            </c:when>

            <c:otherwise>
                <table class="main-table">
                    <tr>
                        <th>№</th>
                        <th>Модель</th>
                        <th>Адрес</th>
                        <th>Действие</th>
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
                            <td>
                                <c:choose>
                                    <c:when test="${machine.model ne null}">
                                        ${machine.model.brand}
                                        <br>
                                        ${machine.model.nameModel}
                                    </c:when>
                                    <c:otherwise>
                                        -
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${machine.address ne null}">
                                        ${machine.address.city}
                                        <br>
                                        ${machine.address.street}
                                    </c:when>
                                    <c:otherwise>
                                        -
                                    </c:otherwise>
                                </c:choose>
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
                                    <input type="hidden" name="machine_id" value="${machine.id}">
                                    <button type="submit" name="command" value="chooseProduct" class="buttonClass">
                                        ВЫБРАТЬ
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>

                </table>
            </c:otherwise>

        </c:choose>
    </div>

    <div>
        <form action="coffee-manage">
            <div style="display: grid;" class="select">
                <label for="searchCities">Города</label>
                <br>
                <c:forEach var="city" items="${requestScope.cities}">
                    <label>
                        <c:choose>
                            <c:when test="${requestScope.setSearchCities.contains(city)}">
                                <input checked type="checkbox" name="searchCities" id="searchCities" value="${city}"/>
                                ${city}
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="searchCities" id="searchCities" value="${city}"/>
                                ${city}
                            </c:otherwise>
                        </c:choose>
                    </label>
                </c:forEach>
            </div>

            <br>
            <input type="hidden" name="pageNumber" value="${pageNumber}">
            <input type="hidden" name="pageSize" value="${pageSize}">
            <button type="submit" name="command" value="chooseMachine" formmethod="get" class="buttonClass">ПОИСК
            </button>
        </form>
    </div>

</div>


<br>

<jsp:include page="../general/pagination.jsp"/>

</body>
</html>
