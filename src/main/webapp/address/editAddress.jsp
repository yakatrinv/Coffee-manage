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
        <span class="span">Изменить адрес</span>

        <form class="validate-form" action="coffee-manage">
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>
            <p>
                <input type="hidden" name="id" value="${requestScope.address.id}">
                <label for="city">Введите город:</label>
                <input name="city" id="city" placeholder="city" value="${requestScope.address.city}">
            </p>
            <p>
                <label for="street">Введите улицу:</label>
                <input name="street" id="street" placeholder="street" value="${requestScope.address.street}">
            </p>

            <p>
                <button type="submit" name="command" value="updateAddress" class="buttonClass" formmethod="post">
                    ОБНОВИТЬ
                </button>
                <a href="${pageContext.request.contextPath}${requestScope.prevURL}" class="buttonClass">ОТМЕНА</a>
            </p>
        </form>

    </div>
</div>
</body>
</html>
