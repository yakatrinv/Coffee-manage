<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>Стараница ошибки</h3>

${message}

<a href="${pageContext.request.contextPath}/coffee-manage?command=getLoginPage">Home page</a>
</body>
</html>
