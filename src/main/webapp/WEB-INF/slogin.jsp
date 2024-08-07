<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Supervisor Login</title>
    <script>
        function setFormAction() {
            // 라디오 버튼 중에서 선택된 값 가져오기
            var selectedValue = document.querySelector('input[name="option"]:checked').value;

            // 폼의 action 속성을 선택된 값에 따라 설정
            var form = document.getElementById('myForm');
            if (selectedValue === "supervisor") {
                form.action = "/login/supervisor";
            } else if (selectedValue === "hr") {
                form.action = "/login/hr";
            } else if (selectedValue === "ad") {
                form.action = "/login/admin";
            }
        }
    </script>
</head>
<body>

<form id="myForm" method="post" onsubmit="setFormAction()">
    <input type="radio" name="option" value="supervisor" id="supervisor">
    <label for="supervisor">Supervisor</label><br>

    <input type="radio" name="option" value="hr" id="hr">
    <label for="hr">HRD</label><br>

    <input type="radio" name="option" value="ad" id="ad">
    <label for="ad">Admin</label><br><br>

    <input type="text" name="id">
    <input type="password" name="pw">
    <button type="submit">Submit</button>
</form>


<%--<form action="/login/supervisor" method="post">--%>

<%--    <input type="radio" name="dept" value="a-dept" required>--%>
<%--    <label>a-dept</label>--%>

<%--    <input type="radio" name="dept" value="b-dept" required>--%>
<%--    <label>b-dept</label>--%>

<%--    <input type="radio" name="dept" value="c-dept" required>--%>
<%--    <label>c-dept</label>--%>

<%--    <label>ID</label>--%>
<%--    <input type="text" name="sid">--%>

<%--    <label>PW</label>--%>
<%--    <input type="password" name="spw">--%>
<%--    <button>submit</button>--%>
<%--</form>--%>

</body>
</html>
