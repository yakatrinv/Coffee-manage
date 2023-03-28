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
        <span class="span">Добавить аппарат</span>

        <form class="validate-form" action="coffee-manage">
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>

            <p>
                <label for="serialNumber">Введите серийный номер:</label>
                <input name="serialNumber" id="serialNumber" placeholder="серийный номер">
            </p>

            <p>
                <select name="address_id">
                    <option value="">не выбрано</option>
                    <c:forEach var="address" items="${requestScope.addresses}">
                        <option value="${address.id}">
                                ${address.city}, ${address.street}
                        </option>
                    </c:forEach>
                </select>
            </p>

            <p>
                <select name="model_id">
                    <option value="">не выбрано</option>
                    <c:forEach var="model" items="${requestScope.models}">
                        <option value="${model.id}">
                                ${model.brand}, ${model.nameModel}
                        </option>
                    </c:forEach>
                </select>
            </p>

            <p>
                <button type="submit" name="command" value="saveMachine" class="buttonClass" formmethod="post">
                    СОХРАНИТЬ
                </button>
                <a href="${pageContext.request.contextPath}${requestScope.prevURL}" class="buttonClass">ОТМЕНА</a>
            </p>
        </form>

    </div>
</div>
</body>

</html>
