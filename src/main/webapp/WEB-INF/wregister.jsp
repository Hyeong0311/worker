<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2024-08-06
  Time: 오후 8:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>register page</h1>

<form action="/page/normal/wregister" method="post">
    <label>ID</label>
    <input type="text" name="wid">

    <label>NAME</label>
    <input type="text" name="wname">

    <label>SID</label>
    <input type="text" name="sid" value="${cookieSid}">

    <button>register</button>
</form>
<a href ="/page/normal?sid=${cookieSid}"><button>목록</button></a>
</body>
</html>
