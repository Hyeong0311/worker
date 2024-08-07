<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jhyeong
  Date: 2024. 8. 6.
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>hr page</h1>

    <c:if test="${list == null}">
        <h3>null</h3>
    </c:if>

    <c:forEach items="${list}" var="schedule">
        <li>
            <div>
                <div>${schedule}</div>
            </div>
        </li>
    </c:forEach>

</body>
</html>
