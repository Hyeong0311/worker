<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin Setting Page</title>
    <script>
        function confirmDeletion(sid, dept) {
            var password = prompt("부서명: " + dept + " / 아이디명: " + sid + "\n정말로 삭제하시겠습니까? \nadmin 계정의 비밀번호를 한번 더 입력해주세요.");
            if (password != null) {
                document.getElementById("deleteForm").password.value = password;
                document.getElementById("deleteForm").sid.value = sid;
                document.getElementById("deleteForm").submit();
            }
        }
    </script>
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
        <th>삭제</th>
    </tr>
    <c:forEach var="supervisor" items="${supervisorList}">
        <tr>
            <td>${supervisor.dept}</td>
            <td>${supervisor.sid}</td>
            <td>${supervisor.spw}</td>
            <td><button type="button" onclick="confirmDeletion('${supervisor.sid}', '${supervisor.dept}')">삭제</button></td>
        </tr>
    </c:forEach>
</table>

<form id="deleteForm" method="post" action="/page/admin/deleteSupervisor">
    <input type="hidden" name="sid" />
    <input type="hidden" name="password" />
</form>
</body>
</html>
