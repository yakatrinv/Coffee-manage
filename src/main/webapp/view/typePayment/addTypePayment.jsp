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
        <span class="span">Добавить тип оплаты</span>

        <form class="validate-form" action="coffee-manage">
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>
            <p>
                <label for="name">Введите название:</label>
                <input name="name" id="name" placeholder="название">
            </p>
            <p>
                <label for="usePhoneNumber">Использовать номер телефона:</label>
                <input type="checkbox" name="usePhoneNumber" id="usePhoneNumber">
            </p>
            <p>
                <label for="useCreditCard">использовать банковские карты:</label>
                <input type="checkbox" name="useCreditCard" id="useCreditCard">
            </p>

            <p>
                <button type="submit" name="command" value="saveTypePayment" class="buttonClass" formmethod="post">СОХРАНИТЬ
                </button>
                <a href="${pageContext.request.contextPath}${requestScope.prevURL}" class="buttonClass">ОТМЕНА</a>
            </p>
        </form>

    </div>
</div>
</body>

</html>
