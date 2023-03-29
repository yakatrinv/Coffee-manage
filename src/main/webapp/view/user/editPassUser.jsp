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
        <span class="span">Смена пароля</span>

        <form class="validate-form" action="coffee-manage">
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>
            <p>
                <label for="login">Логин: ${user.login}</label>
                <input type="hidden" name="login" id="login" placeholder="логин" value="${user.login}">
            </p>
            <p>
                <label for="oldPassword">Введите старый пароль:</label>
                <input type="password" name="oldPassword" id="oldPassword" placeholder="старый пароль">
            </p>
            <p>
                <label for="password">Введите новый пароль:</label>
                <input type="password" name="password" id="password" placeholder="новый пароль">
            </p>
            <br>
            <p>
                <button type="submit" name="command" value="updatePassUser" class="buttonClass" formmethod="post">ОБНОВИТЬ</button>
                <button type="submit" name="command" value="users" class="buttonClass" formmethod="post">ОТМЕНА</button>
            </p>
        </form>

    </div>
</div>
</body>

</html>
