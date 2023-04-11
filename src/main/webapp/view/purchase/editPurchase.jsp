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
        <span class="span">Скорректировать данные покупки</span>

        <form class="validate-form" action="coffee-manage">
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>
            <input type="hidden" name="id" value="${requestScope.purchase.id}">

            <p>
                <select name="customer_id">
                    <option value="">не выбрано</option>
                    <c:forEach var="customer" items="${requestScope.customers}">
                        <c:choose>
                            <c:when test="${customer.id eq requestScope.purchase.customer.id}">
                                <option selected value="${customer.id}">
                                        ${customer.name}, ${customer.surname}
                                </option>
                            </c:when>
                            <c:otherwise>
                                <option value="${customer.id}">
                                        ${customer.name}, ${customer.surname}
                                </option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </p>

            <p>
                <select name="machine_id">
                    <option value="">не выбрано</option>
                    <c:forEach var="machine" items="${requestScope.machines}">
                        <c:choose>
                            <c:when test="${machine.id eq requestScope.purchase.machine.id}">
                                <option selected value="${machine.id}">
                                    <c:if test="${machine ne null}">
                                        ${machine.address}
                                    </c:if>
                                </option>
                            </c:when>
                            <c:otherwise>
                                <option value="${machine.id}">
                                    <c:if test="${machine ne null}">
                                        ${machine.address}
                                    </c:if>
                                </option>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </select>
            </p>

            <p>
                <select name="product_id">
                    <option value="">не выбрано</option>
                    <c:forEach var="product" items="${requestScope.products}">
                        <c:choose>
                            <c:when test="${product.id eq requestScope.purchase.product.id}">
                                <option selected value="${product.id}">
                                    <c:if test="${product ne null}">
                                        ${product.name}, ${product.price}
                                    </c:if>
                                </option>
                            </c:when>
                            <c:otherwise>
                                <option value="${product.id}">
                                    <c:if test="${product ne null}">
                                        ${product.name}, ${product.price}
                                    </c:if>
                                </option>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </select>
            </p>

            <p>
                <label for="price">Цена:</label>
                <input type="number" step="0.1" name="price" id="price" placeholder="цена"
                       value="${requestScope.purchase.price}">
            </p>

            <p>
                <select name="discount_id">
                    <option value="">не выбрано</option>
                    <c:forEach var="discount" items="${requestScope.discounts}">
                        <c:choose>
                            <c:when test="${discount.id eq requestScope.purchase.discount.id}">
                                <option selected value="${discount.id}">
                                        ${discount.percent}, ${discount.sum}
                                </option>
                            </c:when>
                            <c:otherwise>
                                <option value="${discount.id}">
                                        ${discount.percent}, ${discount.sum}
                                </option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </p>

            <p>
                <label for="sum">Сумма:</label>
                <input type="number" step="0.1" name="sum" id="sum" placeholder="сумма"
                       value="${requestScope.purchase.sum}">
            </p>

            <p>
                <select name="type_payment_id">
                    <option value="">не выбрано</option>
                    <c:forEach var="typePayment" items="${requestScope.typePayments}">
                        <c:choose>
                            <c:when test="${typePayment.id eq requestScope.purchase.typePayment.id}">
                                <option selected value="${typePayment.id}">
                                        ${typePayment.name}
                                </option>
                            </c:when>
                            <c:otherwise>
                                <option value="${typePayment.id}">
                                        ${typePayment.name}
                                </option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </p>

            <div id='creditCard'>
                <label for="creditCard">Карта</label>
                <select name="credit_card_id">
                    <option value="">не выбрано</option>
                    <c:forEach var="creditCardItem" items="${requestScope.creditCards}">
                        <c:choose>
                            <c:when test="${creditCardItem.id eq requestScope.purchase.creditCard.id}">
                                <option selected value="${creditCardItem.id}">
                                        ${creditCardItem.number} (${creditCardItem.customer.name}, ${creditCardItem.customer.surname})
                                </option>
                            </c:when>
                            <c:otherwise>
                                <option value="${creditCardItem.id}">
                                        ${creditCardItem.number} (${creditCardItem.customer.name}, ${creditCardItem.customer.surname})
                                </option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>

            <p>
                <button type="submit" name="command" value="updatePurchase" class="buttonClass" formmethod="post">
                    ОБНОВИТЬ
                </button>
                <a href="${pageContext.request.contextPath}${requestScope.prevURL}" class="buttonClass">ОТМЕНА</a>
            </p>
        </form>

    </div>
</div>
</body>

</html>
