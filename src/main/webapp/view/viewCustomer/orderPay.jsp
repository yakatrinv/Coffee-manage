<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/add-form-style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/radio-style.css">

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
            <input type="hidden" name="discount_id" value="${requestScope.discount.id}" scope="request"/>
            <input type="hidden" name="price" value="${requestScope.product.price}" scope="request"/>
            <input type="hidden" name="sum" value="${requestScope.sum}" scope="request"/>

            <c:if test="${sessionScope.loggedCustomer ne null}">
                <p>
                        Покупатель: ${sessionScope.loggedCustomer.name}, ${sessionScope.loggedCustomer.surname}
                </p>
            </c:if>
            <br>
            <c:if test="${requestScope.machine ne null}">
                Аппарат:
                <p>
                        ${requestScope.machine.model.brand}, ${requestScope.machine.model.nameModel}
                </p>

                <p>
                        ${requestScope.machine.address.city}, ${requestScope.machine.address.street}
                </p>
            </c:if>

            <c:if test="${requestScope.product ne null}">
                <p>
                        Продукция: ${requestScope.product.name}
                </p>
                <p>
                        Цена: ${requestScope.product.price}
                </p>
            </c:if>

            <div>
                <script>
                    function SelectedCreditCard(creditCard, phoneNumber) {
                        document.getElementById("creditCard").style.display = 'none';
                        document.getElementById("phoneNumber").style.display = 'none';


                        if (creditCard.valueOf()=='true') {
                            alert(creditCard.valueOf());
                            document.getElementById("creditCard").style.display = 'block';
                        } else {
                            document.getElementById("creditCard").style.display = 'none';
                        }

                        if (phoneNumber.valueOf()=='true') {
                            document.getElementById("phoneNumber").style.display = 'block';
                        } else {
                            document.getElementById("phoneNumber").style.display = 'none';
                        }
                    }
                </script>

                <div>
                    <label>Типы оплаты</label>
                    <c:forEach var="typePayment" items="${requestScope.typePayments}">
                        <div class="form_radio_btn">
                            <input id="typePayment${typePayment.id}" type="radio" name="type_payment_id"
                                   value="${typePayment.id}"
                                   onChange="SelectedCreditCard('${typePayment.useCreditCard}','${typePayment.usePhoneNumber}')">
                            <label for="typePayment${typePayment.id}">${typePayment.name}</label>
                        </div>
                    </c:forEach>
                </div>

            </div>

            <div id='creditCard' style='display: none;'>
                <label for="creditCard">Карта</label>
                <select name="credit_card_id">
                    <option value="">не выбрано</option>
                    <c:forEach var="creditCardItem" items="${requestScope.creditCards}">
                    <option value="${creditCardItem.id}">${creditCardItem.number}</option>
                    </c:forEach>
                </select>
            </div>

            <div id='phoneNumber' style='display: none;'>
                <label for="phoneNumber">Номер телефона: ${sessionScope.loggedCustomer.phone}</label>
            </div>

            <c:set var="discount" value="${requestScope.discount}"/>
            <p>
                Скидка: ${requestScope.discount.percent} %
            </p>

            <p><b>
                Сумма к оплате: ${requestScope.sum}
            </b></p>
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
