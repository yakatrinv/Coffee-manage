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
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>
            <p>
                <label for="login">Введите логин:</label>
                <input name="login" id="login" placeholder="логин">
            </p>
            <p>
                <label for="password">Введите пароль:</label>
                <input type="password" name="password" id="password" placeholder="пароль">
            </p>
            <br>

            <p>
            <div class="select">
                <label for="roles">Роли</label>
                <select id="roles" name="roles" multiple>
                    <option value="Administrator">Administrator</option>
                    <option value="Customer">Customer</option>
                    <option value="Manager">Manager</option>
                </select>
            </div>
            </p>
            <p>
                <button type="submit" name="command" value="saveUser" class="buttonClass" formmethod="post">
                    ДОБАВИТЬ
                </button>
                <button type="submit" name="command" value="users" class="buttonClass" formmethod="post">
                    ОТМЕНА
                </button>
            </p>
        </form>

    </div>
</div>
</body>

</html>
