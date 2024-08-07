<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin Setting Page</title>
</head>
<body>
<h1>Supervisor List
    <a href="/page/admin/mregister"><button>새 관리자 등록</button></a>
</h1>
<table border="1">
    <tr>
        <th>부서</th>
        <th>아이디</th>
        <th>비밀번호</th>
    </tr>
    <c:forEach var="supervisor" items="${supervisorList}">
        <tr>
            <td>${supervisor.dept}</td>
            <td>${supervisor.sid}</td>
            <td>${supervisor.spw}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
