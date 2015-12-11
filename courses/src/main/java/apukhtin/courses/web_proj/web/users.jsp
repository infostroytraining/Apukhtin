<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:if test="${msg ne null}">
    <div style="background-color: #b7e5ff; color: #4741e8;">
        <h3> ${msg} </h3>
    </div>
</c:if>

<table border="1">
    <thead>
    <td>ID</td>
    <td>ФИО</td>
    <td>мыло</td>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="u">
        <tr>
            <td>${u.id}</td>
            <td>${u.name}</td>
            <td>${u.email}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
