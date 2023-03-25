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
        <span class="span">Вход в систему</span>

        <form class="validate-form" action="coffee-manage">
            <p>
                <label for="login">Введите логин:</label>
                <input name="login" id="login" placeholder="логин">
            </p>
            <p>
                <label for="password">Введите пароль:</label>
                <input type="password" name="password" id="password" placeholder="пароль">
            </p>

            <p>
                <button type="submit" name="command" value="login" class="buttonClass" formmethod="post">ВОЙТИ</button>
            </p>

            <p>
                <label>Нет аккаунта?</label>
                <button type="submit" name="command" value="getRegPage" class="buttonClass" formmethod="post">РЕГИСТРАЦИЯ</button>
            </p>
        </form>

    </div>
</div>
</body>

</html>
