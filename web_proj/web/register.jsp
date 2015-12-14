<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>

<c:if test="${error ne null}">
    <div style="border: 1px solid red">
            ${error}
    </div>
</c:if>

<form action="/register" method="post">
    <input type="input" name="name" placeholder="Введите имя"/>
    <input type="email" name="email" placeholder="Введите мыло"/>
    <input type="password" name="password" placeholder="Введите пароль"/>
    <input type="submit"/>
</form>
</body>
</html>
