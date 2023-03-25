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
        <span class="span">Регистрация</span>

        <form class="validate-form" action="coffee-manage">
            <p>
                <label for="login">Введите логин:</label>
                <input name="login" id="login" placeholder="логин">
            </p>
            <p>
                <label for="password">Введите пароль:</label>
                <input name="password" id="password" placeholder="пароль">
            </p>
            <br>

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
                <input type="tel" name="phone" id="phone" placeholder="телефон">
            </p>
            <p>
                <label for="email">Введите email:</label>
                <input type="email" name="email" id="email" placeholder="email">
            </p>

            <p>
                <button type="submit" name="command" value="registration" class="buttonClass" formmethod="post">РЕГИСТРАЦИЯ</button>
                <button type="submit" name="command" value="getLoginPage" class="buttonClass" formmethod="post">ОТМЕНА</button>
            </p>
        </form>

    </div>
</div>
</body>

</html>
