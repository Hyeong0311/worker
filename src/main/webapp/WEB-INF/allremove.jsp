<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2024-08-07
  Time: 오후 4:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>all remove page</h1>

<form action="/page/normal/allremove" method="post">
    <button type="submit">YES</button>
</form>
<a href="/page/normal?sid=${cookieSid}"><button>NO</button></a>
</body>
</html>
