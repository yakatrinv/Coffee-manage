<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/add-form-style.css">

<html>
<head>
    <title>Coffee manage project</title>
</head>
<body>

<div class="container">
    <div class="div">
        <span class="span">Изменить данные банковской карты</span>

        <form class="validate-form" action="coffee-manage">
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>
            <input type="hidden" name="customer_id" value="${requestScope.customer.id}"/>
            <input type="hidden" name="credit_card_id" value="${requestScope.creditCard.id}"/>

            <p>
                <label for="number">Номер карты:</label>
                <input name="number" id="number" placeholder="номер карты" value="${requestScope.creditCard.number}">
            </p>

            <p>
                <button type="submit" name="command" value="updateCreditCard" class="buttonClass" formmethod="post">
                    ОБНОВИТЬ
                </button>
                <a href="${pageContext.request.contextPath}${requestScope.prevURL}" class="buttonClass">ОТМЕНА</a>
            </p>
        </form>

    </div>
</div>
</body>

</html>
