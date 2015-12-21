<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head></head>
<body>
<c:forEach items="${logs}" var="msg">
    <c:out value="${msg}"></c:out>
</c:forEach>
</body>
</html>