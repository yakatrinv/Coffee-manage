<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/add-form-style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-style.css">

<html>
<head>
    <title>Coffee manage project</title>
</head>
<body>

<div class="container">
    <div class="div">
        <span class="span">Профиль</span>

        <form class="validate-form" action="coffee-manage">
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>
            <input type="hidden" name="customer_id" value="${requestScope.customer.id}">
            <p>
                <label for="name">Введите имя:</label>
                <input name="name" id="name" placeholder="имя" value="${requestScope.customer.name}">
            </p>
            <p>
                <label for="surname">Введите фамилию:</label>
                <input name="surname" id="surname" placeholder="фамилия" value="${requestScope.customer.surname}">
            </p>

            <p>
                <label for="phone">Введите телефон:</label>
                <input type="tel" name="phone" id="phone" placeholder="телефон" value="${requestScope.customer.phone}">
            </p>

            <p>
                <label for="email">Введите эл. почту:</label>
                <input type="email" name="email" id="email" placeholder="эл. почта"
                       value="${requestScope.customer.email}">
            </p>

            <p>
                <button type="submit" name="command" value="updateCustomer" class="buttonClass" formmethod="post">
                    ОБНОВИТЬ
                </button>
                <a href="${pageContext.request.contextPath}${requestScope.prevURL}" class="buttonClass">ОТМЕНА</a>
            </p>

        </form>
        <label>Банковские карты</label>

        <form class="validate-form" action="coffee-manage">
            <button type="submit" name="command" value="createCreditCard" class="buttonClass" formmethod="post">
                ДОБАВИТЬ КАРТУ
            </button>
        </form>

        <table class="main-table">
            <tr>
                <th>№</th>
                <th>Номер</th>
                <th colspan="2">Действие</th>
            </tr>

            <c:forEach var="creditCard" items="${requestScope.creditCards}" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${creditCard.number}</td>
                    <td>
                        <form class="validate-form" action="coffee-manage">
                            <input type="hidden" name="credit_card_id" value="${creditCard.id}">
                            <input type="hidden" name="number" value="${creditCard.number}">
                            <input type="hidden" name="prevURL"
                                   value="/coffee-manage?${pageContext.request.queryString}"/>
                            <button type="submit" name="command" value="editCreditCard" class="buttonClass">
                                РЕДАКТИРОВАТЬ
                            </button>
                        </form>
                    </td>
                    <td>
                        <form class="validate-form" action="coffee-manage">
                            <input type="hidden" name="prevURL"
                                   value="/coffee-manage?${pageContext.request.queryString}"/>
                            <input type="hidden" name="credit_card_id" value="${creditCard.id}">
                            <button type="submit" name="command" value="deleteCreditCard" class="buttonClass">
                                УДАЛИТЬ
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>

        </table>

    </div>
</div>
</body>

</html>
