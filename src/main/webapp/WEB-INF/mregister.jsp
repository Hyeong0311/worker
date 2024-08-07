<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Supervisor Register</title>
    <style>
        .input-group {
            margin-bottom: 10px;
        }
        .input-group label {
            display: block;
            margin-bottom: 5px;
        }
        .input-group input {
            width: 50%;
            padding: 8px;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
<h1>Supervisor Register Page</h1>

<form action="/page/admin/mregister" method="post">
    <div class="input-group">
        <label for="sid">ID</label>
        <input type="text" id="sid" name="sid" required>
    </div>
    <div class="input-group">
        <label for="spw">Password</label>
        <input type="password" id="spw" name="spw" required>
    </div>
    <div class="input-group">
        <label for="dept">Department</label>
        <input type="text" id="dept" name="dept" required>
    </div>
    <div>
        <button type="button" onclick="location.href='/page/admin'">관리자 목록</button>
        <button type="submit">신규 등록</button>
    </div>
</form>
</body>
</html>
