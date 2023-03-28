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
        <span class="span">Добавить покупку</span>

        <form class="validate-form" action="coffee-manage">
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>

            <p>
                <select name="customer_id">
                    <option value="">не выбрано</option>
                    <c:forEach var="customer" items="${requestScope.customers}">
                        <option value="${customer.id}">
                                ${customer.name}, ${customer.surname}
                        </option>
                    </c:forEach>
                </select>
            </p>

            <p>
                <select name="machine_id">
                    <option value="">не выбрано</option>
                    <c:forEach var="machine" items="${requestScope.machines}">
                        <option value="${machine.id}">
                            <c:if test="${machine ne null}">
                                ${machine.address}
                            </c:if>
                        </option>
                    </c:forEach>
                </select>
            </p>

            <p>
                <select name="product_id">
                    <option value="">не выбрано</option>
                    <c:forEach var="product" items="${requestScope.products}">
                        <option value="${product.id}">
                            <c:if test="${product ne null}">
                                ${product.name}, ${product.price}
                            </c:if>
                        </option>
                    </c:forEach>
                </select>
            </p>

            <p>
                <label for="price">Цена:</label>
                <input type="number" step="0.1" name="price" id="price" placeholder="цена">
            </p>

            <p>
                <select name="discount_id">
                    <option value="">не выбрано</option>
                    <c:forEach var="discount" items="${requestScope.discounts}">
                        <option value="${discount.id}">
                                ${discount.percent}, ${discount.sum}
                        </option>
                    </c:forEach>
                </select>
            </p>

            <p>
                <label for="sum">Сумма:</label>
                <input type="number" step="0.1" name="sum" id="sum" placeholder="сумма">
            </p>

            <p>
                <button type="submit" name="command" value="savePurchase" class="buttonClass" formmethod="post">
                    СОХРАНИТЬ
                </button>
                <a href="${pageContext.request.contextPath}${requestScope.prevURL}" class="buttonClass">ОТМЕНА</a>
            </p>
        </form>

    </div>
</div>
</body>

</html>
