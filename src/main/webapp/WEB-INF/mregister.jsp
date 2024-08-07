<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Supervisor Register</title>
</head>
<body>
<h1>Supervisor Register Page</h1>

<form action="/page/admin/mregister" method="post">
    <label>ID</label>
    <input type="text" name="sid" required>

    <label>Password</label>
    <input type="password" name="spw" required>

    <label>Department</label>
    <input type="text" name="dept" required>

    <button>Register</button>
</form>
</body>
</html>
