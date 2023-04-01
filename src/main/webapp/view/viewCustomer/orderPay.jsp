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
        <span class="span">Подтверждение покупки</span>

        <form class="validate-form" action="coffee-manage">
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>

            <input type="hidden" name="prevURL" value="${pageContext.request.queryString}"/>
            <input type="hidden" name="pageNumber" value="${pageNumber}">
            <input type="hidden" name="pageSize" value="${pageSize}">

            <input type="hidden" name="customer_id" value="${sessionScope.loggedCustomer.id}" scope="request"/>
            <input type="hidden" name="machine_id" value="${requestScope.machine.id}" scope="request"/>
            <input type="hidden" name="product_id" value="${requestScope.product.id}" scope="request"/>
            <input type="hidden" name="price" value="${requestScope.product.price}" scope="request"/>
            <input type="hidden" name="sum" value="${requestScope.product.price}" scope="request"/>

            <c:if test="${sessionScope.loggedCustomer ne null}">
                <p>
                        ${sessionScope.loggedCustomer.name}, ${sessionScope.loggedCustomer.surname}
                </p>
            </c:if>
            <br>
            <c:if test="${requestScope.machine ne null}">
                <p>
                        ${requestScope.machine.model.brand}, ${requestScope.machine.model.nameModel}
                </p>

                <p>
                        ${requestScope.machine.address.city}, ${requestScope.machine.address.street}
                </p>
            </c:if>
            <br>

            <c:if test="${requestScope.product ne null}">
                <p>
                        ${requestScope.product.name}, ${requestScope.product.price}
                </p>
            </c:if>
            <br>

            <c:set var="sum" value="${requestScope.product.price}"/>
                <p>
                        ${sum}
                </p>
            <br>

            <p>
                <button type="submit" name="command" value="saveOrderPay" class="buttonClass" formmethod="post">
                    ОПЛАТИТЬ
                </button>

                <button type="submit" name="command" value="updatePurchase" class="buttonClass" formmethod="post">
                    ОТМЕНА
                </button>
            </p>
        </form>

    </div>
</div>
</body>

</html>
