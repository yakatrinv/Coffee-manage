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
        <span class="span">Изменить скидку</span>

        <form class="validate-form" action="coffee-manage">
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>
            <input type="hidden" name="id" value="${requestScope.discount.id}">
            <p>
                <label for="sum">Введите сумму:</label>
                <input type="number" name="sum" id="sum" placeholder="сумма" value="${requestScope.discount.sum}">
            </p>
            <p>
                <label for="percent">Введите процент:</label>
                <input type="number" name="percent" id="percent" placeholder="процент" value="${requestScope.discount.percent}">
            </p>

            <p>
                <button type="submit" name="command" value="updateDiscount" class="buttonClass" formmethod="post">
                    ОБНОВИТЬ
                </button>
                <a href="${pageContext.request.contextPath}${requestScope.prevURL}" class="buttonClass">ОТМЕНА</a>
            </p>
        </form>

    </div>
</div>
</body>
</html>
