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
        <span class="span">Добавить покупателя</span>

        <form class="validate-form" action="coffee-manage">
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>
            <p>
                <label for="login">Введите логин:</label>
                <input name="login" id="login" placeholder="логин">
            </p>

            <p>
                <label for="password">Введите пароль:</label>
                <input type="password" name="password" id="password" placeholder="пароль">
            </p>

            <p>
                <label for="name">Введите имя:</label>
                <input name="name" id="name" placeholder="имя">
            </p>

            <p>
                <label for="surname">Введите фамилию:</label>
                <input name="surname" id="surname" placeholder="фамилия">
            </p>

            <p>
                <label for="phone">Введите телефон:</label>
                <input name="phone" id="phone" placeholder="телефон">
            </p>

            <p>
                <label for="email">Введите эл. почту:</label>
                <input name="email" id="email" placeholder="эл. почта">
            </p>

            <p>
                <button type="submit" name="command" value="saveCustomer" class="buttonClass" formmethod="post">
                    СОХРАНИТЬ
                </button>
                <a href="${pageContext.request.contextPath}${requestScope.prevURL}" class="buttonClass">ОТМЕНА</a>
            </p>
        </form>

    </div>
</div>
</body>

</html>
