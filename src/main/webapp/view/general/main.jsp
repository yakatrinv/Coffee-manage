<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Coffee manage project</title>
</head>
<body>
<jsp:include page="../general/head.jsp"/>
<br>

<h3>Coffee manage project</h3>
<br>
<a href="${pageContext.request.contextPath}/coffee-manage?command=users">Пользователи</a>
<br>
<a href="${pageContext.request.contextPath}/coffee-manage?command=roles">Роли</a>
<br>
<a href="${pageContext.request.contextPath}/coffee-manage?command=addresses">Адреса</a>
<br>
<a href="${pageContext.request.contextPath}/coffee-manage?command=customers">Покупатели</a>
<br>
<a href="${pageContext.request.contextPath}/coffee-manage?command=models">Модели</a>
<br>
<a href="${pageContext.request.contextPath}/coffee-manage?command=products">Продукция</a>
</body>
</html>
