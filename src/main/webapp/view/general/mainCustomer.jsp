<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/general-style.css">

<html>
<head>
    <title>Coffee manage project</title>
</head>
<body>
<jsp:include page="../general/head.jsp"/>
<br>

<h3>Coffee manage project</h3>
<br>
<form action="coffee-manage" class="validate-form">
    <div>
        <div>
            <img width="400" src="${pageContext.request.contextPath}/images/wallpaper.jpg">
        </div>
        <div>
            <button disabled type="submit" name="command" value="scanCode" class="buttonClass" formmethod="post">
                СКАНИРОВАТЬ QR-CODE
            </button>

            <button type="submit" name="command" value="chooseMachine" class="buttonClass" formmethod="post">
                ВЫБРАТЬ ВРУЧНУЮ
            </button>
        </div>
    </div>
</form>

</body>
</html>
