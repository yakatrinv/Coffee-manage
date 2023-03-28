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
        <span class="span">Добавить продукцию</span>

        <form class="validate-form" action="coffee-manage">
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>
            <input type="hidden" name="machine_id" value="${requestScope.machine_id}"/>

            <p>
                <select name="product_id">
                    <option value="">не выбрано</option>
                    <c:forEach var="product" items="${requestScope.products}">
                        <option value="${product.id}">
                                ${product.name}, ${product.price}
                        </option>
                    </c:forEach>
                </select>
            </p>

            <p>
                <button type="submit" name="command" value="saveMachineProduct" class="buttonClass" formmethod="post">
                    СОХРАНИТЬ
                </button>
                <a href="${pageContext.request.contextPath}${requestScope.prevURL}" class="buttonClass">ОТМЕНА</a>
            </p>
        </form>

    </div>
</div>
</body>

</html>
